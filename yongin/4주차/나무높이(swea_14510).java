package java_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무높이_14510 {
	/*
	 * 하루에 한 나무에 물을 줄 수 있다.
	 * 
	 * 가장 첫 줄에는 테스트 케이스의 총 수가 주어진다. 
	 * 그 다음 줄부터 각 테스트 케이스가 주어지며, 
	 * 각 테스트 케이스는 2줄로 구성된다. 
	 * 각 테스트 케이스의 첫째 줄에는 나무의 개수 N이 주어진다. 
	 * 다음 줄에는 나무들의 높이가 N개의 자연수로 주어진다.
	 */
	static int[] arr;
	static int minNumber, maxNumber, result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N];
			result = 0;
			minNumber = Integer.MAX_VALUE;
			maxNumber = Integer.MIN_VALUE;
			boolean isSameValue = true;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				maxNumber = Math.max(maxNumber, arr[i]);
			}
			
			for (int i = 0; i < arr.length; i++) {
				if(arr[0] != arr[i]) isSameValue=false;
			}
			
			if(isSameValue) {
				System.out.println("#"+t+" "+result);
				continue;
			}
			
			// 짝수, 홀수, maxNumber와 해당 값의 차
			int even=0, odd=0, gap=0;
			for (int i = 0; i < N; i++) {
				if(arr[i] == maxNumber) continue;
				gap = maxNumber - arr[i];
				even+=gap/2;
				odd+=gap%2;
			}
			
			/*
			 * 2의 갯수(even) = 1의 갯수(odd)가 같으면 even + odd
			 * 2의 갯수(even) < 1의 갯수(odd)이면, 2*odd-1
			 * 2의 갯수(even) > 1의 갯수(odd)이면, 2*even (even이 odd보다 1 많은 경우)
			 * 2의 갯수(even) > 1의 갯수(odd)인데, even이 odd보다 2 이상 많은 경우
			 */
			if(even > odd && (even-odd) >= 2) {
				while(even-odd > 1) {
					even--;
					odd+=2;
				}
			}
			
			if(even == odd) {
				result = even + odd;
			}else if(even < odd) {
				result = 2*odd-1;
			}else if(even > odd) {
				result = 2*even;
			}
			
			
			
//			// 홀수 번째 1, 짝수 번째 2
//			int day = 1;
//			int cnt = 0;
//			
//			for (int i = 0; i < arr.length; i++) {
//				if(maxNumber == arr[i]) {
//					arr[i] = -1;
//					cnt++;
//				}
//			}
//			// 해당 위치의 값과 maxNumber의 차이가 1이 나는데, 2를 넣어야하면 보류
//			// 해당 위치의 값과 maxNumber의 차이가 2가 나는데, 1을 넣어야하면 보류
//			// 그렇지않으면 넣는다.
//			// 해당 위치의 값이 maxNumber와 동일해지면 -1 처리
//			while(cnt!=arr.length) {
//				if(day%2==1) {
//					for (int i = 0; i < arr.length; i++) {
//						if(arr[i]+2 == maxNumber) continue;
//						if(arr[i]==-1) continue;
//						arr[i]+=1;
//						if(arr[i]==maxNumber) {
//							arr[i] = -1; 
//							cnt++;
//						}
//						break;
//					}
//					
//				}else {
//					for (int i = 0; i < arr.length; i++) {
//						if(arr[i]+1 == maxNumber) continue;
//						if(arr[i]==-1) continue;
//						arr[i]+=2;
//						if(arr[i]==maxNumber) {
//							arr[i] = -1;
//							cnt++;
//						}
//						break;
//					}
//				}
//				day++;
//			}

			System.out.println("#"+t+" "+result);
		}
	}
}
