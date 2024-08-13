package java_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서연결하기_1767 {
	static int[][] arr;
	static boolean[][] v;
	static List<int[]> coreList;
	static int N, maxCores;
	static int minLength;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	static void dfs(int idx, int connectCore, int length) {
		
		
		int[] coreIdx = coreList.get(idx);
		int r = coreIdx[0];
		int c = coreIdx[1];
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			int l = 0;
			int lr = nr;
			int lc = nc;
			// 연결 가능한지 확인
			boolean isConnect = true;
			while(true) {
				lr += dr[d];
				lc += dc[d];
				
				if(lr<0 || lr>=N || lc<0 || lc>=N) {
					break;
				}
				if(arr[lr][lc]!=0) {
					isConnect = false;
					break;
				}
				l++;
			}
			
			// 코어를 연결했을 경우
			if(isConnect) {
				int cr = r;
				int cc = c;
				
				for (int i = 0; i < l; i++) {
					cr += dr[d];
					cc += dc[d];
					arr[cr][cc] = 2;
				}
				
			}
			
			// 연결 못한 경우 idx+1
			dfs(idx+1, connectCore, length);
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/*
		 * 최대한 많은 Core에 전원을 연견하였을 경우, 전선 길이의 합을 구하라.
		 * 단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			
			maxCores = 0;
			minLength = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			v = new boolean[N][N];
			
			coreList = new ArrayList<int[]>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]==1 && (i!=0 && i!=(N-1) && j!=0 && j!=(N-1))){
						coreList.add(new int[] {i, j});
					}
				}
			}
//			for (int i = 0; i < coreList.size(); i++) {
//				System.out.println(Arrays.toString(coreList.get(i)));
//			}
			
			dfs(0, 0, 0);
			
			System.out.println("#"+t + " " + minLength);
		}
	}
}

//3
//7
//0 0 1 0 0 0 0
//0 0 1 0 0 0 0
//0 0 0 0 0 1 0
//0 0 0 0 0 0 0
//1 1 0 1 0 0 0
//0 1 0 0 0 0 0
//0 0 0 0 0 0 0
