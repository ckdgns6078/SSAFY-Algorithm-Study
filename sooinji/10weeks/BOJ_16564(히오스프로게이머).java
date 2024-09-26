import java.util.*;

public class Main {
	static int n, k;
	static int[] x;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = sc.nextInt();
		}
		Arrays.sort(x);
		long st = 0; 
		long ed = x[n-1] + k;
		while (st < ed) {
			long mid = (st + ed + 1) / 2;
			if (solve(mid)) st = mid;
			else ed = mid - 1;
		}
		System.out.println(st);
	}
	private static boolean solve(long mid) {
		long sum = 0;
		for (int i = 0; i < n; i++) {
			if (mid <= x[i]) break;
			sum += mid - x[i];
		}
		if (sum <= k) return true;
		else return false;
	}
}
