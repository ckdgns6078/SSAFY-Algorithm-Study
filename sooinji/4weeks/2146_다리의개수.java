import java.util.*;

class Point {
	int x, y, d;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
public class Main {
	
	static int n, cnt = 1;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					cnt++;
					bfs(i, j);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 1) {
					bfs2(i, j);
				}
			}
		}
		System.out.println(ans - 1);
	}
	private static void bfs2(int x, int y) {
		boolean[][] vis = new boolean[n][n];
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y, 0));
		vis[x][y] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int nd = cur.d + 1;
				// 범위 밖
				if (nx < 0 || nx >= n || ny < 0 || ny >=n) continue;
				// 방문 o 점
				if (vis[nx][ny]) continue;
				// 육지일 때 
				if (map[nx][ny] == map[x][y]) continue;
				// 다른 섬에 도착했으면 탐색 종료
				if (map[nx][ny] != 0 && map[x][y] != map[nx][ny]) {
					if (nd < ans) ans = nd;
					break;
				}
				q.offer(new Point(nx, ny, nd));
				vis[nx][ny] = true;
			}
		}
	}
	private static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y));
        map[x][y] = cnt;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				// 범위 밖
				if (nx < 0 || nx >= n || ny < 0 || ny >=n) continue;
				// 방문 o 점, 바다
				if (map[nx][ny] > 1 || map[nx][ny] == 0) continue;
				q.offer(new Point(nx, ny));
                map[nx][ny] = cnt;
			}
		}
	}

}
