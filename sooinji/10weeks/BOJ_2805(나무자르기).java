import java.util.*;

public class Main {
	static int n, m;
	static int[] tree;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		tree = new int[n];
		for (int i = 0; i < n; i++) {
			tree[i] = sc.nextInt();
		}
		Arrays.sort(tree);
		long st = 0;
		long ed = tree[n - 1];
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
			if (mid < tree[i]) sum += tree[i] - mid;
		}
		if (sum >= m) return true;
		else return false;
	}
}
