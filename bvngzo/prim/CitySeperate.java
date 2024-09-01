import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static ArrayList<Vertex>[] buildings;
	
	static class Vertex implements Comparable<Vertex>{
		int e;
		int w;
		public Vertex(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.w, o.w);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		buildings = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			buildings[i] = new ArrayList<Vertex>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			buildings[s].add(new Vertex(e,w));
            buildings[e].add(new Vertex(s,w));
			
		}

		boolean[] selected = new boolean[N];
		int[] dist = new int[N];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[0] = 0;
		long cost = 0;
		int maxWeight = Integer.MIN_VALUE;
		PriorityQueue<Vertex> PQ = new PriorityQueue<Vertex>();
		
		PQ.offer(new Vertex(0,0));
		
		while(!PQ.isEmpty()) {
			Vertex curr = PQ.poll();
			
			if(selected[curr.e]) continue;
			
			selected[curr.e] = true;
			cost += dist[curr.e];
			maxWeight = Math.max(maxWeight, dist[curr.e]);
			
			for(Vertex next : buildings[curr.e]) {
				if(!selected[next.e] && dist[next.e] > next.w) {
					dist[next.e] = next.w;
					PQ.offer(next);
				}
			}
			
		}
		
		System.out.println(cost-maxWeight);
		
	}

}
