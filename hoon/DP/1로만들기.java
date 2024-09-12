import java.util.*;

public class Main {

	// 메모이제이션을 위한 Map
	private static Map<Integer, Integer> memo = new HashMap<>();
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = 10;
		dp = new int[(int)Math.pow(10, 6) +1];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		int x = sc.nextInt();

		for(int i = 4 ; i <=x ; i++) {
			int cnt =Integer.MAX_VALUE;
			if( i % 3 == 0) {
				cnt = Math.min(cnt, dp[i/3]+1);
			}
			if( i % 2 == 0) {
				cnt = Math.min(cnt, dp[i/2]+1);
			}
			cnt = Math.min(cnt, dp[i-1]+1);
			dp[i] = cnt;
		}
		System.out.println(dp[x]);
		
	}

}
