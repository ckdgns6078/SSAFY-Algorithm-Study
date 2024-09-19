import java.util.*;

public class Main {
	static int m, n, ans = 0;
	static int[][] map;
	static int[][] dp;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		map = new int[m][n];
		dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				dp[i][j] = -1;
			}
		}
		int ans = dfs(0, 0);
		System.out.println(ans);
	}
	private static int dfs(int x, int y) {
		if (x == m - 1 && y == n - 1) return 1;
		if (dp[x][y] != -1) return dp[x][y];
		dp[x][y] = 0;
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
			if (map[nx][ny] < map[x][y]) {
				dp[x][y] = dp[x][y] + dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
}
