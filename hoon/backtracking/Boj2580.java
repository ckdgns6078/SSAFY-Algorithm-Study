import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Boj2580 {

	static int[][] map = new int[9][9];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, 0);

		for (int i = 0; i < 9; i++) {
			System.out.println(Arrays.toString(map[i]));
		}

	}

	private static void dfs(int r, int c) {
		// basis part
		if (c >= 9) {
			dfs(r + 1, 0);
			return;
		}
		if(r == 9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			
			System.exit(0);
		}
		
		if (map[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (check(r, c, i)) {
					System.out.println("true");
					map[r][c] = i;
					dfs(r, c + 1);
				}
			}
			map[r][c] = 0;
			return;
		}
		dfs(r, c + 1);
	}

	private static boolean check(int r, int c, int val) {
		// 가로검사
		for (int i = 0; i < 9; i++) {
			if (map[r][i] == val) {
				return false;
			}
		}

		// 세로검사
		for (int i = 0; i < 9; i++) {
			if (map[i][c] == val) {
				return false;
			}
		}

		// 3*3 검사
		int nr = (r / 3) * 3;
		int nc = (c / 3) * 3;

		for (int i = nr; i < nr + 3; i++) {
			for (int j = nc; j < nc + 3; j++) {
				if (map[i][j] == val)
					return false;
			}
		}

		return true;
	}
}

// 스도쿠의 값을 1~9를 넣고 그 값이 맞는지 안맞는지 확인하고
// 확인한 값이 맞으면 종료하고 출력한다 백트래킹을 사용해야한다.