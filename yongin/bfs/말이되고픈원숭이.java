package java_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int r, c;
    int hCnt;
    int mCnt;
    Point(int r, int c, int hCnt, int mCnt){
        this.r = r;
        this.c = c;
        this.hCnt = hCnt;
        this.mCnt = mCnt;
    }
}

public class copy1 {
	static int K, W, H;
	static int[][] arr;
	static boolean[][][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[] hr = {2, 2, -2, -2, 1, 1, -1, -1};
	static int[] hc = {-1, 1, -1, 1, -2, 2, -2, 2};

	static void print() {
		for (int i = 0; i < K+1; i++) {
			for (int j = 0; j < H; j++) {
				for (int k = 0; k < W; k++) {
					System.out.print(v[i][j][k]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------");
	}
	
	static int bfs(int r, int c, int k) {
		Queue<Point> Q = new ArrayDeque<Point>();
		Q.offer(new Point(r, c, 0, 0));
		v[0][r][c] = true;
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			
			int curR = p.r;
			int curC = p.c;
			
			if(curR == H-1 && curC == W-1) {
				return p.mCnt;
			}
			
			// 사방탐색
			for (int i = 0; i < 4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];

//				System.out.println("현재위치 : " + curR + " " + curC +  ", 사방탐색 : " + nr + " " + nc);
//				print();
				
				if(nr<0 || nr>=H || nc<0 || nc>=W) continue;
				if(v[p.hCnt][nr][nc]) continue;
				
				if(arr[nr][nc] == 0) {
					v[p.hCnt][nr][nc] = true;
					Q.offer(new Point(nr, nc, p.hCnt, p.mCnt+1));
				}
			}
			
			if(p.hCnt < K) {
				for (int i = 0; i < 8; i++) {
					int nr = curR + hr[i];
					int nc = curC + hc[i];
					
					
//					System.out.println("현재위치 : " + curR + " " + curC +  ", 팔방탐색 : " + nr + " " + nc);
//					print();
					
					
					if(nr<0 || nr>=H || nc<0 || nc>=W) continue;
					if(v[p.hCnt+1][nr][nc]) continue;
					
					if(arr[nr][nc] == 0) {
						v[p.hCnt+1][nr][nc] = true;
						Q.offer(new Point(nr, nc, p.hCnt+1, p.mCnt+1));
					}
				}
			}
		}
		return -1;
	}
	

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][W];
        v = new boolean[K+1][H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = bfs(0, 0, K);
        System.out.println(result);
        
    }

}
