import java.util.*;

class Pipe {
	int ax, ay, bx, by;
	Pipe(int ax, int ay, int bx, int by) {
		this.ax = ax;
		this.ay = ay;
		this.bx = bx;
		this.by = by;
	}
}

public class Main {
	static int n, cnt = 0;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		bfs();
		System.out.println(cnt);
	}

	private static void bfs() {
		Queue<Pipe> q = new ArrayDeque<>();
		q.offer(new Pipe(0, 0, 0, 1));
		while(!q.isEmpty()) {
			Pipe cur = q.poll();
			if (cur.ax == cur.bx) { // 가로
				// 가로
				if (cur.by + 1 < n && map[cur.bx][cur.by + 1] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx, cur.by + 1);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
				// 대각선
				if (cur.bx + 1 < n && cur.by + 1 < n && map[cur.bx + 1][cur.by] == 0 && map[cur.bx][cur.by + 1] == 0 && map[cur.bx + 1][cur.by + 1] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx + 1, cur.by + 1);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
			}
			else if (cur.ay == cur.by) { // 세로
				// 세로
				if (cur.bx + 1 < n && map[cur.bx + 1][cur.by] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx + 1, cur.by);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
				// 대각선
				if (cur.bx + 1 < n && cur.by + 1 < n && map[cur.bx + 1][cur.by] == 0 && map[cur.bx][cur.by + 1] == 0 && map[cur.bx + 1][cur.by + 1] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx + 1, cur.by + 1);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
			}
			else { // 대각선
				// 가로
				if (cur.by + 1 < n && map[cur.bx][cur.by + 1] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx, cur.by + 1);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
				// 세로
				if (cur.bx + 1 < n && map[cur.bx + 1][cur.by] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx + 1, cur.by);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
				// 대각선
				if (cur.bx + 1 < n && cur.by + 1 < n && map[cur.bx + 1][cur.by] == 0 && map[cur.bx][cur.by + 1] == 0 && map[cur.bx + 1][cur.by + 1] == 0) {
					Pipe nxt = new Pipe(cur.bx, cur.by, cur.bx + 1, cur.by + 1);
					if (nxt.bx == n - 1 && nxt.by == n - 1) {
						cnt++;
						continue;
					}
					q.offer(nxt);
				}
			}
		}
	}
}
