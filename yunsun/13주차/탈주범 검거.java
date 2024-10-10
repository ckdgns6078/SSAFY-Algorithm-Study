import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA등산로조성 {
	public static class Node {
		int x, y, dir, time;

		public Node(int x, int y, int dir, int time) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.time = time;
		}
	}
  
	static int t, n, m, r, c, l;
	static int[][] map, visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] type = { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };
	static HashMap<Integer, Integer> check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		init();

		t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc < t + 1; tc++) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());

			map = new int[n][m];
			visited = new int[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sum += visited[i][j];
				}
			}

			sb.append("#" + tc + " " + sum + "\n");
		}

		System.out.println(sb.toString());
	}

	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < type[map[r][c]].length; i++) {
			q.add(new Node(c, r, type[map[r][c]][i], 1));
			visited[r][c] = 1;
		}

		while (!q.isEmpty()) {
			Node node = q.poll();
			
			for (int i = 0; i < type[map[node.y][node.x]].length; i++) {
				int nx = node.x + dx[type[map[node.y][node.x]][i]];
				int ny = node.y + dy[type[map[node.y][node.x]][i]];
				
				if (nx < 0 || ny < 0 || nx >= m || ny >= n || map[ny][nx] == 0 || visited[ny][nx] != 0)
					continue;

				if(checkDirection(nx, ny, node)) {
					for (int k = 0; k < type[map[ny][nx]].length; k++) {
						q.add(new Node(nx, ny, type[map[ny][nx]][k], node.time + 1));
						visited[ny][nx] = 1;
					}
				}
			}
		}
	}
	
	public static boolean checkDirection(int nx, int ny, Node node) {
		if(node.time >= l)
			return false;
		
		for (int j = 0; j < type[map[ny][nx]].length; j++) {
			if (type[map[ny][nx]][j] == check.get(node.dir)) {
				if(node.x == nx && node.y > ny && node.dir == 0)
					return true;
				if(node.x == nx && node.y < ny && node.dir == 1)
					return true;
				if(node.x > nx && node.y == ny && node.dir == 2)
					return true;
				if(node.x < nx && node.y == ny && node.dir == 3)
					return true;
			}
		}
		
		return false;
	}

	public static void init() {
		check = new HashMap();
		check.put(0, 1);
		check.put(1, 0);
		check.put(2, 3);
		check.put(3, 2);
	}
}
