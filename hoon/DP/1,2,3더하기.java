import java.util.*;
public class Main {
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 
		int T = sc.nextInt();
		dp = new int[12];
		
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		dp[4]=7;
		for(int i = 5; i < 12;i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		for(int tc = 0 ; tc<T ; tc++) {
			int input = sc.nextInt();
			System.out.println(dp[input]);
			
		}
		
	}

}
 
