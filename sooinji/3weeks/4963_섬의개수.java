import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {

    static int w, h, cnt;
    static int arr[][];
    static boolean vis[][];
    static int dx[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int dy[] = {0, 0, 1, -1, -1, 1, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            w = sc.nextInt();
            h = sc.nextInt();
            arr = new int[h][w];
            vis = new boolean[h][w];
            cnt = 0;
            if (w == 0 && h == 0) return;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j] == 1 && vis[i][j] == false) {
                    	dfs(new Point(i, j));
                    	cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
	private static void dfs(Point p) {
		Stack<Point> st = new Stack<>();
		st.push(p);
		while(!st.isEmpty()) {
			Point cur = st.pop();
			vis[cur.x][cur.y] = true;
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				if (vis[nx][ny] || arr[nx][ny] == 0) continue;
				st.push(new Point(nx, ny));
			}
		}
	}

}
