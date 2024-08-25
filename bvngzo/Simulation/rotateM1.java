import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt();
		int C = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] M = new int[R][C];
		
		int[] dr = {1,0,-1,0};
		int[] dc = {0,1,0,-1};
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				M[i][j] = sc.nextInt();
			}
		}
		
		int layer = Math.min(R, C) / 2;

		
		for(int k = 0; k < K; k++) { // 회전 수
			
			int curr_r = 0;
			int curr_c = 0;
			int curr_v = M[curr_r][curr_c];
			int len = R;
			
			for(int s = 0; s < layer; s++) {	//배열 레이어
				for(int d = 0; d < 4; d++) {	// 방향
					for(int l = 1; l < len; l++) {	// 배열 한변길이
						
						int nw_r = curr_r + dr[d];
						int nw_c = curr_c + dc[d];
						
						int nw_v = M[nw_r][nw_c];
						M[nw_r][nw_c] = curr_v;
						
						curr_v = nw_v;
						curr_r = nw_r;
						curr_c = nw_c;
						
					}	//end len
					len = ((d+1) % 2 == 0) ? R : C;
				}// end dir
				R -= 2;
				C -= 2;
				curr_r += 1;
				curr_c += 1;
				curr_v = M[curr_r][curr_c];
				len = R;
			}// end layer
			
			R = M.length;
			C = M[0].length;
		
		}// end cnt
		
		print(M);
	}
	
	static void print(int[][] M) {
		for (int[] is : M) {
			for (int i : is) {
				System.out.print(i+" ");
			}System.out.println();
		}
	}
	

}
