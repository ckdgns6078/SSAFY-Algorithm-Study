import java.util.*;

public class Main {
	static int n;
	static int[] stairs;
	static int[][] d;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		stairs = new int[301];
		d = new int[301][3];
		for (int i = 1; i <= n; i++) {
			stairs[i] = sc.nextInt();
		}
		// d[i][j]는 i번째 계단을 j번 연속으로 밟음 
		d[1][1] = stairs[1];
		d[2][1] = stairs[2];
		d[2][2] = stairs[1] + stairs[2];
		for (int i = 3; i <= n; i++) {
			d[i][1] = Math.max(d[i - 2][1], d[i - 2][2]) + stairs[i];
			d[i][2] = d[i - 1][1] + stairs[i];
		}
		System.out.println(Math.max(d[n][1], d[n][2]));
	}
}
