import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean checked(int r, int c, int R, int C) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int R = sc.nextInt();
		int C = sc.nextInt();
		int[][] M = new int[R][C];
		int[][][] dist = new int[2][R][C];

		dist[0][0][0] = 1;
		dist[1][0][0] = 1;

		for (int i = 0; i < R; i++) {
			String str = sc.next();
			for (int j = 0; j < C; j++) {
				M[i][j] = str.charAt(j) - '0';
			}
		}

		Queue<int[]> loc = new ArrayDeque();
		loc.offer(new int[] { 0, 0, 0 });

		while (!loc.isEmpty()) {
			int[] curr = loc.poll();

			int state = curr[0];
			int curr_r = curr[1];
			int curr_c = curr[2];

			for (int d = 0; d < dc.length; d++) {
				int nw_r = curr_r + dr[d];
				int nw_c = curr_c + dc[d];

				if (!checked(nw_r, nw_c, R, C))
					continue;

				if (M[nw_r][nw_c] == 0) {
					if (dist[state][nw_r][nw_c] == 0) {
						loc.offer(new int[] { state, nw_r, nw_c });
						dist[state][nw_r][nw_c] = dist[state][curr_r][curr_c] + 1;
					} else {
						dist[state][nw_r][nw_c] = Math.min(dist[state][nw_r][nw_c], dist[state][curr_r][curr_c] + 1);
					}
				} else if (M[nw_r][nw_c] == 1) {
					if (state == 0) {
						loc.offer(new int[] { state + 1, nw_r, nw_c });
						dist[state + 1][nw_r][nw_c] = dist[state][curr_r][curr_c] + 1;
					}
				}

			}

		} // end while

		int ans = Integer.MAX_VALUE;
		
		for(int i = 0; i < dist.length; i++) {
			if(dist[i][R-1][C-1] != 0) {
				ans = Math.min(ans, dist[i][R-1][C-1]);
			}
		}
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);

	}

}
