import java.util.*;

class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m, cnt = 0;
	static int[][] map;
	static boolean[][] vis;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		vis = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt(); 
				if (map[i][j] == 1) cnt++;
			}
		}
		int time = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 치즈 가장자리 2로 칠하기
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
					if (map[i][j] == 0 && !vis[i][j]) bfs(i, j);
				}
			}
		}
		while (cnt > 0) {
			// 치즈 먹기
			eat();
			time++;
		}
		System.out.println(time);
	}
	private static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		vis[x][y] = true;
		map[x][y] = 3;
		q.offer(new Point(x, y));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (vis[nx][ny] || map[nx][ny] == 1) continue;
				if (map[nx][ny] == 0) {
					vis[nx][ny] = true;
					map[nx][ny] = 3;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	private static void eat() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					int air = 0;
					for (int d = 0; d < dx.length; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
						if (map[nx][ny] == 3) air++;
					}
					if (air >= 2) map[i][j] = 2;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2) {
					map[i][j] = 3;
					bfs(i, j);
					cnt--;
				}
			}
		}
	}
}
