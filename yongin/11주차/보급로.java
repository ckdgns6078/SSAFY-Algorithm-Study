import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, result;
	static int[][] arr;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static class Point{
		int r, c, cnt;
		Point(int r, int c, int cnt){
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
	}
	
	public static void main(String[] args) throws Exception{
		// solve
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			result = Integer.MAX_VALUE;
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = str.charAt(j)-'0';
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			bfs(0, 0);
//			System.out.println(result);
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs(int r, int c) {
		// TODO Auto-generated method stub
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(r, c, 0));
		boolean[][] v = new boolean[N][N];
		v[r][c] = true;
		int[][] distArr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distArr[i][j] = arr[i][j];
			}
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			if(p.r == N-1 && p.c == N-1) {
				result = Math.min(result, p.cnt);
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r+dr[d];
				int nc = p.c+dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<N) {
					if(v[nr][nc] && arr[nr][nc]+p.cnt < distArr[nr][nc]) {
//						System.out.println("emfd");
						distArr[nr][nc] = arr[nr][nc]+p.cnt;
						queue.offer(new Point(nr, nc, distArr[nr][nc]));
					}else if(!v[nr][nc]) {
						v[nr][nc] = true;
						distArr[nr][nc] = arr[nr][nc]+p.cnt;
						queue.offer(new Point(nr, nc, distArr[nr][nc]));
					}

				}
			}
		}
	}
}
