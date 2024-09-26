import java.util.Scanner;

// 난 이 문제가...너무 어려웠어...........
// 결국 못 풀고 솔루션 봤다.... 다시 풀자....ㅠㅠㅠ
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coinValues = new int[n];
		int[] dp = new int[k + 1];;
		
		for (int i = 0; i < n; i++) {
			coinValues[i] = sc.nextInt();
		}
		
		dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coinValues[i]; j <= k; j++) {
                dp[j] += dp[j - coinValues[i]];
            }
        }
		System.out.println(dp[k]);
		sc.close();
	}

}
