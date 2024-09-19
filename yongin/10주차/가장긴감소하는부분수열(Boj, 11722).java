import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[3000];
		Arrays.fill(dp, 1);
//		System.out.println(Arrays.toString(arr));
		int maxValue = Integer.MIN_VALUE;
		for (int i = 2; i < N+1; i++) {
			for (int j = 1; j < i; j++) {
				if(arr[i] < arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		for (int i = 1; i < N+1; i++) {
			maxValue = Math.max(maxValue, dp[i]);
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(maxValue);
	}
}
