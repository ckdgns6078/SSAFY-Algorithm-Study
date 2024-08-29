import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int N, K;
	static boolean[] rotate;
	
	static void clock(int number, int forward) {
		
		if(forward == 1) {
			int temp = arr[number][7];
			for (int i = 6; i >= 0; i--) {
				arr[number][i+1] = arr[number][i];
			}
			arr[number][0] = temp;
		}else if(forward == -1) {
			int temp = arr[number][0];
			for (int i = 1; i < 8; i++) {
				arr[number][i-1] = arr[number][i];
			}
			arr[number][7] = temp;
		}
		
	}
	
	
	
	static void rotate(int number, int forward) {
		
		int reforward = -1;
		if(forward==-1) reforward=1;
		
		
		
		// 처음 위치 회전
		clock(number, forward);
		
		// 처음 위치에서 왼쪽부분 탐색 후 true면 회전
		int idx = 1;
		for (int i = number; i > 0; i--) {
			if(rotate[i-1] && idx%2==1) {
				clock(i-1, reforward);
			}else if(rotate[i-1] && idx%2==0) {
				clock(i-1, forward);
			}
			else {
				break;
			}
			idx++;
		}
		// 처음 위치에서 오른쪽부분 탐색 후 true면 회전
		idx = 1;
		for (int i = number; i < N-1; i++) {
			if(rotate[i+1] && idx%2==1) {
				clock(i+1, reforward);
			}else if(rotate[i+1] && idx%2==0) {
				clock(i+1, forward);
			}
			else {
				break;
			}
			idx++;
		}
		
	}
	
	
	static void dfs(int number) {
		// 2, 6 비교
		if(number<0 || number>=N) return;
		rotate[number]=true;
		
		// 왼쪽비교
		if(number>0) {
			for (int i = number; i > 0; i--) {
				if(arr[i][6] != arr[i-1][2] && !rotate[i-1]) {
					dfs(i-1);
				}else {
					break;
				}
			}
		}
		
		// 오른쪽비교
		if(number<(N-1)) {
			for (int i = number; i < N-1; i++) {
				if(arr[i][2] != arr[i+1][6] && !rotate[i+1]) {
					dfs(i+1);
				}else {
					break;
				}
			}
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][8];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				arr[i][j]=str.charAt(j)-'0';
			}
		}

		K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int forward = Integer.parseInt(st.nextToken());
			rotate = new boolean[N];
			
			dfs(number-1);
			
			rotate(number-1, forward);
			
		}
		
		
		
		
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum+=arr[i][0];
		}
		System.out.println(sum);
		
	}
}
