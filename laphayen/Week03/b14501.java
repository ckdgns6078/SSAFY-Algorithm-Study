import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] TP = new int[N][2];
        for (int i = 0; i < N; i++) {
            TP[i][0] = sc.nextInt();
            TP[i][1] = sc.nextInt();
        }

        int[] dp = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            if (i + TP[i][0] > N) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], dp[i + TP[i][0]] + TP[i][1]);
            }
        }

        System.out.println(dp[0]);
        sc.close();
    }
}
