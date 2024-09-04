import java.util.*;

class Point {
	int rx, ry, bx, by, cnt;
	boolean redGoal = false;
	boolean blueGoal = false;
	Point(int rx, int ry, int bx, int by, int cnt) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
}

public class Main {
	static int n, m;
	static char[][] map;
	static boolean[][][][] vis;
	static Point st;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new char[n][m];
		vis = new boolean[n][m][n][m];
		st = new Point(0, 0, 0, 0, 0);
		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					st.rx = i;
					st.ry = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'B') {
					st.bx = i;
					st.by = j;
					map[i][j] = '.';
				}
			}
		}
		Queue<Point> q = new ArrayDeque<>();
		vis[st.rx][st.ry][st.bx][st.by] = true;
		q.offer(st);
		boolean goal = false;
		while(!q.isEmpty() && !goal) {
			Point cur = q.poll();
			if (cur.cnt >= 10) break;
			
			for (int i = 0; i < dx.length; i++) {
				Point nxt = move(cur, i);
				if (nxt.blueGoal) continue;
				if (nxt.redGoal) {
					goal = true;
					break;
				}
				// 다음 칸 이동
				if (vis[nxt.rx][nxt.ry][nxt.bx][nxt.by]) continue;
				vis[nxt.rx][nxt.ry][nxt.bx][nxt.by] = true;
				q.offer(nxt);
			}
		}
		if (goal) System.out.println(1);
		else System.out.println(0);
	}
	private static Point move(Point cur, int dir) {
		Point nxt = new Point(cur.rx, cur.ry, cur.bx, cur.by, cur.cnt + 1);
		// red부터 이동
		while (true) {
			int nx = nxt.rx + dx[dir];
			int ny = nxt.ry + dy[dir];
			if (map[nx][ny] == '#') break;
			if (map[nx][ny] == 'O') {
				nxt.redGoal = true;
				break;
			}
			 nxt.rx = nx; 
			 nxt.ry = ny;
		}
		// blue 이동
		while (true) {
			int nx = nxt.bx + dx[dir];
			int ny = nxt.by + dy[dir];
			if (map[nx][ny] == '#') break;
			if (map[nx][ny] == 'O') {
				nxt.blueGoal = true;
				break;
			}
			 nxt.bx = nx; 
			 nxt.by = ny;
		}
		// 동시에 같은 칸 이동하는 경우
		if (nxt.rx == nxt.bx && nxt.ry == nxt.by) {
			int redDist = Math.abs(nxt.rx - cur.rx) + Math.abs(nxt.ry - cur.ry);
			int blueDist = Math.abs(nxt.bx - cur.bx) + Math.abs(nxt.by - cur.by);
			if (redDist > blueDist) {
				nxt.rx -= dx[dir];
				nxt.ry -= dy[dir];
			}
			else {
				nxt.bx -= dx[dir];
				nxt.by -= dy[dir];
			}
		}
		return nxt;
	}
}
