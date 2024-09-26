import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
        }

        int[] dp = new int[k + 1];
        dp[0] = 1; // 0원을 만드는 경우는 1가지 (아무 동전도 사용하지 않는 경우)

        // 동전의 종류마다 경우의 수를 갱신
        for (int i = 1; i <= n; i++) {
            for (int j = v[i]; j <= k; j++) {
                dp[j] += dp[j - v[i]];
            }
        }

        System.out.println(dp[k]);

        sc.close();
    }
}
