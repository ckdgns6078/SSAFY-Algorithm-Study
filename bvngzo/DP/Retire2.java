import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 기간
			arr[i][1] = Integer.parseInt(st.nextToken()); // 수익
		}

		int[] dp = new int[n + 1];
		int[] future = new int[n + 1];

		if (arr[1][0] == 1)
			dp[1] = arr[1][1];
		else {
			if(arr[1][0] <= n) 
				future[arr[1][0]] = arr[1][1];
		}

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1];
			int f = i + arr[i][0] - 1;
			if (f <= n)
				future[f] = Math.max(future[f], dp[i] + arr[i][1]);

			dp[i] = Math.max(dp[i], future[i]);
		}
		System.out.println(dp[n]);

	}


}
