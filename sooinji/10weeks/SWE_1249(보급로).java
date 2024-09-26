import java.util.*;
import java.awt.*;

public class Solution {
	static int t, n;
	static int[][] map;
	static int[][] dis;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int test_case = 1; test_case <= t; test_case++) {
			n = sc.nextInt();
			map = new int[n][n];
			dis = new int[n][n];
			for (int i = 0; i < n; i++) {
				String s = sc.next();
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(Character.toString(s.charAt(j)));
					dis[i][j] = Integer.MAX_VALUE;
				}
			}
			bfs(0, 0);
			System.out.println("#" + test_case + " " + dis[n - 1][n - 1]);
		}
	}
	private static void bfs(int x, int y) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		Queue<Point> q = new ArrayDeque<>();
		dis[x][y] = 0;
		q.offer(new Point(0, 0));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (dis[nx][ny] > dis[cur.x][cur.y] + map[cur.x][cur.y]) {
					dis[nx][ny] = dis[cur.x][cur.y] + map[cur.x][cur.y];
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
}
