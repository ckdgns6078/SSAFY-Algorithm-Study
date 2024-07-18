import java.util.*;

public class Main {
	static int[] cnt = new int[3];
	static int maxVal = 3*3*3*3*3*3*3;
	static int[][] arr = new int[maxVal][maxVal];
	
	public static void func(int a, int b, int s) {
		if (s <= 1) {
			cnt[arr[a][b] + 1]++;
			return;
		}
		boolean val = true;
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				if (arr[a][b] != arr[a + i][b + j]) {
					val = false;
					break;
				}
			}
		}
		if (val) {
			cnt[arr[a][b] + 1]++;
			return;
		}
		else {
			s = s / 3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					func(a + i*s, b + j*s, s);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		func(0,0,n);
		for(int i = 0; i < 3; i++) {
			System.out.println(cnt[i]);
		}
	}
}
