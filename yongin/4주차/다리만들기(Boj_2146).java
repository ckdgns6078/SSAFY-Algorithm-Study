package java_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int r, c;
	Point(int r, int c){
		this.r=r;
		this.c=c;
	}
}

class findPoint{
	int r, c, cnt;
	findPoint(int r, int c, int cnt){
		this.r=r;
		this.c=c;
		this.cnt=cnt;
	}
}

public class 다리만들기_2146 {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    
    // 각 섬마다 id 주는 bfs
    static void bfsPlusId(int r, int c, int id) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;
        arr[r][c] = id;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
                    if (!visited[nr][nc] && arr[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        arr[nr][nc] = id;
                        queue.offer(new Point(nr, nc));
                    }
                }
            }
        }
    }

    // 짧은 거리 찾는 bfs
    static int findShortDistance(int r, int c) {
        Queue<findPoint> queue = new ArrayDeque<>();
        boolean[][] visitedDistance = new boolean[N][N];
        queue.offer(new findPoint(r, c, 0)); // 현재 위치와 거리
        visitedDistance[r][c] = true;
        int currentId = arr[r][c];

        while (!queue.isEmpty()) {
            findPoint p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visitedDistance[nr][nc]) {
                    visitedDistance[nr][nc] = true;

                    if (arr[nr][nc] == 0) {
                        queue.offer(new findPoint(nr, nc, p.cnt+1));
                    } else if (arr[nr][nc] != currentId) {
                        return p.cnt;
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬마다 id 주기
        int id = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    bfsPlusId(i, j, id);
                    id++;
                }
            }
        }
        
//        for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
        
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // id가 1 이상이면 각각의 섬
                if (arr[i][j] > 1) {
                    minDistance = Math.min(minDistance, findShortDistance(i, j));
                }
            }
        }

        System.out.println(minDistance);
    }
}
