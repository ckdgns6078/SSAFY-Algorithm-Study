import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		long left = 1;
		long right = arr[0]+1000000000;
		long result = 0;
		while(left <=right) {
			long center  = (left + right)/2;
			long sum = 0;
			for(int i = 0 ; i < N ; i++) {
				if(center > arr[i]) {
					sum += center - arr[i];
				}
			}
			
			if(sum <=K) {
				left = center+1;
				result = center;			
			}else {
				right = center-1;
			}
			
		}
		System.out.println(result);
	}

}
