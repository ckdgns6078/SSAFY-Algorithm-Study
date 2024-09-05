import java.util.*;

class Point implements Comparable<Point>{
	int r;
	int c;
	int cost;

	Point(int r, int c, int cost) {
		this.r = r;
		this.c = c;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Point o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] v;
	static int result = Integer.MAX_VALUE;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		m = sc.nextInt();	//가로
		n = sc.nextInt();	//세로

		map = new int[n][m];
		v = new boolean[n][m];
		
		
		for(int i = 0 ; i < n ; i++) {
			String input = sc.next();
			for( int j = 0 ; j < input.length() ; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		

		bfs(0, 0);
		System.out.println(result);
	}

	private static void bfs(int r, int c) {
		PriorityQueue<Point> q = new PriorityQueue();
		v[r][c] = true;
		q.offer(new Point(0, 0, 0));

		while (!q.isEmpty()) {

			Point p = q.poll();
			if(p.r==n-1 && p.c == m-1) {
				result = Math.min(result, p.cost);
			}
			
			for( int i = 0 ; i < 4 ; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				int cost = p.cost;				
				if(nr>=0 && nr<n && nc>=0 && nc<m && !v[nr][nc]) {
					q.offer(new Point(nr,nc,cost+map[nr][nc]));
					v[nr][nc]= true;
				}
			}
			
			
		}

	}

}
