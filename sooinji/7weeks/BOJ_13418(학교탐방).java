import java.util.*;

class Edge implements Comparable <Edge>{
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
	static int n, m, a, b, c;
	static boolean[] vis;
	static ArrayList<Edge>[] adj; // 최선의 경우, 1일 때 k++
	static ArrayList<Edge>[] adj2; // 최악의 경우, 0일 때 k2++
	static int ans = 0; // 최악 - 최선
	static int k, k2; // 피로도 = 오르막길 개수의 제곱
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		adj = new ArrayList[n + 1];
		adj2 = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
			adj2[i] = new ArrayList<>();
		}
		for (int i = 0; i < m + 1; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			adj[a].add(new Edge(b, c * (-1)));
			adj[b].add(new Edge(a, c * (-1)));
			adj2[a].add(new Edge(b, c));
			adj2[b].add(new Edge(a, c));
		}
		vis = new boolean[n + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0,-1));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (vis[cur.w]) continue;
			vis[cur.w] = true;
			if (cur.cost == 0) k++;
			for (Edge e : adj[cur.w]) {
				if (vis[e.w]) continue;
				pq.offer(e);
			}
		}
		k *= k;
		vis = new boolean[n + 1];
		pq.offer(new Edge(0,-1));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (vis[cur.w]) continue;
			vis[cur.w] = true;
			if(cur.cost == 0) k2++;
			for (Edge e : adj2[cur.w]) {
				if (vis[e.w]) continue;
				pq.offer(e);
			}
		}
		k2 *= k2;
		System.out.println(k2 - k);
	}
}
