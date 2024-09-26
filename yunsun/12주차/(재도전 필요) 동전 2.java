import java.util.Scanner;

// 너무......어렵다...............
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coinValues = new int[k+1];
		int[] dp = new int[k+1];
		
		for (int i = 0; i < n; i++) {
			coinValues[i] = sc.nextInt();
		}
		
		// 배열 최대값으로 초기화
		for (int i = 1; i <= k; i++) {
			dp[i] = Integer.MAX_VALUE - 1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = coinValues[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j-coinValues[i]] + 1);
			}
		}
		
        if(dp[k]==Integer.MAX_VALUE-1)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
        
		sc.close();
	}
}
