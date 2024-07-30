import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

class Point2 {
	int y;
	int x;
	int c;

	Point2(int y, int x, int c) {
		this.y = y;
		this.x = x;
		this.c = c;
	}

}

public class Boj1600 {
	static int K, H, W;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		K = sc.nextInt();
		H = sc.nextInt();
		W = sc.nextInt();
		map = new int[W][H];
		visited = new boolean[2][W][H];
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				map[i][j] = sc.nextInt();

			}
		}

		System.out.println(bfs());

		for (int i = 0; i < W; i++) {
			System.out.println(Arrays.toString(map[i]));
		}

	}

	private static int bfs() {
		int[] xarr = { -1, 1, 0, 0 };
		int[] yarr = { 0, 0, 1, -1 };
		int[] kx = { 1, 2, 2, 1, -1, -2, -2, -1 };
		int[] ky = { -2, -1, 1, 2, 2, 1, -1, -2 };

		Queue<Point2> q = new ArrayDeque();
		q.offer(new Point2(0, 0, 0));
		visited[0][0][0] = true;
		visited[1][0][0] = true;

		int result = 0;
		int temp = 2;
		// 갈 수 있으면 이동하고 이동하지 못하면 칸이동
		while (!(q.isEmpty())) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				Point2 p = q.poll();
				System.out.println("px: " +p.x);
				System.out.println("py: " +p.y);
				System.out.println("pc: " +p.c);
				for (int i = 0; i < W; i++) {
					System.out.println(Arrays.toString(map[i]));
				}
				System.out.println("===================");
				int x;
				int y;
				if (p.c < K) {
					for (int i = 0; i < kx.length; i++) {
						x = p.x + kx[i];
						y = p.y + ky[i];
						if (x >= 0 && x < H && y >= 0 && y < W && !visited[0][y][x] && map[y][x] != 1) {
							map[y][x] = temp;
							visited[0][y][x] = true;
							q.offer(new Point2(y, x, p.c + 1));
						}
					}
				}
				for (int i = 0; i < xarr.length; i++) {
					x = p.x + xarr[i];
					y = p.y + yarr[i];
					if (x >= 0 && x < H && y >= 0 && y < W && !visited[1][y][x] && map[y][x] != 1) {
						map[y][x] = temp;
						visited[1][y][x] = true;
						q.offer(new Point2(y, x, p.c));
					}
				}
			}

			temp++;
		}
		if (map[W - 1][H - 1] == 0) {
			return -1;
		}

		return (map[W - 1][H - 1] - 1);
	}

}
