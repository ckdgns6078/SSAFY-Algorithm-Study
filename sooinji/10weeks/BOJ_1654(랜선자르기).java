import java.util.*;

public class Main {
	static int n, k;
	static int[] line;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		line = new int[n];
		for (int i = 0; i < n; i++) {
			line[i] = sc.nextInt();
		}
		long st = 0;
		long ed = (long) (Math.pow(2, 31) - 1);
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
			sum += line[i] / mid;
		}
		if (sum >= k) return true;
		else return false;
	}
}
