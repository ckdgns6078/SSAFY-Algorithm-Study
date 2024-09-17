import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stair = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            stair[i] = sc.nextInt();
        }

        dp[0] = stair[0];
        if (n > 1) {
            dp[1] = Math.max(stair[0] + stair[1], stair[1]);
        }
        if (n > 2) {
            dp[2] = Math.max(stair[0] + stair[2], stair[1] + stair[2]);
        }

        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + stair[i], stair[i - 1] + stair[i] + dp[i - 3]);
        }

        System.out.println(dp[n - 1]);
        sc.close();
    }
}
