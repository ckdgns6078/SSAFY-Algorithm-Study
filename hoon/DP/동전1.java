import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int size = sc.nextInt();
		int n = sc.nextInt();
		int[] arr = new int[size+1];
		int[][] dp = new int[size+1][n+1];
		
		for(int i = 1 ; i < size+1 ; i++) {
			arr[i] = sc.nextInt();
		}
		
		//0을 만드는 방법은 아무것도 사용하지 않는 방법으로 가능함으로 1로 설정한다.
		for(int i = 0 ; i < size+1 ; i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 1 ; i < size+1 ; i++) {	
			for(int j = 1 ; j < n+1 ; j++) {
				
				if(j < arr[i]) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j]=dp[i-1][j] + dp[i][j-arr[i]];					
				}
				
				
			}
			
		}
		

		
		System.out.println(dp[size][n]);
		
		
	}

}
