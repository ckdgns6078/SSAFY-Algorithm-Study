package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기_2206 {
	static int[][] arr;
	static boolean[][][] v;
	static int[][] dist;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int N, M;
	
	static class Point{
		int r, c, broke;
		Point(int r, int c, int broke){
			this.r = r;
			this.c = c;
			this.broke = broke; // 벽 부셨는지 여부
		}
	}
	
	static void bfs(int r, int c, int broke) {
		// broke : 0, 벽 안부심
		// broke : 1, 벽 부심
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c, broke));
		v[0][r][c] = true;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				if(p.r == N-1 && p.c == M-1) return; // 도착
				
				// 벽이 있을 경우
				if(arr[nr][nc] == 1) {
					// 벽 안부셨는지 확인, 벽 부실 때 방문배열이 false인지 확인
					if(p.broke==0 && !v[1][nr][nc]) {
//						System.out.println("들어옴?");
						v[p.broke][nr][nc] = true;
						dist[nr][nc] = dist[p.r][p.c]+1; // 거리 갱신
						queue.offer(new Point(nr, nc, 1)); // 벽 부셨으니 p.broke 1로 주기
					}
				}
				else {
					if(!v[p.broke][nr][nc]) {
						v[p.broke][nr][nc] = true;
						dist[nr][nc] = dist[p.r][p.c]+1; // 거리 갱신
						queue.offer(new Point(nr, nc, p.broke));
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		v = new boolean[2][N][M]; // v[0] 벽 안부실 때 방문배열, v[1] 벽 부시고 방문배열
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		if(N==1 && M==1) {
			System.out.println(1);
			return;
		}
		
		bfs(0, 0, 0);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dist[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		if(dist[N-1][M-1]==0) {
			System.out.println(-1);
			return;
		}
		System.out.println(dist[N-1][M-1]+1);
		
		
		
	}
}

//1 1
//0
//answer: 1


//6 4
//0100
//1110
//1000
//0000
//0111
//0000
