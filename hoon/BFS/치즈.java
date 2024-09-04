import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean[][] v;
	static int cheese = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					cheese++;
				}
			}
		}

		while (cheese > 0) {
			result++;
			v = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0) {
						if (i == 0 || i == n - 1 || j == 0 || j == m - 1 && !v[i][j]) {
							bfs(i, j, v);
						}
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {
						int cnt = 0;
						for (int k = 0; k < 4; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							if (nr >= 0 && nr < n && nc >= 0 && nc < m && v[nr][nc]) {
								cnt++;
							}
						}
						if (cnt >= 2) {
							map[i][j] = 0;
							cheese--;
						}
					}
				}
			}

//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("==========================");
		}

		System.out.println(result);

	}

	private static void bfs(int r, int c, boolean[][] v) {
		Queue<int[]> q = new ArrayDeque();
		v[r][c] = true;
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 0 && !v[nr][nc]) {
					q.offer(new int[] { nr, nc });
					v[nr][nc] = true;
				}

			}

		}

	}

}
