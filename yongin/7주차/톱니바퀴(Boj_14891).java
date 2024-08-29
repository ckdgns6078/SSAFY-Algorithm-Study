import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int[][] arr;
	
	static void Clock(int idx, int forward) {
		// idx = 0 or 1 or 2 or 3
		if(forward==1) {
			int temp = arr[idx][7];
			for (int i = 6; i >= 0; i--) {
				arr[idx][i+1] = arr[idx][i];
			}
			arr[idx][0] = temp;
		}else if(forward==-1) {
			int temp = arr[idx][0];
			for (int i = 0; i < 7; i++) {
				arr[idx][i] = arr[idx][i+1];
			}
			arr[idx][7] = temp;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 배열 초기화 및 데이터 넣기, N극:0, S극:1
		arr = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		// 회전 횟수 데이터 넣기
		K = Integer.parseInt(br.readLine());
		// 회전 방법 초기화 및 데이터 넣기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken())-1;
			int forward = Integer.parseInt(st.nextToken());
			
			int reforward = 1;
			if(forward==1) reforward=-1;
			
			
			
			// 각 col의 2번째가 오른쪽, 6번째가 왼쪽
			if(idx==0) {
				// 1, 2, 3, 4 번째 톱니바퀴의 왼쪽 오른쪽 모두 같은 경우
				if(arr[idx][2] != arr[idx+1][6] && arr[idx+1][2]!=arr[idx+2][6] && arr[idx+2][2]!=arr[idx+3][6]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
					Clock(idx+2, forward);
					Clock(idx+3, reforward);
				}else if(arr[idx][2] != arr[idx+1][6] && arr[idx+1][2]!=arr[idx+2][6]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
					Clock(idx+2, forward);
				}else if(arr[idx][2] != arr[idx+1][6]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
				}else {
					Clock(idx, forward);
				}
			}else if(idx==3) {
				if(arr[idx][6] != arr[idx-1][2] && arr[idx-1][6]!=arr[idx-2][2] && arr[idx-2][6]!=arr[idx-3][2]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
					Clock(idx-2, forward);
					Clock(idx-3, reforward);
				}else if(arr[idx][6] != arr[idx-1][2] && arr[idx-1][6]!=arr[idx-2][2]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
					Clock(idx-2, forward);
				}else if(arr[idx][6] != arr[idx-1][2]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
				}else {
					Clock(idx, forward);
				}
			}else if(idx==1) {
				if(arr[idx][6]!=arr[idx-1][2] && arr[idx][2]!=arr[idx+1][6] && arr[idx+1][2]!=arr[idx+2][6]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
					Clock(idx+1, reforward);
					Clock(idx+2, forward);
				}else if(arr[idx][6]!=arr[idx-1][2] && arr[idx][2]!=arr[idx+1][6]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
					Clock(idx+1, reforward);
				}else if(arr[idx][2]!=arr[idx+1][6] && arr[idx+1][2]!=arr[idx+2][6]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
					Clock(idx+2, forward);
				}else if(arr[idx][6]!=arr[idx-1][2]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
				}else if(arr[idx][2]!=arr[idx+1][6]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
				}else {
					Clock(idx, forward);
				}
			}else if(idx==2) {
				if(arr[idx][2]!=arr[idx+1][6] && arr[idx][6]!=arr[idx-1][2] && arr[idx-1][6]!=arr[idx-2][2]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
					Clock(idx-1, reforward);
					Clock(idx-2, forward);
				}else if(arr[idx][2]!=arr[idx+1][6] && arr[idx][6]!=arr[idx-1][2]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
					Clock(idx-1, reforward);
				}else if(arr[idx][6]!=arr[idx-1][2] && arr[idx-1][6]!=arr[idx-2][2]){
					Clock(idx, forward);
					Clock(idx-1, reforward);
					Clock(idx-2, forward);
				}else if(arr[idx][2]!=arr[idx+1][6]) {
					Clock(idx, forward);
					Clock(idx+1, reforward);
				}else if(arr[idx][6]!=arr[idx-1][2]) {
					Clock(idx, forward);
					Clock(idx-1, reforward);
				}else {
					Clock(idx, forward);
				}
			}
		}
		int sum = 0;
		int value = 1;
		for (int i = 0; i < 4; i++) {
			if(arr[i][0]==1) {
				sum+=value;
			}
			value*=2;
		}
		System.out.println(sum);

		
	}

}
