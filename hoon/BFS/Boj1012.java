import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

class Point {
	int r, c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Boj1012 {

	private static int[][] map;
	private static boolean[][] visited;

	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };
	private static int result = 0;
	private static int M, N, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			M = sc.nextInt(); // 가로길이
			N = sc.nextInt(); // 세로길이
			K = sc.nextInt(); // K번 입력

			map = new int[M][N];
			visited = new boolean[M][N];
			for (int i = 0; i < K; i++) {
				map[sc.nextInt()][sc.nextInt()] = 1;

			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && visited[i][j] == false) {
						bfs(i, j);
					}
				}
			}

			System.out.println(result);
			result=0;

		}
	}

	private static void bfs(int y, int x) {
		// y, x 값은 1이다.
		result++;
		visited[y][x] = true;
		Queue<Point> q = new ArrayDeque();
		q.offer(new Point(y, x));
		while (!q.isEmpty()) {

			for (int s = 0; s < q.size(); s++) {
				boolean check = true;
				Point p = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = p.r + dr[i];
					int nc = p.c + dc[i];

					if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] != 0 && visited[nr][nc] == false) {
						check = false;
						visited[nr][nc] = true;
//						System.out.println("( " + nr + "," + nc + " )");
						q.offer(new Point(nr, nc));
					}

				}

			}

		}

	}

}
