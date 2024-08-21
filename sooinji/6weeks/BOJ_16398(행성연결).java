import java.util.*;

class Edge implements Comparable<Edge>{
	int w;
	int cost;
	
	Edge(int  w, int cost){
		this.w = w;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Edge>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++) adj[i] = new ArrayList();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int c = sc.nextInt();
				adj[i].add(new Edge(j, c));
				adj[j].add(new Edge(i, c));
			}
		}
		boolean[] vis = new boolean[n];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		
		long tot = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int v = cur.w;
			int cost = cur.cost;
			
			if (vis[v]) continue;
			
			vis[v] = true;
			tot += cost;
			
			for (Edge e : adj[v]) {
				if (!vis[e.w]) {
					pq.add(e);
				}
			}
		}
		System.out.println(tot);
	}
}
