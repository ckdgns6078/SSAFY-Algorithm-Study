package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 디저트카페 {
	static int N, result;
	static int[][] arr;
	static int sr, sc;
	static boolean[] v;
	// 우하, 좌하, 상좌, 상우
	static int[] dr = {1, -1, -1, 1};
	static int[] dc = {1, 1, -1, -1};
	
	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			result = -1;
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			print();
			// 검사해야 하는 cow
//			int c = N/2 + (int) Math.ceil(N%2) -1;
//			System.out.println(c);
			// 검사해야 하는 row의 범위는 1부터 N-1까지
			
			// N이 4일 경우, 최대 2번 이동
			// N이 5일 경우, 최대 3번 이동
			// 즉 c+1 만큼 이동
			for (int i = 0; i < N-2; i++) {
				for (int j = 1; j < N-1; j++) {
					sr = j;
					sc = i;
//					System.out.println(j + " " + i);
					v = new boolean[101]; // 최대 디저트 수가 100이므로 101로 처리
					v[arr[j][i]]= true;
					dfs(j, i, j, i, 1, 0);
				}
			}
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	private static void dfs(int r, int c, int pre_r, int pre_c, int cnt, int start) {
		// TODO Auto-generated method stub
		for (int d = start; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if(pre_r == nr && pre_c == nc) continue;
			
			if(sr==nr && sc == nc) {
				result = Math.max(result, cnt);
				return;
			}
			
			if(v[arr[nr][nc]]) continue;
			
			v[arr[nr][nc]] = true;
			dfs(nr, nc, r, c, cnt+1, d);
			v[arr[nr][nc]] = false;
			
		}
	}
}




//2
//4
//9 8 9 8
//4 6 9 4
//8 7 7 8
//4 5 3 5
//5
//8 2 9 6 6
//1 9 3 3 4
//8 2 3 3 6
//4 3 4 4 9
//7 4 6 3 5
