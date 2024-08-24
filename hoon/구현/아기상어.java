import java.util.*;

class PreyInfo implements Comparable<PreyInfo> {
	int r;
	int c;
	int d;

	PreyInfo(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}

	@Override
	public int compareTo(PreyInfo o) {
		if (this.d != o.d) {
			return Integer.compare(this.d, o.d);
		}
		if (this.r != o.r) {
			return Integer.compare(this.r, o.r);
		}

		return Integer.compare(this.c, o.c);
	}

}

public class Main {

	static int n, m;
	static int[][] map;
	static int sharkSize = 2;
	static int shark[];
	static int eatCount = 0;
	static int preyCount;
	static int result = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 15 : 20 시작
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					map[i][j] = 0;
					shark = new int[] { i, j };
				}
				if (map[i][j] < 2 && map[i][j] != 0) {
					preyCount++;
				}
			}

		}

		while (preyCount != 0) {
			PreyInfo prey;
			ArrayList<int[]> preyList = new ArrayList();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] < sharkSize && map[i][j] != 0) {
						preyList.add(new int[] { i, j });
					}
				}
			}
			if (preyList.size() == 0) {
				break;
			}

			prey = checkDistance(preyList);
			if (prey == null) {
				break;
			}
			result += moveShark(prey.r, prey.c);
		}
		System.out.println(result);

	}

	// 먹을 수 있는 먹이들의 List
	private static PreyInfo checkDistance(ArrayList<int[]> preyList) {
		PriorityQueue<PreyInfo> pq = new PriorityQueue<PreyInfo>();

		for (int k = 0; k < preyList.size(); k++) {
			int distance = 0;
			boolean[][] v = new boolean[n][n];
			int r = shark[0];
			int c = shark[1];
			v[r][c] = true;
			ArrayDeque<int[]> q = new ArrayDeque();
			q.offer(new int[] { r, c });

			L: while (!q.isEmpty()) {
				int size = q.size();

				for (int t = 0; t < size; t++) {
					int[] arr = q.poll();

					if (arr[0] == preyList.get(k)[0] && arr[1] == preyList.get(k)[1]) {
						pq.offer(new PreyInfo(arr[0], arr[1], distance));
						break L;
					}

					for (int i = 0; i < 4; i++) {
						int nr = arr[0] + dr[i];
						int nc = arr[1] + dc[i];

						if (nr >= 0 && nr < n && nc >= 0 && nc < n && !v[nr][nc] && map[nr][nc] <= sharkSize) {
							q.offer(new int[] { nr, nc });
							v[nr][nc] = true;
						}

					}
				}
				distance++;
			}
		}
		return pq.poll();
	}

	private static int moveShark(int r, int c) {
		boolean[][] v = new boolean[n][n];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { shark[0], shark[1] });
		v[shark[0]][shark[1]] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int k = 0; k < size; k++) {
				int[] p = q.poll();

				if (p[0] == r && p[1] == c) {
					map[r][c] = 0;
					shark[0] = r;
					shark[1] = c;
					eatCount++;
					if (eatCount == sharkSize) {
						sharkSize++;
						eatCount = 0;
					}
					return cnt;
				}
				for (int i = 0; i < 4; i++) {
					int nr = p[0] + dr[i];
					int nc = p[1] + dc[i];

					if (nr >= 0 && nr < n && nc >= 0 && nc < n && !v[nr][nc] && map[nr][nc] <= sharkSize) {
						q.offer(new int[] { nr, nc });
						v[nr][nc] = true;
					}
				}
			}
			cnt++;
		}
		return cnt;
	}

}
