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
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			int c = coin[i];
			for (int j = c; j <= k; j++) {
				dp[j] = dp[j] + dp[j - c];
			}
		}
		System.out.println(dp[k]);
	}
}
