import java.util.*;

public class Main {
	static int v, e, a, b;
	static ArrayList<Integer>[] adjList;
	static boolean vis[];
	static int cnt = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		v = sc.nextInt();
		e = sc.nextInt();
		adjList = new ArrayList[v + 1];
		vis = new boolean[v + 1];
		for (int i = 1; i <= v; i++) {
			adjList[i] = new ArrayList();
		}
		for (int i = 0; i < e; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			adjList[a].add(b);
			adjList[b].add(a);
		}
		bfs(1);
		System.out.println(cnt);
	}

	private static void bfs(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(idx);
		vis[idx] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < adjList[cur].size(); i++) {
				int nxt = adjList[cur].get(i);
				if (!vis[nxt]) {
					q.offer(nxt);
					vis[nxt] = true;
					cnt++;
				}
			}
		}
	}
}
