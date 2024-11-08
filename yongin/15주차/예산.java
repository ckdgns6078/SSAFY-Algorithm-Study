package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 예산 {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int sum = 0;
		int high = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
			high = Math.max(high, arr[i]);
		}
		
		int max = Integer.parseInt(br.readLine()); // 총 예산
		
		if(sum <= max) {
			System.out.println(high);
			return;
		}
		
		int low = 1;
		
		while(low < high-1) {
			int mid = (low+high)/2; // 상한선
			
			if(cal(mid) > max) { // 상한선 총합이 max보다 크면
				high = mid; // 상한선을 낮춰야됨
			}else {
				low = mid;
			}
		}
		
		System.out.println(low);
	}

	private static int cal(int high) {
		// TODO Auto-generated method stub
		int total = 0;
		for (int i = 0; i < N; i++) {
			total += Math.min(arr[i], high);
		}
		return total;
	}
}
