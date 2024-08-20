package algorithm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 파이프옮기기1 {
	static int N, result;
	static int[][] arr;
	static String forward;
	
	static int[] rdr = {1, 1};
	static int[] rdc = {0, 1};
	static int[] cdr = {0, 1};
	static int[] cdc = {1, 1};
	static int[] rcdr = {0, 1, 1};
	static int[] rcdc = {1, 0, 1};
	
	
	static class Point{
		int r, c;
		String forward;
		public Point(int r, int c, String forward) {
			this.r = r;
			this.c = c;
			this.forward = forward;
		}
	}
	
	// forward 방향 r(세로), c(가로), rc(대각선)
	static void bfs(int r, int c, String forward) {
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(r, c, forward));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			if(p.r==N-1 && p.c==N-1) {
				result++;
			}
			if(p.forward.equals("r")) {
				for (int d = 0; d < 2; d++) {
					int nr = p.r + rdr[d];
					int nc = p.c + rdc[d];
					
					if(nr>=0 && nr<N && nc>=0 && nc<N && arr[nr][nc]==0) {
						if(d==1 && (arr[nr-1][nc]==1 || arr[nr][nc-1]==1)) continue;
						
						if(d==0) forward = "r"; // 1, 0
						if(d==1) forward = "rc"; // 1, 1
						
						
						queue.offer(new Point(nr, nc, forward));
					}
				}
			}else if(p.forward.equals("c")) {
				for (int d = 0; d < 2; d++) {
					int nr = p.r + cdr[d];
					int nc = p.c + cdc[d];
					
					if(nr>=0 && nr<N && nc>=0 && nc<N && arr[nr][nc]==0) {
						if(d==1 && (arr[nr-1][nc]==1 || arr[nr][nc-1]==1)) continue;
						if(d==0) forward = "c"; // 0, 1
						if(d==1) forward = "rc"; // 1, 1
						
						queue.offer(new Point(nr, nc, forward));
					}
					
					
				}
			}else if(p.forward.equals("rc")) {
				for (int d = 0; d < 3; d++) {
					int nr = p.r + rcdr[d];
					int nc = p.c + rcdc[d];
					
					if(nr>=0 && nr<N && nc>=0 && nc<N && arr[nr][nc]==0) {
						if(d==2 && (arr[nr-1][nc]==1 || arr[nr][nc-1]==1)) continue;
						
						if(d==0) forward = "c"; // 0, 1
						if(d==1) forward = "r"; // 1, 0 
						if(d==2) forward = "rc"; // 1, 1 
						
						queue.offer(new Point(nr, nc, forward));
					}
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 예외처리를 하여 시간 복잡도 감소
		if(arr[N-1][N-1]==1) {
			System.out.println(0);
			return;
		}
		
		bfs(0, 1, "c");
		StringBuilder sb = new StringBuilder();
		sb.append(result).append('\n');
		System.out.println(sb);
	}
}

//5
//0 0 1 0 0
//0 0 1 0 0
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0
