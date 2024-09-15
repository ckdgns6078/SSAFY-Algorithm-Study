import java.util.*;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n =  sc.nextInt();
		int[] arr = new int[n+1];
		int[] dp = new int[2000];
		int result = 1;
		for(int i = 1 ; i < n+1 ; i++) {
			arr[i] = sc.nextInt();
		}
		
		dp[1] = 1;
		
		for(int i = 2 ; i < n+1 ; i++) {
			int cnt = 0;
			for( int j = 1  ; j < i ; j++) {
				if(arr[j]>arr[i]) {
					cnt = Math.max(cnt, dp[j]);
				}
			}
			dp[i] = cnt==0 ? 1 : cnt+1;
			result = Math.max(result, dp[i]);
		}
		
		System.out.println(result);
		
	}

}
