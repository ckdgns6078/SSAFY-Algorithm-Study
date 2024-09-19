import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[][] dp = new int[N+1][3];
		for (int i = 1; i < N+1; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
//		System.out.println(Arrays.toString(arr));
		for (int i = 1; i < N+1; i++) {
//			System.out.println(i);
			dp[i][0] = Math.max(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = dp[i-1][0] + arr[i];
			dp[i][2] = dp[i-1][1] + arr[i];
		}
//		System.out.println(Arrays.toString(arr));
//		for(int[] a : dp) {
//			System.out.println(Arrays.toString(a));
//		}
		System.out.println(Math.max(dp[N][1], dp[N][2]));
		
		
		
	}
}

//6
//10
//20
//15
//25
//10
//20
