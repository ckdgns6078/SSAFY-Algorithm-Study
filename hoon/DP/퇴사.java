import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int result = 0;
		int anoter = 0;
		ArrayList<int[]>[] list = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList();
		}

		int[] dp = new int[1000];

		for (int i = 1; i < n + 1; i++) {
			int t = sc.nextInt();
			int p = sc.nextInt();
			if (i + t - 1 <= n) {
				list[i + t - 1].add(new int[] { i, p });
			}
		}

		for (int i = 1; i < n + 1; i++) {

			int size = list[i].size();
			if (size > 0) {
				for (int j = 0; j < list[i].size(); j++) {
					int idx = list[i].get(j)[0] - 1;
					int pay = list[i].get(j)[1];
					// 이전에 완료한 값 + 지금의 pay
					
					dp[i] = Math.max(dp[i], dp[idx] + pay);
				}
			}
			dp[i] = Math.max(dp[i], dp[i-1]);
			
//			System.out.println("dp [" + i + "] : " + dp[i]);
			result = Math.max(result, dp[i]);
		}

		System.out.print(result);
	}

}
