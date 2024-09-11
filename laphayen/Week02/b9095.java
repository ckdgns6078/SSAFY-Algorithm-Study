import java.util.Scanner;

public class Main {
	static int[] dp = new int[11];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			int n = scan.nextInt();
			System.out.println(func(n));
		}
	}

	public static int func(int n) {
		if (n < 0) {
            return 0;
        }
			
		if (n == 0 || n == 1) {
            return 1;
        }
		if (dp[n] == 0) {
            dp[n] = func(n - 1) + func(n - 2) + func(n - 3);
        }
		return dp[n];
	}

}
