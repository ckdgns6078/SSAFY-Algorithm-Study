import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		long M = sc.nextLong();
		
		long[] trees = new long[N];
		
		long max = 0;
		for(int i = 0; i < N; i++) {
			trees[i] = sc.nextLong();
			max = Math.max(max, trees[i]);
		}
		
		long start = 0;
		long end = max;
		long mid =(start+end)/2;
		long ans = -1;
		while(start <= end) {
			long sum = 0;
			for(int i = 0; i < N; i++) {
				sum += (trees[i]>mid) ? (trees[i]-mid) : 0;
			}
			if(sum >= M) {
				start = mid + 1;
				ans = Math.max(ans, mid);
			}else {
				end = mid-1;
			}
			mid = (start+end)/2;
		}
		System.out.println(ans);
	}

}
