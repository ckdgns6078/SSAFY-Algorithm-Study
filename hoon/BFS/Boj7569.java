package tt111;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

import tt111.Point;

class Point {
	int r, c, h;

	Point(int h, int r, int c) {

		this.h = h;
		this.r = r;
		this.c = c;

	}

}

public class Boj7569 {
	private static int M, N, H;
	private static int[][][] map;
	private static boolean[][][] visited;
	private static Queue<Point> q = new ArrayDeque();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();

		map = new int[H][N][M];
		visited = new boolean[H][N][M];

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[k][i][j] = sc.nextInt();
					if (map[k][i][j] == 1) {
						q.offer(new Point(k, i, j));
					} else if (map[k][i][j] == 0) {
						cnt++;
					}
				}
			}

			// 전부 1일때

		}

		if (cnt == 0) {
			System.out.print(0);
			return;
		}
		int re = bfs();
		System.out.println(re);

	}

	public static int checkVisited() {
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[k][i][j] != -1 && visited[k][i][j] == false && map[k][i][j] == 0) {
						return -1;
					}
				}
			}
		}
		return 0;
	}

	public static int bfs() {
		int[] xarr = { -1, 1, 0, 0 };
		int[] yarr = { 0, 0, -1, 1 };
		int[] harr = { -1, 1 };
		int result = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Point p = q.poll();
				visited[p.h][p.r][p.c] = true;

				for (int i = 0; i < 4; i++) {
					// r : y / c : x
					int y = p.r + yarr[i];
					int x = p.c + xarr[i];
					if (y >= 0 && y < N && x >= 0 && x < M && visited[p.h][y][x] == false && map[p.h][y][x] != -1) {
						visited[p.h][y][x] = true;
						map[p.h][y][x] = 1;
						q.offer(new Point(p.h, y, x));
					}
				}
				for (int i = 0; i < 2; i++) {
					int h = p.h + harr[i];
					if (h >= 0 && h < H && visited[h][p.r][p.c] == false && map[h][p.r][p.c] != -1) {
						visited[h][p.r][p.c] = true;
						map[h][p.r][p.c] = 1;
						q.offer(new Point(h, p.r, p.c));
					}
				}
			}

			result++;

		}
		result -= 1;
		if (checkVisited() == -1) {
			return -1;
		}
		return result;
	}
}
