import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int R, C, ans;
	static int[][] map, dp;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		map = new int[R][C];
		dp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				dp[i][j] = -1;
			}
		}
		
		dp[0][0] = 0;
		for(int d = 0; d < 2; d++) {
			int nr = 0 + dr[d];
			int nc = 0 + dc[d];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] >= map[0][0])
				continue;
			else
				dp[nr][nc] = 1;
			
		}
		
		TopDown(R-1,C-1);
		System.out.println(dp[R-1][C-1]);

	}
	
	private static int TopDown(int r, int c) {
		if(dp[r][c] != -1) return dp[r][c];
		int cnt = 0;
		for(int d = 0; d < dr.length; d++) {
			int pr = r - dr[d];
			int pc = c - dc[d];
			
			if (pr < 0 || pr >= R || pc < 0 || pc >= C)
				continue;
			
			if(map[pr][pc] > map[r][c]) cnt += TopDown(pr,pc);
			
		}
		return dp[r][c] = cnt;
	}


}
