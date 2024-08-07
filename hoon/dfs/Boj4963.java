import java.util.Iterator;
import java.util.Scanner;

public class Boj4963 {

	static int[][] map;
	static boolean[][] v;
	static int[] ar = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] ac = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int cnt;
	static int h, w;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			
		w = sc.nextInt();
		h = sc.nextInt();
		if( w==0 && h==0) {
			break;
		}
		cnt=0;
		map = new int[h][w];
		v = new boolean[h][w];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 1 && !v[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		}
	}

	private static void dfs(int r, int c) {
		v[r][c] = true;

		for (int i = 0; i < ac.length; i++) {
			int nr = ar[i] + r;
			int nc = ac[i] + c;

			if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] == 1 && !v[nr][nc]) {
				v[nr][nc] = true;
				dfs(nr, nc);
			}

		}

	}

}
