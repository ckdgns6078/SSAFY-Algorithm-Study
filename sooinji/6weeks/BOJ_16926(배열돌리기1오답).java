import java.util.*;
public class BOJ_16926 {
	static int n, m, r;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		int x = 0;
		int y = 0;
		int d = 0;
		int idx = 0;
		Queue<Integer>[] q = new ArrayDeque[m/2 + m%2];
		for (int i = 0; i < m/2 + m%2; i++) q[i] = new ArrayDeque<>();
		boolean[][] vis = new boolean[n][m];
		while (cnt < n*m) {
			vis[x][y] = true;
			q[idx].offer(map[x][y]);
			cnt++;
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m || vis[nx][ny]) {
				d = (d + 1) % 4;
				if (d == 0) {
					idx++;
				}
				nx = x + dx[d];
				ny = y + dy[d];
			}
			x = nx;
			y = ny;
		}
		for (int j = 0; j < idx; j++) {
			for (int i = 0; i < r; i++) {
				q[j].offer(q[j].poll());
			}	
		}
		cnt = 0;
		x = 0;
		y = 0;
		d = 0;
		idx = 0;
		vis = new boolean[n][m];
		while (cnt < n*m) {
			vis[x][y] = true;
			map[x][y] = q[idx].poll();
			cnt++;
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m || vis[nx][ny]) {
				d = (d + 1) % 4;
				if (d == 0) idx++;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			x = nx;
			y = ny;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
