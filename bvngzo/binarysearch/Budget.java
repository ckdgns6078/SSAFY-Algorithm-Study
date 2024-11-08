import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long sum = 0;
		int max = Integer.MIN_VALUE;
		int[] budgets = new int[N];
		
		for(int i = 0; i < N; i++) {
			int budget = sc.nextInt();
			sum += budget;
			max = Math.max(max, budget);
			budgets[i] = budget;
		}
		
		long total = sc.nextLong();
		
		int ans = 0;
		
		if(sum <= total) {
			ans = max;
		}else {
			int left = 1;
			int right = 1000000000;
			
			while(left <= right) {
				sum = 0;
				int mid = (left + right)/2;
				for(int i = 0; i < N; i++) {
					sum += (budgets[i] < mid) ? budgets[i] : mid;
				}
				if(sum > total) {
					right = mid-1;
				}else {
					left = mid+1;
					ans = Math.max(ans, mid);
				}
			}
		}
		
		System.out.println(ans);
	}

}
