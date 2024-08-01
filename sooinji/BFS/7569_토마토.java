import java.util.*;

class Node{
	public int x;
	public int y;
	public int z;
	
	public Node(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main {
	static int m, n, h;
	static int arr[][][];
	static int dx[] = {1, -1, 0, 0, 0, 0};
	static int dy[] = {0, 0, 1, -1, 0, 0};
	static int dz[] = {0, 0, 0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		h = sc.nextInt();
		arr = new int[m][n][h];
		int cnt = 0;
		for (int k = 0; k < h; k++) {
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < m; i++) {
					arr[i][j][k] = sc.nextInt();
					if (arr[i][j][k] == 0) cnt++;
				}
			}
		}
		// 토마토가 모두 익어 있는 상태
		if (cnt == 0) {
			System.out.println(0);
			return;
		}
		// bfs 탐색
		for (int k = 0; k < h; k++) {
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < m; i++) {
					if (arr[i][j][k] == 1) {
						bfs(i, j, k);
					}
				}
			}
		}
		// 토마토가 모두 익지 못하는 상황 -> bfs가 끝났는데 0인 토마토가 있는 상황 찾기
		// 최소 일수 찾기
		int ans = 0;
		for (int k = 0; k < h; k++) {
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < m; i++) {
					if (arr[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					if (arr[i][j][k] > ans) {
						ans = arr[i][j][k];
					}
				}
			}
		}	
		System.out.println(ans - 1);
	}
	
	public static void bfs(int x, int y, int z) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y, z));
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 6; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int nz = cur.z + dz[i];
				// 범위 밖
				if (nx < 0 || nx >= m || ny < 0 || ny >= n || nz < 0 || nz >= h) continue;
				if (arr[nx][ny][nz] == 0 || arr[cur.x][cur.y][cur.z] + 1 < arr[nx][ny][nz]) {
					arr[nx][ny][nz] = arr[cur.x][cur.y][cur.z] + 1;
					q.offer(new Node(nx, ny, nz));
				}
			}
		}		
	}
}
