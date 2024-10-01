import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] num = new int[N];
		
		for(int i = 0; i < N; i++)
			num[i] = sc.nextInt();
		
		Arrays.sort(num);
		int s = 0;
		int e = N-1;
		
		int[] ans = {0,0};
		int sum = Integer.MAX_VALUE;
		
		while(s<e) {
			if(Math.abs(num[s]+num[e]) < sum) {
				ans[0] = Math.min(num[s], num[e]);
				ans[1] = Math.max(num[s], num[e]);
				sum = Math.abs(num[s]+num[e]);
			}
			
			if(Math.abs(num[e]+num[s+1]) < Math.abs(num[s]+num[e-1])) {
				s++;
			}else {
				e--;
			}
			
		}
		
		System.out.println(ans[0]+" "+ans[1]);
	}

}
