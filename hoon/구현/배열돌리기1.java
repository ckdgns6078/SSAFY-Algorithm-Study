import java.util.*;

public class Main {
	static int n, m, r;
	static int[][] map;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

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

		int cycle = Math.min(n, m) / 2;

		for (int k = 0; k < r; k++) {
			for (int c = 0; c < cycle; c++) {
				int rr = c;
				int cc = c;
				int cnt = 0;
				int data = map[rr][cc];
				int data2 =0;
				while (cnt < 4) {
					
					rr+=dr[cnt];
					cc+=dc[cnt];
					data2 =map[rr][cc];
					map[rr][cc]=data;
					
					data = data2;
					
					
					int nr = rr+dr[cnt];
					int nc = cc+dc[cnt];
					if (nr < c || nr >= n-c || nc < c || nc >= m-c) {
						cnt++;

					}
					

				}

			}
		}
	
		StringBuffer sb = new StringBuffer();
		for( int i = 0 ; i < n ; i++) {
			for( int j = 0 ; j < m ; j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

}
