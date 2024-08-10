import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Swe14510 {
	// 나무의 높이
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			
			int max = Integer.MIN_VALUE;
			int n = sc.nextInt();
			int[] tree = new int[n];
			for (int i = 0; i < n; i++) {
				tree[i] = sc.nextInt();
				max = Math.max(max, tree[i]);
			}
			
			int result = 0;
			int cnt = 1;
		

			//값을 계속해서 계속해서 검사하는데 더할 2를 넣으면 바로 처리가 될때는 더하지말고 나머지를 계산해서 더한다.
			while(true) {
				Arrays.sort(tree);
				
				if(tree[0]==max) {
					break;
				}
//				System.out.println(Arrays.toString(tree));
				
				int plus = cnt %2 ==0 ? 2 : 1;
				boolean check = true;
				boolean pCheck = true;
				for( int i = n-2 ; i >=0 ; i--) {
					if(tree[i]+plus==max) {
						tree[i]+=plus;
						check=false;
						break;
					}
				}
				
				if( plus==1) {
					if(tree[0]+2 == max) {
						pCheck= false;
					}
				}			
				
				if(pCheck && check && tree[0]+plus<=max) {
					tree[0]+=plus;
				}
	
				cnt++;
				result++;
			}
			
			System.out.println("#" + tc + " " + result);
		}

	}

}
