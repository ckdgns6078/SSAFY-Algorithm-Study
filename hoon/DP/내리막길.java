import java.util.*;

public class Main {
	static int m; // 세로
	static int n; // 가로
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		m = sc.nextInt();
		n = sc.nextInt();
		map = new int[m][n];
		dp = new int[m][n];
	
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				dp[i][j]=-1;
			}
		}

//		dfs(0, 0);
		System.out.println(dfs(0, 0));	
	}

	private static int dfs(int r, int c) {
		if(r==m-1 && c ==n-1) {
			return 1;
		}
		if(dp[r][c]==-1) {
			dp[r][c] = 0;
			for(int i = 0 ; i < 4 ; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				
				if(nr>=0 && nr<m && nc>=0 && nc<n && map[r][c]>map[nr][nc]) {
					dp[r][c]+= dfs(nr,nc);
				}
			}
			
		}
		return dp[r][c];
	}
}
