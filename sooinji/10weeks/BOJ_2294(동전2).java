import java.util.*;

public class Main {
	static int n, k;
	static int[] coin;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		coin = new int[n];
		dp = new int[k + 1];
		for (int i = 0; i < n; i++) {
			coin[i] = sc.nextInt();
		}
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			int c = coin[i];
			for (int j = c; j <= k; j++) {
				if (dp[j - c] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j - c] + 1);
				}
			}
		}
		if (dp[k] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dp[k]);
	}
}
