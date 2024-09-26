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
                /*
                dp 테이블에 저장할 때 우선 1원으로 만들 수 있는 경우 수들을 모두 저장해놓고 
                2원으로 만들 수 있는 경우의 수 dp 테이블에는 만약 2원으로 4원을 만드는 경우의 수를 구한다면
                2원을 하나 사용한 수 + 1원으로 2원을 만드는 경우의 수를 더한 값일 것이다.
2원으로 4원을 만드는 경우의 수를 구할 차례일 경우 dp[j] = dp[j] + dp[j - coins[i]]; 이 부분이 i = 1이고 j = 4이다.
*/
                dp[j] += dp[j - v[i]];
            }
        }

        System.out.println(dp[k]);

        sc.close();
    }
}
