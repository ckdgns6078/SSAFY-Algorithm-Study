import java.util.*;

class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	
	static int cnt = 0;
	static int arr[][] = new int[9][9];
	static Point p[] = new Point[81];
	static boolean num[][][] = new boolean[10][10][10];
	// 가로줄: [0][1][숫자], 세로줄: [1][0][숫자], 정사각형: [1][1][숫자]
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] != 0) {
					num[0][j + 1][arr[i][j]] = true;
					num[i + 1][0][arr[i][j]] = true;
					num[i/3 + 1][j/3 + 1][arr[i][j]] = true;
				}
				else {
					p[cnt++] = new Point(i, j);
				}
			}
		}
		func(0);
	}
	
	private static void func(int idx) {
//		System.out.println("#" + idx);
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		// basis part
		if (idx == cnt) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
			return;
		}
		// indective part
		int cx = p[idx].x;
		int cy = p[idx].y;
		for (int i = 1; i < 10; i++) {
			arr[cx][cy] = i;
			// 조건에 맞으면
			if (num[0][cy + 1][arr[cx][cy]] || num[cx + 1][0][arr[cx][cy]] || num[cx/3 + 1][cy/3 + 1][arr[cx][cy]]) continue;
			num[0][cy + 1][arr[cx][cy]] = true;
			num[cx + 1][0][arr[cx][cy]] = true;
			num[cx/3 + 1][cy/3 + 1][arr[cx][cy]] = true;
			func(idx + 1);
			num[0][cy + 1][arr[cx][cy]] = false;
			num[cx + 1][0][arr[cx][cy]] = false;
			num[cx/3 + 1][cy/3 + 1][arr[cx][cy]] = false;
		}
		return;
	}
}

