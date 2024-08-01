import java.util.*;

class Node {
	int x, y, cnt;
	
	Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
public class Main {
	static int k, w, h, tmp;
	static int arr[][][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		w = sc.nextInt();
		h = sc.nextInt();
		arr= new int[h][w][2];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				tmp = sc.nextInt();
				if (tmp == 0) {
					arr[i][j][0] = tmp;
				}
				else {
					arr[i][j][0] = -1;
				}
			}
		}
		// 일단 bfs 돌리고
		int dx[] = {1, -1, 0, 0};
		int dy[] = {0, 0, 1, -1};
		int hx[] = {-2, -2, -1, -1, 1, 1, 2, 2};
		int hy[] = {1, -1, 2, -2, 2, -2, 1, -1};
		Queue<Node> q = new LinkedList<>();
		List<Integer> li = new ArrayList<>();
		q.offer(new Node(0,0,0));
		int cnt = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				// 범위 밖
				if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				// 벽
				if (arr[nx][ny][0] == -1 || (arr[nx][ny][0] != 0 && arr[nx][ny][0] < arr[cur.x][cur.y][0] + 1)) continue;
				arr[nx][ny][0] = arr[cur.x][cur.y][0] + 1;
				q.offer(new Node(nx, ny, cur.cnt));
			}
		}
		arr[0][0][0] = 0;
		// 배열 복사
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				arr[i][j][1] = arr[i][j][0];
			}
		}
		// 말 bfs
		q.offer(new Node(0,0,0));
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.x == h - 1 && cur.y == w - 1) {
				cnt = cur.cnt;
			}
			for (int i = 0; i < hx.length; i++) {
				int nx = cur.x + hx[i];
				int ny = cur.y + hy[i];
				// 범위 밖
				if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				// 벽
				if (arr[nx][ny][1] == -1 || (arr[nx][ny][1] != 0 && arr[nx][ny][1] < arr[cur.x][cur.y][1] + 1)) continue;
				arr[nx][ny][1] = arr[cur.x][cur.y][1] + 1;
				q.offer(new Node(nx, ny, cur.cnt + 1));
				li.add(dist(cur.x, cur.y, nx, ny));
			}
		}
		arr[0][0][1] = 0;
		// 도착점까지 갈 수 없는 경우
		if (arr[h - 1][w - 1][0] == 0) {
			System.out.println(-1);
			return;
		}
//		for (int i = 0; i < h; i++) {
//			for (int j = 0; j < w; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		Collections.sort(li);
		int ans = arr[h - 1][w - 1][1];
		for (int i = 0; i < cnt - k; i++) {
			ans += li.get(i) - 1;
		}
		System.out.println(ans);
	}
	
	public static int dist(int cx, int cy, int nx, int ny) {
		// 범위, 못 가는 길 처리
		int tmp = arr[nx][ny][0] - arr[cx][cy][0];
		if (tmp < 3 && tmp > -3) return Integer.MAX_VALUE;
		if (tmp < 0) return -tmp;
		else if (tmp > 0) return tmp;
		return Integer.MAX_VALUE;
	}
}
