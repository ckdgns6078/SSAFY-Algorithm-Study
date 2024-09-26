import java.util.*;

class Point {
	int x, y, d;
	Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class Main {
	static char[][] map = new char[8][8];
	static boolean[][] vis = new boolean[8][8];
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1, 0};
	static int[] dy = {0, 0, 1, -1, 1, 1, -1, -1, 0};
	static int ans = 0;
	static boolean wall = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 8; i++) {
			String s = sc.next();
			for (int j = 0; j < 8; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '#') wall = true;
			}
		}
		bfs(7, 0);
		if (vis[0][7]) System.out.println(1);
		else System.out.println(0);
	}
	private static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		vis[x][y] = true;
		q.offer(new Point(x, y, 0));
		Point prev = null;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.x == 0 && cur.y == 7) return;
//			System.out.println(cur.x + " " + cur.y + " " + cur.d);
			if (prev != null && prev.d < cur.d) {
				down();
			}
			prev = cur;
			if (map[cur.x][cur.y] == '#') continue;
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;
				if (map[nx][ny] == '#') continue;
				if (wall == false && vis[nx][ny]) continue;
				vis[nx][ny] = true;
				q.offer(new Point(nx, ny, cur.d + 1));
			}
		}
	}
	private static void down() {
		wall = false;
		for (int i = 6; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				map[i + 1][j] = map[i][j];
				if (map[i][j] == '#') wall = true;
			}
		}
		for (int j = 0; j < 8; j++) {
			map[0][j] = '.';
		}
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
	}
}
