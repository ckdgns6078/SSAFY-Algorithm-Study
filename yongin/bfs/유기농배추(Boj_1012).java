package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int r, c;
	Point(int r, int c){
		this.r = r;
		this.c = c;
	}
}


public class 유기농배추_1012 {
	static int N, M;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result = 0;
	
	private static void bfs(int Sr, int Sc) {
		Queue<Point> Q = new ArrayDeque<Point>();
		Q.offer(new Point(Sr, Sc));
		v[Sr][Sc] = true;
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && map[nr][nc] == 1) {
					v[nr][nc] = true;
					Q.offer(new Point(nr, nc));
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		/*
		 * 0: 배추 x, 1: 배추 o
		 * 입력 첫줄에는 테스트 케이스 개수 T
		 * 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M, 
		 * 세로길이 N 그리고 배추가 심어져 있는 위치의 개수 K
		 */
		int x, y;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int BaeChu = Integer.parseInt(st.nextToken());
			map = new int [N][M];
			v = new boolean[N][M];
			for(int i=0; i<BaeChu; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			
			for(int i = 0; i< N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 1 && !v[i][j]) {
						bfs(i, j);
						result++;
					}				
				}
			}
			System.out.println(result);
		}
	}
}

