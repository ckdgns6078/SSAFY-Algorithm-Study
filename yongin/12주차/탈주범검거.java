package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거 {
	static int N, M, R, C, L, result;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	
	static class Point{
		int r, c, cnt, type;
		Point(int r, int c, int cnt, int type){
			this.r=r;
			this.c=c;
			this.cnt=cnt;
			this.type=type;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			arr = new int[N][M];
			v = new boolean[N][M];
			
			result = 0;
			// 경과된 시간이 주어질 때 탈주범이 위치할 수 있는 장소의 개수를 구해라
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs(R, C, 0);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(v[i][j]) result++;
				}
			}
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs(int r, int c, int cnt) {
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c, cnt, arr[r][c]));

		v[r][c] = true;
		int count = 1;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			if(++count > L) break;

			while(size-- > 0) {
				Point p = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc] || arr[nr][nc]==0) continue;
					
					if(p.type == 1) {
						// 상
						if(p.r-1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==5 || arr[nr][nc]==6)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 하
						else if(p.r+1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==4 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 좌
						else if(p.r == nr && p.c-1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==4 || arr[nr][nc]==5)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 우
						else if(p.r == nr && p.c+1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==6 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}else if(p.type == 2) {
						// 상
						if(p.r-1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==5 || arr[nr][nc]==6)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 하
						else if(p.r+1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==4 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}else if(p.type == 3) {
						// 좌
						if(p.r == nr && p.c-1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==4 || arr[nr][nc]==5)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 우
						else if(p.r == nr && p.c+1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==6 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}else if(p.type == 4) {
						// 상
						if(p.r-1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==5 || arr[nr][nc]==6)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 우
						else if(p.r == nr && p.c+1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==6 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}else if(p.type == 5) {
						// 우
						if(p.r == nr && p.c+1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==6 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 하
						else if(p.r+1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==4 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}else if(p.type == 6) {
						// 좌
						if(p.r == nr && p.c-1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==4 || arr[nr][nc]==5)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 하
						else if(p.r+1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==4 || arr[nr][nc]==7)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}else if(p.type == 7) {
						// 좌
						if(p.r == nr && p.c-1 == nc && (arr[nr][nc]==1 || arr[nr][nc]==3 || arr[nr][nc]==4 || arr[nr][nc]==5)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
						// 상
						else if(p.r-1 == nr && p.c == nc && (arr[nr][nc]==1 || arr[nr][nc]==2 || arr[nr][nc]==5 || arr[nr][nc]==6)) {
							queue.offer(new Point(nr, nc, cnt+1, arr[nr][nc]));
							v[nr][nc]=true;
						}
					}
				}
			}
		}
	}
}
