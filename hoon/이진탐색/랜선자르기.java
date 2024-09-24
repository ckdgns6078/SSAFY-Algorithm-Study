import java.util.*;

public class Main {
	static int n;
	static int k;
	static long[] arr;
	static long max = Integer.MIN_VALUE;
	static long min = Integer.MAX_VALUE;
	static long result = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		k = sc.nextInt();
		arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}

		//최소값 1부터 최대값까지 
		parametric(1 , max);
		System.out.println(result);
		
	}
	private static void parametric(long left, long right) {
		if(left > right) {
			return;
		}
		
		int cnt = 0;
		long center = (left + right)/ 2;
		
		
		
		for(int i = 0 ; i < n ; i++) {
			cnt += arr[i] / center;
		}
		if(cnt>=k) {
			result = Math.max(result, center);
			parametric(center+1 , right);
		}else {
			parametric(left , center-1);
		}
		
		
		
	}

}
