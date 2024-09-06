import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		System.out.println(dp(N));
	}
	
	static int dp(int N) {
		if(N == 1) return 0;
		if(N <= 3) return 1;
		
		int[] dp = new int[N+1];
		dp[2] = 1;
		dp[3] = 1;
		
		for(int i = 4; i<=N;i++) {
			if(i%6==0) dp[i] = Math.min(Math.min(dp[i/2], dp[i/3]), dp[i-1])+1;
			else if(i%2 == 0) dp[i] = Math.min(dp[i/2], dp[i-1])+1;
			else if(i%3 == 0) dp[i] =Math.min(dp[i/3], dp[i-1])+1;
			else dp[i] = dp[i-1] + 1;
		}
		return dp[N];
	}

}
