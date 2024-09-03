import java.util.*;

class Edge implements Comparable<Edge>{
	int w, cost;
	Edge (int w, int cost) {
		this.w = w;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int n, m, v, w, cost;
	static ArrayList<Edge>[] adj;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// MST 만들고 가장 큰 간선 삭제?
		n = sc.nextInt();
		m = sc.nextInt();
		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			v = sc.nextInt();
			w = sc.nextInt();
			cost = sc.nextInt();
			adj[v].add(new Edge(w, cost));
			adj[w].add(new Edge(v, cost));
		}
		
		// 프림 시작
		int maxCost = 0;
		int ans = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] vis = new boolean[n + 1];
		pq.offer(new Edge(1, 0));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (vis[cur.w]) continue;
			vis[cur.w] = true;
			maxCost = Math.max(maxCost, cur.cost);
			ans += cur.cost;
			for (Edge e : adj[cur.w]) {
				if (vis[e.w]) continue;
				pq.offer(e);
			}
		}
		System.out.println(ans - maxCost);
	}
}
