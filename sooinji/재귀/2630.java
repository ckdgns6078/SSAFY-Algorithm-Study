import java.util.*;

public class Main {
	static int[] cnt = new int[2]; // 하양, 파랑
	static int maxVal = 128;
	static int[][] arr = new int[maxVal][maxVal];
	
	static void func(int a, int b, int s) {
		if (s <= 1) {
			cnt[arr[a][b]]++;
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
			cnt[arr[a][b]]++;
			return;
		}
		else {
			s /= 2;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					func(a + s*i, b + s*j, s);
				}
			}
		}
		return;
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
		for (int i = 0; i < cnt.length; i++) {
			System.out.println(cnt[i]);
		}
	}

}
