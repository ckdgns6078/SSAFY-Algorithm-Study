import java.util.*;

class Point implements Comparable <Point>{
    int x, y, d;
    Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
    @Override
    public int compareTo(Point o) {
        if (this.d == o.d) {
            if (this.x == o.x) return this.y - o.y;
            else return this.x - o.x;
        }
        return this.d - o.d;
    }
}

public class BOJ_16236 {
    static int n, ans = 0;
    static int[][] map;
    static Point shark;
    static int size = 2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    shark = new Point(i, j, 0);
                }
            }
        }
        bfs(shark.x, shark.y);
        System.out.println(ans);
    }
    private static void bfs(int x, int y) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        boolean[][] vis = new boolean[n][n];
        int cnt = 0;
        PriorityQueue<Point> q = new PriorityQueue<>();
        vis[x][y] = true;
        q.offer(shark);
        while(!q.isEmpty()) {
            Point cur = q.poll();
            if (map[cur.x][cur.y] > 0 && map[cur.x][cur.y] < size) {
                // 물고기 냠냠
            	ans += cur.d;
            	while (!q.isEmpty()) q.poll();
            	cnt++;
            	if (cnt == size) {
            		size++;
            		cnt = 0;
            	}
            	map[cur.x][cur.y] = 9;
            	map[shark.x][shark.y] = 0;
            	shark = new Point(cur.x, cur.y, 0);
            	vis = new boolean[n][n];
            	vis[cur.x][cur.y] = true;
            	q.offer(new Point(cur.x, cur.y, 0));
            }
            else {
                for (int i = 0; i < dx.length; i++) {
                    int cx = cur.x + dx[i];
                    int cy = cur.y + dy[i];
                    if (cx < 0 || cx >= n || cy < 0 || cy >= n) continue;
                    if (map[cx][cy] > size || vis[cx][cy]) continue;
                    vis[cx][cy] = true;
                    q.offer(new Point(cx, cy, cur.d + 1));
                }
            }
        }
    }
}
