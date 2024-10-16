import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Link{
		int s;
		int e;
		int time;
		public Link(int s, int e, int time) {
			super();
			this.s = s;
			this.e = e;
			this.time = time;
		}
		@Override
		public String toString() {
			return s +" "+ e;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Link>[] graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) 
			graph[i] = new ArrayList<Link>();
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			Link link1 = new Link(s,e,time);
			Link link2 = new Link(e,s,time);
			
			graph[s].add(link1);
			graph[e].add(link2);
		}
		
		//세팅
		ArrayList<Link> ans = new ArrayList<Link>();
		int[] dist = new int[N+1];
		boolean[] selected = new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2) -> Integer.compare(o1[1], o2[1]));
		PQ.offer(new int[] {1,0});
		dist[1] = 0;
		
		while(!PQ.isEmpty()) {
			int[] node = PQ.poll();
			
			if(selected[node[0]]) continue;
			selected[node[0]] = true;
			
			for(int i = 0; i < graph[node[0]].size(); i++) {
				Link temp = graph[node[0]].get(i);
				
				if(!selected[temp.e] && dist[temp.e] > node[1] + temp.time) {
					dist[temp.e] = node[1] + temp.time;
					PQ.offer(new int[] {temp.e,dist[temp.e]});
					
					for(Link link : ans) {
						if(link.e == temp.e) {
							ans.remove(link);
							break;
						}
					}
					ans.add(temp);
				}
			}
		}
		
		System.out.println(ans.size());
		for(Link l : ans)
			System.out.println(l);
		
	}

}
