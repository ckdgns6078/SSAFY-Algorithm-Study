import java.util.*;

class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {
	int w, cost;
	Edge(int w, int cost) {
		this.w = w;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int n, m, cnt, ans;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<Edge>[] adj = new ArrayList[8];
	static boolean[] vis = new boolean[8];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < 8; i++) adj[i] = new ArrayList();
		// 섬 구분하기
		cnt = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					fill(i, j);
				}
			}
		}
		// 다리 만들기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] > 0) bridge(i, j);
			}
		}
		// 다리 길이 최솟값 계산
		ans = 0;
		prim();
		// 모든 섬이 연결되었는지 확인
		for (int i = 2; i < cnt; i++) {
			if (!vis[i]) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		System.out.print(ans);
	}

	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(2, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (vis[cur.w]) continue;
			vis[cur.w] = true;
			ans += cur.cost;
			
			for (Edge e : adj[cur.w]) {
				if (vis[e.w]) continue;
				pq.offer(e);
			}
		}
		
	}

	private static void bridge(int x, int y) {
		for (int d = 0; d < dx.length; d++) {
			int nx, ny, len = 1;
			int w = -1;
			while (true) {
				nx = x + dx[d] * len;
				ny = y + dy[d] * len;
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
				if (map[nx][ny] > 1) {
					w = map[nx][ny];
					break;
				}
				len++;
			}
			if (--len > 1 && w > 1) {
				adj[map[x][y]].add(new Edge(w, len));
			}
		}
	}

	private static void fill(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x,y));
		map[x][y] = cnt;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (map[nx][ny] == 1) {
				q.offer(new Point(nx, ny));
				map[nx][ny] = cnt;
				}
			}
		}
		cnt++;
	}
}
