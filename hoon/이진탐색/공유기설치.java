import java.util.*;

public class Main {

	static int n;
	static int c;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		c = sc.nextInt();
		arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int left = 1;
		int right = arr[n-1] - arr[0] + 1;
		
		while(left < right) {
			int center = (left + right) / 2;
			
			if(check(center) < c) {
				right = center;
			}else {
				left = center +1;
			}
		}
		
		System.out.println(left-1);
	}
	
	//집의 거리를 검사하는 함수
	public static int check(int k) {
		
		// 처음 집 검사
		int cnt = 1;
		int lastHome = arr[0];
		
		for(int i = 1 ; i < arr.length ; i++) {
			int locate = arr[i];
			
			if( locate - lastHome >= k) {
				cnt++;
				lastHome = locate;
			}
		}
		return cnt;
	}
}
