import java.util.*;
public class Main {
	static int n, c;
	static int[] x;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		c = sc.nextInt();
		x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = sc.nextInt();
		}
		Arrays.sort(x);
		long st = 0;
		long ed = x[n - 1] - x[0];
		while (st < ed) {
			long mid = (st + ed + 1) / 2;
			if (solve(mid)) st = mid;
			else ed = mid - 1;
		}
		System.out.println(st);
	}
	private static boolean solve(long mid) {
		int cnt = 1;
		int loc = x[0];
		for (int i = 0; i < n; i++) {
			if (x[i] - loc >= mid) {
				cnt++;
				loc = x[i];
			}
		}
		if (cnt >= c) return true;
		else return false;
	}
}
