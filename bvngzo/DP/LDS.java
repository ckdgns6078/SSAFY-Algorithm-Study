import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			dp[i] = 1;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i ; j++) {
				if(arr[j] > arr[i]) {
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		//System.out.println(Arrays.toString(dp));
		System.out.println(max);
		
	}

}
