import java.util.*;

class Point {
	int x, y, cnt;
	boolean[] vis;
	Point(int x, int y, int cnt, boolean[] vis) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.vis = vis;
	}
}

public class Solution {
	static int t, r, c;
	static char[][] map;
	static int ans;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int test_case = 1; test_case <= t; test_case++) {
			System.out.print("#" + test_case + " ");
			r = sc.nextInt();
			c = sc.nextInt();
			sc.nextLine();
			ans = 0;
			map = new char[r][c];
			for (int i = 0; i < r; i++) {
				String s = sc.nextLine();
				for (int j = 0; j < c; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			dfs(new Point(0, 0, 1, new boolean[30]));
			System.out.println(ans);
		}
	}
	private static void dfs(Point p) {
		Stack<Point> st = new Stack<>();
		st.push(p);
		p.vis[map[0][0] - 'A'] = true;
		while (!st.isEmpty()) {
			Point cur = st.pop();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					if (cur.cnt > ans) ans = cur.cnt;
					continue;
				}
				if (cur.vis[map[nx][ny] - 'A']) {
					if (cur.cnt > ans) ans = cur.cnt;
					continue;
				}
				Point nxt = new Point(nx, ny, cur.cnt + 1, new boolean[30]);
				nxt.vis = cur.vis.clone();
				nxt.vis[map[nx][ny] - 'A'] = true;
				st.push(nxt);
			}
		}
		return;
	}

}
