package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 특이한자석 {
	static int K, result;
	static int[][] arr;
	static int[] rotateArr;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			K = Integer.parseInt(br.readLine());
			arr = new int[4][8];
			result = 0;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			for (int i = 0; i < 4; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken())-1;
				int forward = Integer.parseInt(st.nextToken());
//				System.out.println(idx + " " + forward);
				// 2 : 오른쪽 방향, 6 : 왼쪽 방향
				rotateArr = new int[4];
				v = new boolean[4];
				start(idx, forward);
//				for (int i = 0; i < 4; i++) {
//					System.out.println(Arrays.toString(arr[i]));
//				}
//				System.out.println(Arrays.toString(rotateArr));
				rotate();
			}
			int a = 1;
			for (int i = 0; i < 4; i++) {
				if(arr[i][0] == 1) {
					result+=a;
				}
				a*=2;
			}
//			System.out.println(result);
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void rotate() {
		for (int i = 0; i < 4; i++) {
			if(rotateArr[i] == 1) { // 시계방향
				int temp = arr[i][7];
				for (int j = 7; j > 0; j--) {
					arr[i][j] = arr[i][j-1];
				}
				arr[i][0] = temp;
				
			}else if(rotateArr[i] == -1) { // 반시계방향
				int temp = arr[i][0];
				for (int j = 0; j < 7; j++) {
					arr[i][j] = arr[i][j+1];
				}
				arr[i][7] = temp;
			}
		}
	}

	private static void start(int idx, int forward) {
		// TODO Auto-generated method stub
//		System.out.println(idx);
		rotateArr[idx] = forward;
		v[idx] = true;
		if(forward == 1) {
			forward=-1;
		}else {
			forward=1;
		}
		
		if(idx+1 < 4 && arr[idx][2] != arr[idx+1][6] && !v[idx+1]) {
			start(idx+1, forward);
		}
		if(idx-1 >= 0 && arr[idx][6] != arr[idx-1][2] && !v[idx-1]) {
			start(idx-1, forward);
		}
		
	}
}


//10
//2
//0 0 1 0 0 1 0 0
//1 0 0 1 1 1 0 1
//0 0 1 0 1 1 0 0
//0 0 1 0 1 1 0 1
//1 1
//3 -1
