import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		
		int[] lans = new int[K];
		
		long max = Integer.MIN_VALUE;
		
		for(int i = 0; i < K; i++) {
			lans[i] = sc.nextInt();
			max = Math.max(max, lans[i]);
		}
		
		long cnt = 0;
		long min = 1;
		long mid = (max+min)/2; 
		long ans = Integer.MIN_VALUE;
		while(min <= max) {
			cnt = 0;
			for(int i = 0; i < K; i++) {
				cnt += lans[i] / mid;
			}
			if(cnt < N) {
				max = mid-1;
			}
			else {
				ans = Math.max(mid, ans);
				min = mid+1;
			}
			mid = (max+min)/2; 
		}
		
		System.out.println(ans);

	}

}
