import java.util.*;

public class Main {
	static int[][] gears = new int[5][8];
	static int[][] shift;
	static boolean[] v;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 1; i < 5; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				gears[i][j] = c - '0';
			}
		}
		int k = sc.nextInt();
		shift = new int[k][2];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < 2; j++) {
				shift[i][j] = sc.nextInt();
			}
		}

		// 톱니의 왼쪽끝은 0 과 4

		for (int i = 0; i < k; i++) {
			v=new boolean[5];
			v[shift[i][0]] = true;
			rotate(shift[i][0], shift[i][1]);
		}


		int result = 0;
		int cnt = 1;
		for (int i = 1; i < gears.length; i++) {
			if (gears[i][0] == 1) {
				result += cnt;
			}
			cnt = cnt * 2;
		}

		System.out.println(result);

	}

	private static void rotate(int idx, int dir) {
		// 회전하기 전에 인접한 값들을 검사한다.
		// 닿고 있는 부분이 회전이 필요한지 검사한다.

		// 3을 검사해야하니 -1 과 +1을 검사한다
		// 근데 이 값들이 0 <= <n 이면 실행
		// 중요 : 이미 검사한 값들을 다시 검사하지 않아야한다.
		// 이미 검사한 경우
		if (idx <= 0 || idx == 5) {
			return;
		}

		int od = dir == 1 ? -1 : 1;
		if (idx - 1 > 0 && !v[idx - 1] && checkRotation(idx, idx - 1)) {
			v[idx - 1] = true;
			rotate(idx - 1, od);
		}
		if (idx + 1 < 5 && !v[idx + 1] && checkRotation(idx, idx + 1)) {
			v[idx + 1] = true;
			rotate(idx + 1, od);
		}

		// 현재 방향에 따라 방향을 바꾸는 코드 실행
		changeGear(idx, dir);
		

	}

	private static void changeGear(int idx, int dir) {
		if (dir == 1) { // 시계방향
			int last = gears[idx][7];

			for (int i = 7; i > 0; i--) {
				gears[idx][i] = gears[idx][i - 1];
			}
			gears[idx][0] = last;

		} else { // 반시계 방향
			int first = gears[idx][0];
			for (int i = 0; i < 7; i++) {
				gears[idx][i] = gears[idx][i + 1];
			}
			gears[idx][7] = first;
		}

	}

	// 2 6
	private static boolean checkRotation(int idx, int idx2) {
		if (idx - 1 == idx2) { // 아래 값을 검사했을 경우
			if (gears[idx][6] != gears[idx2][2]) {
				return true;
			}
		} else { // 큰 값 검사했을 경우
			if (gears[idx][2] != gears[idx2][6]) {
				return true;
			}
		}
		return false;
	}

}
