import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

class Point2 {
	int r;
	int c;
	int cnt;
	int horse;

	Point2(int r, int c, int cnt, int horse) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.horse = horse;
	}

}

public class Main {
	static int K, H, W;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][W];

		visited = new boolean[H][W][K + 1];
//		visited = new boolean[K][H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();

			}
		}

		bfs();

	}

	private static void bfs() {

		int[] dr = { -1, 1, 0, 0, 1, 2, 2, 1, -1, -2, -2, -1 };
		int[] dc = { 0, 0, 1, -1, -2, -1, 1, 2, 2, 1, -1, -2 };

		Queue<Point2> q = new ArrayDeque();
		q.offer(new Point2(0, 0, 0, 0));
		visited[0][0][0] = true;
		boolean check = false;
		// 갈 수 있으면 이동하고 이동하지 못하면 칸이동
		while (!(q.isEmpty())) {
			Point2 p = q.poll();
			if (p.r == H - 1 && p.c == W - 1) {
				System.out.println(p.cnt);
				check = true;
				break;
			}
			for (int d = 0; p.horse >= K ? d < 4 : d < 12; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				int nHorse = d < 4 ? p.horse : p.horse + 1;
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc][nHorse] && map[nr][nc] != 1) {
					visited[nr][nc][nHorse] = true;
					q.offer(new Point2(nr, nc, p.cnt + 1, nHorse));
				}
			}
		}

		if (!check)
			System.out.println(-1);

	}
}
