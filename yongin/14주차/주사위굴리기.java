package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class 주사위굴리기 {
	static int N, M, x, y, K, result;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken()); // 주사위 x
		y = Integer.parseInt(st.nextToken()); // 주사위 y
		K = Integer.parseInt(st.nextToken()); // 명령 개수
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dice = new int[6];
		
		int[] dr = {0, 0, 0, -1, 1};
		int[] dc = {0, 1, -1, 0, 0};
		
		int temp = 0;
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < K; k++) {

			int cmd = Integer.parseInt(st.nextToken());
			int nr = x+dr[cmd];
			int nc = y+dc[cmd];
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				
			// 주사위 이동
			if(cmd == 1) { // 동쪽
				temp = dice[2];
				dice[2] = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5];
				dice[5] = temp;
				
			} else if(cmd == 2) { // 서쪽
				temp = dice[3];
				dice[3] = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5];
				dice[5] = temp;
			} else if(cmd == 3) { // 북쪽
				temp = dice[1];
				dice[1] = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = temp;
			} else if(cmd == 4) { // 남쪽
				temp = dice[4];
				dice[4] = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5];
				dice[5] = temp;
			}
			
			x+=dr[cmd];
			y+=dc[cmd];
			if(arr[nr][nc] == 0) {
				arr[nr][nc] = dice[5];
			}else {
				dice[5] = arr[nr][nc];
				arr[nr][nc] = 0;
			}
			System.out.println(dice[0]);
		}
		
		
		
	}
}
