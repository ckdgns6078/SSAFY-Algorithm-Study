import java.util.*;

class Node{
	public int x;
	public int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int t, m, n, k, ans;
	static int arr[][];
	static boolean vis[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int test_case = 0; test_case < t; test_case++) {
			ans = 0;
			m = sc.nextInt();
			n = sc.nextInt();
			arr = new int[m][n];
			vis = new boolean[m][n];
			k = sc.nextInt();
			int kx, ky;
			for (int i = 0; i < k; i++) {
				kx = sc.nextInt();
				ky = sc.nextInt();
				arr[kx][ky] = 1;
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == 1 && vis[i][j] == false) {
						bfs(i, j);
						ans++;
					}
				}
			}
			System.out.println(ans);
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x,y));
		vis[x][y] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx >= 0 && nx < m && ny >= 0 && ny < n && vis[nx][ny] == false && arr[nx][ny] == 1) {
					vis[nx][ny] = true;
					q.offer(new Node(nx, ny));
				}
			}
		}
	}
}
