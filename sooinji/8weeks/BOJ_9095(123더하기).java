import java.util.*;

public class Main {
	static int t, n;
	static int[] d;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			n = sc.nextInt();
			if (n > 3) d = new int[n + 1];
			else d = new int[4];
			d[1] = 1; // 1
			d[2] = 2; // 2 1+1
			d[3] = 4; // 3 1+2 2+1 1+1+1
			for (int i = 4; i <= n; i++) {
				d[i] = d[i - 1] + d[i - 2] + d[i - 3];
			}
			System.out.println(d[n]);
		}
	}
}
