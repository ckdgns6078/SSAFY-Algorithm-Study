package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int h, r, c;
	Point(int h, int r, int c){
		this.h = h;
		this.r = r;
		this.c = c;
	}
}

public class 토마토_7569 {
	static int N, M, H;
	static int[][][] map;
	static boolean[][][] v;
	static int[] dh = {0, 0, 0, 0, -1, 1};
	static int[] dr = {-1, 1, 0, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int result = 0, cnt = 0;
	static 	Queue<Point> Q = new ArrayDeque<Point>();

	private static void bfs() {

		
		while(!Q.isEmpty()) {
			
			Point p = Q.poll();
			for(int l = 0; l < 6; l++) {
				int nh = p.h + dh[l];
				int nr = p.r + dr[l];
				int nc = p.c + dc[l];
				
				if(nh>=0 && nh<H && nr>=0 && nr<N && nc>=0 && nc<M && (map[nh][nr][nc]==0)) {
					Q.offer(new Point(nh, nr, nc));
					map[nh][nr][nc] =  map[p.h][p.r][p.c]+1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		/*
		 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있다.
		 * 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
		 * 하나의 토마토에 인접한 곳은 위 아래 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다.
		 * 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
		 * 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 알고 싶어한다.
		 * 
		 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H가 주어진다.
		 * M은 상자의 가로칸 수, N은 상자의 세로칸 수를 나타낸다.
		 * 둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다.
		 * 즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다.
		 * 1은 익은 토마토, 0은 익지 않은 토마토, -1은 토마토가 들어있지 않다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		H = Integer.parseInt(st.nextToken()); // 높이
		map = new int[H][N][M];
		v = new boolean[H][N][M];
		boolean TomatoRipe = false;
		for(int i = 0; i < H; i++){
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k] == 0) {
						TomatoRipe = true;
					}
				}
			}
		}
		
		// 토마토가 다 익은 상태
		if(!TomatoRipe) {
			System.out.println(0);
			return;
		}
		

		for(int i = 0; i < H; i++){
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(map[i][j][k]==1) {
						Q.offer(new Point(i, j, k));
					}
				}
			}
		}
		bfs();
		
		boolean NotTomato = false;
		L:for(int i = 0; i < H; i++){
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(map[i][j][k] == 0) {
						NotTomato = true;
						break L;
					}
					result = Math.max(result, map[i][j][k]);
				}
			}
		}
		
		if(NotTomato) {
			System.out.println(-1);
		}else {
			System.out.println((result-1));	
		}		
		
		
	}
}
