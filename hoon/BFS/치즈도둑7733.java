import java.util.*;

public class 치즈도둑7733 {

	static int n;
	static int[][] map;
	static int max;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
	
		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			n = sc.nextInt();
			map = new int[n][n];
			result = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int input = sc.nextInt();
					max = Math.max(max, input);
					map[i][j] = input;
				}
			}
			if( max ==1 ) {
				System.out.println("#"+ tc +" " + 1);
			}else {
				for (int k = 1; k <= max; k++) {
					int check = 0;
					boolean[][] v = new boolean[n][n];
					
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							if (map[i][j] > k && !v[i][j]) {
								bfs(i, j, v , k);
								check++;
							}
						}
					}
					result = Math.max(result, check);
				}
			}

			System.out.println("#"+ tc +" " + result);
		}

	}

	private static void bfs(int r, int c, boolean[][] v , int day) {
		Queue<int[]> q = new ArrayDeque();
		v[r][c] = true;
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if(nr>=0 && nr < n && nc>=0 && nc < n && !v[nr][nc] && map[nr][nc]>day) {
					q.offer(new int[] {nr, nc});
					v[nr][nc]= true;
				}
				
				
			}

		}

	}

}
