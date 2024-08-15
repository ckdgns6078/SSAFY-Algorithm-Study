import java.util.Scanner;

public class Main {

	static int N;
	static int[][] M;
	static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				M[i][j] = sc.nextInt();
			}
		}

		pipe(0, 1, 1);
		System.out.println(cnt);
	}

	private static void pipe(int r, int c, int state) {// state -> 1: 가로, 2: 세로, 3 : 대각선
		if (r == N - 1 && c == N - 1) {
			cnt++;
			return;
		}
		switch (state) {
		case 1:				// 가로 상태
			if (c + 1 < N && M[r][c + 1] == 0) {		// 가로 이동
				pipe(r, c + 1, 1);
			}
			if (r + 1 < N && c + 1 < N && M[r + 1][c + 1] == 0 && M[r + 1][c] == 0 && M[r][c + 1] == 0) { // 대각선 이동
				pipe(r + 1, c + 1, 3);
			}
			break;

		case 2:				// 세로 상태
			if (r + 1 < N && M[r+1][c] == 0) {			// 세로 이동
				pipe(r+1, c, 2);
			}
			if (r + 1 < N && c + 1 < N && M[r + 1][c + 1] == 0 && M[r + 1][c] == 0 && M[r][c + 1] == 0) { // 대각선 이동
				pipe(r + 1, c + 1, 3);
			}
			break;
		case 3:				// 대각선 상태
			if (c + 1 < N && M[r][c + 1] == 0)			// 가로 이동
			{
				pipe(r, c + 1, 1);
			}
			if (r + 1 < N && M[r+1][c] == 0)			// 세로 이동
			{
				pipe(r+1, c, 2);
			}
			if (r + 1 < N && c + 1 < N && M[r + 1][c + 1] == 0 && M[r + 1][c] == 0 && M[r][c + 1] == 0) {	//대각선 이동
				pipe(r + 1, c + 1, 3);
			}
			break;
		}

	}

}
