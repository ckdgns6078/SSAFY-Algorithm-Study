import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Shark {
		int r;
		int c;
		int size;
		int consume;

		Shark() {
			size = 2;
			consume = 0;
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Shark shark = new Shark();

		int N = sc.nextInt();

		int[][] M = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				M[i][j] = sc.nextInt();
				if (M[i][j] == 9) {
					shark.r = i;
					shark.c = j;
				}
			}
		}

		int time = 0;
		L: while (true) {

			Queue<int[]> Q = new ArrayDeque();
			boolean[][] v = new boolean[N][N];

			Q.offer(new int[] { shark.r, shark.c });
			v[shark.r][shark.c] = true;

			int dist = 0;

			while (!Q.isEmpty()) {
				ArrayList<int[]> fishes = new ArrayList();
				int q_len = Q.size();
				for (int i = 0; i < q_len; i++) {
					int[] core = Q.poll();
					for (int d = 0; d < dr.length; d++) { // 4방 탐색
						int nr = core[0] + dr[d];
						int nc = core[1] + dc[d];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N || M[nr][nc] > shark.size || v[nr][nc]) {
							continue;
						} else if (M[nr][nc] == 0 || M[nr][nc] == shark.size) { // 이동만 가능

							Q.offer(new int[] { nr, nc });
							v[nr][nc] = true;

						} else if (M[nr][nc] < shark.size) { // 먹을 수 있는 고기
							fishes.add(new int[] { nr, nc });
							v[nr][nc] = true;
						}

					}
				}
				dist++;

				// 종료 조건
				if (fishes.size() == 0 && Q.isEmpty())
					break L; // 식사 불가 + 이동 불가

				if (fishes.size() > 0) { // 현재 dist 범위에는 먹을 수 있는 고기가 없음
					int targetR = N + 1;
					int targetC = N + 1;

					for (int i = 0; i < fishes.size(); i++) {
						int[] fish = fishes.get(i);

						if (fish[0] < targetR) {
							targetR = fish[0];
							targetC = fish[1];
						} else if (fish[0] == targetR) {
							targetC = Math.min(targetC, fish[1]);
						}
					}

					time += dist;

					// 좌표 갱신
					M[shark.r][shark.c] = 0;
					shark.r = targetR;
					shark.c = targetC;
					M[shark.r][shark.c] = 9;

					// 상어 상태 갱신
					shark.consume++;
					if (shark.consume == shark.size) {
						shark.size++;
						shark.consume = 0;
					}

					Q.clear();
				} 
			} // inner while

		} // outer while
		System.out.println(time);
	}

}
