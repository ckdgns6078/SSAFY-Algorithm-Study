import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		double dist;
		public Edge(int s, int e, double dist) {
			super();
			this.s = s;
			this.e = e;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dist, o.dist);
		}
		
	}
	
	static int[] parents;
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		ArrayList<int[]> gods = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			gods.add(new int[]{x,y});
		}

				
		PriorityQueue<Edge> edges = new PriorityQueue();
		
		for(int i = 0; i < N; i++) {
			int[] g1 = gods.get(i);
			for(int j = i+1 ; j < N; j++) {
				int[] g2 = gods.get(j);
				double dist = getDist(g1, g2);
				edges.offer(new Edge(i,j,dist));
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			edges.offer(new Edge(s,e,0));
		}
		
		makeSet();
		int cnt = 0;
		double ans = 0.;
		while(cnt < N-1) {
			Edge target = edges.poll();
			if(findSet(target.s) != findSet(target.e)) {
				cnt++;
				ans += target.dist;
				union(target.s, target.e); 
			}
		}
		System.out.println(String.format("%.2f", ans));
	}
	
	private static boolean union(int s, int e) {
		int parentS = findSet(s);
		int parentE = findSet(e);
		
		if(parentS == parentE) return false;
		
		else {
			parents[parentE] = parentS;
			return true;
		}
		
	}

	private static void makeSet() {
		parents = new int[N];
		for(int i = 0; i < N; i++) 
			parents[i] = i;
	}
	
	private static int findSet(int i) {
		if(parents[i] == i) return parents[i];
		else {
			return parents[i] = findSet(parents[i]);
		}
	}
	
	
	

	private static double getDist(int[] o1, int[] o2) {
		return Math.sqrt(Math.pow(o1[0]-o2[0], 2)+Math.pow(o1[1]-o2[1], 2));
	}

}
