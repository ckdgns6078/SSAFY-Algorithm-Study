
import java.util.LinkedList;
import java.util.Scanner;

public class TreeHeight {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		

		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int odd_day = 0;
			int even_day = 0;
			int max = Integer.MIN_VALUE;

			int[] num = new int[N];

			for(int i = 0; i < N; i++) {
				int val = sc.nextInt();
				
				max = Math.max(max, val);
				
				num[i] = val;
			}

			for(int i = 0; i<N;i++){
				int diff = max - num[i];

				even_day += diff/2;			// 필요한 짝수일
				odd_day += diff%2;			// 필요한 홀수일
			}

			int ans = 0;

			if(even_day == odd_day){
				ans = even_day + odd_day;
			} else if (even_day < odd_day) {
				ans = even_day*2+2*(odd_day-even_day)-1;
			}
			else if(even_day> odd_day){
				ans = odd_day*2 + (even_day-odd_day) + (even_day-odd_day-1)/3 + 1;
			}

			
			System.out.println("#"+t+" "+ans);
			
			
		}
	}

}
