import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);
		int start=0;
		int end=n-1;
		
		int rs = 0;
		int re = 0;
		int min = Integer.MAX_VALUE;
		
		while(start < end) {
			int sum = arr[start]+ arr[end];
			
			if(min >Math.abs(sum)) {
				min = Math.abs(sum);
				
				rs = arr[start];
				re = arr[end];
			}
			
			if(sum < 0) {
				start++;
			}else {
				end--;
			}
			
		}
		
		
		System.out.println(rs + " " + re);
	}

}
