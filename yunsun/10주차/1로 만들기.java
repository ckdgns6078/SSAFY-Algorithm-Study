import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// Top-down으로 했는데 안돼서 Bottom-up 방식으로 도전 
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N + 1];
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		
		while(!queue.isEmpty()) {
			if (dp[N] != 0) {
				break;
			}
			
			int num = queue.poll();
			int operations = dp[num];
			
			if (num * 2 <= N && dp[num * 2] == 0) {
				dp[num * 2] = operations + 1;
				queue.add(num * 2);
			}
			if (num * 3 <= N && dp[num * 3] == 0) {
				dp[num * 3] = operations + 1;
				queue.add(num * 3);
			}
			if (num + 1 <= N && dp[num + 1] == 0) {
				dp[num + 1] = operations + 1;
				queue.add(num + 1);
			}
		}
		
		System.out.println(dp[N]);
		
		sc.close();
	}
}
