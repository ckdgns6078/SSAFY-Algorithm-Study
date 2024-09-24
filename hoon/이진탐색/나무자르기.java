import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		long result = 0;
		int left = 1;
		int right = 0;
//		2,147,483,647
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			right = Math.max(right, arr[i]);
		}

		while (left <= right) {
			int center = left + (right - left)/ 2;
			long cnt = 0;

			for (int i = 0; i < n; i++) {
				if(arr[i]>center) {
					cnt+= arr[i] - center;
				}
				
			}
			if( cnt>=m) { //더 클 경우 center를 더 크게
				left = center +1;
				result = Math.max(result, center);
			}else {		  //작을 경우 center를 작게
				right = center-1;
			}
			
		}
		
		System.out.println(result);

	}

}
