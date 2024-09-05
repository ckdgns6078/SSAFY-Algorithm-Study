import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		int R = sc.nextInt();
		
		int[][] map = new int[R][C];
		int[][] dist = new int[R][C];
		boolean[][] selected = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			char[] ch = sc.next().toCharArray();
			for(int j = 0; j < C; j++) {
				map[i][j] = ch[j] - '0';
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2) -> Integer.compare(o1[2], o2[2]));
		
		dist[0][0] = map[0][0];
		PQ.offer(new int[] {0,0,dist[0][0]});
		
		while(!PQ.isEmpty()) {
			int[] curr = PQ.poll();
			
			int r = curr[0];
			int c = curr[1];
			int distance = curr[2];
			
			selected[r][c] = true;
			
			for(int d = 0; d < dr.length; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
			
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || selected[nr][nc]) continue;
				
				if(dist[nr][nc] > distance + map[nr][nc]) {
					dist[nr][nc] = distance + map[nr][nc];
					PQ.offer(new int[] {nr, nc, dist[nr][nc]});
				}
			}
		}// end while
		
		System.out.println(dist[R-1][C-1]);
		
	}

}
