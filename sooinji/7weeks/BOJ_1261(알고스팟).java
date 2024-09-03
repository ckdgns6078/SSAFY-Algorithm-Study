import java.util.*;

class Node implements Comparable<Node>{
	int x, y, cost;
	Node(int x, int y, int cost) {
		this.x= x;
		this.y = y;
		this.cost = cost;
	}
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int n, m;
	static int[][] map;
	static int[][] dist;
	static boolean[][] vis;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[m][n];
		dist = new int[m][n];
		vis = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			String s = sc.next();
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.MAX_VALUE;
				map[i][j] = (int)s.charAt(j) - '0';
			}
		}
		// 다익스트라 알고리즘 과정
		// 1. 아직 방문하지 않은 정점 중 출발지로부터 가장 거리가 짧은 정점을 방문
		// 2. 해당 정점을 거쳐 갈 수 있는 정점의 거리가 이전 기록한 값보다 적으면 갱신	
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		dist[0][0] = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (vis[cur.x][cur.y]) continue;
			vis[cur.x][cur.y] = true;
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= m || ny < 0 || ny >=n) continue;
				if (dist[nx][ny] > dist[cur.x][cur.y] + map[nx][ny]) {
					dist[nx][ny] = dist[cur.x][cur.y] + map[nx][ny];
					pq.offer(new Node(nx, ny, dist[nx][ny]));
				}
			}
		}
		System.out.println(dist[m - 1][n - 1]);
	}
}
