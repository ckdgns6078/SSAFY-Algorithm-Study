import java.util.*;

public class Main {

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1, 0 };
	static ArrayList<int[]> wallList = new ArrayList();
	static char[][] map = new char[8][8];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 8; i++) {
			String input = sc.next();
			for (int j = 0; j < 8; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '#') {
					wallList.add(new int[] { i, j });
				}
			}
		}

		System.out.println(findWay());
	}

	private static int findWay() {
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] { 7, 0 });

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				int r = p[0];
				int c = p[1];

				if (r == 0 && c == 7) {
					return 1;
				}
				
				if(map[r][c]=='#') {
					continue;
				}
				for (int j = 0; j < dr.length; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];

					if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8 && map[nr][nc] != '#') {
						q.offer(new int[] { nr, nc });
					}
				}

			}
			moveWall();
		}

		return 0;
	}

	private static boolean checkWall(int r, int c) {
		for (int i = 0; i < wallList.size(); i++) {
			if (r == wallList.get(i)[0] && c == wallList.get(i)[1]) {
				return true;
			}
		}
		return false;
	}

	private static void moveWall() {
		for (int i = wallList.size()-1; i >= 0; i--) {
			int wr = wallList.get(i)[0];
			int wc = wallList.get(i)[1];
			
			map[wr][wc] = '.';
			if (wr + 1 < 8) {
				map[wr + 1][wc] = '#';
			}

			wallList.get(i)[0]+=1;
			if (wallList.get(i)[0] >= 8) {
				wallList.remove(i);
			}

		}
	}

}
