import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, number;
    static int[][] arr;
    static boolean[][] v;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] adj;

    static class Point {
        int r, c, number;

        Point(int r, int c, int number) {
            this.r = r;
            this.c = c;
            this.number = number;
        }
    }

    // 섬 번호 매기기
    static void bfs(int r, int c, int number) {
        Queue<Point> queue = new ArrayDeque<Point>();
        queue.offer(new Point(r, c, number));
        arr[r][c] = number;
        v[r][c] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc] || arr[nr][nc] == 0) continue;

                v[nr][nc] = true;
                arr[nr][nc] = number;
                queue.offer(new Point(nr, nc, number));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬의 id
        number = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!v[i][j] && arr[i][j] == 1) {
                    bfs(i, j, number);
                    number++;
                }
            }
        }

        int V = number - 2;
        adj = new int[V][V];
        
        for (int i = 0; i < V; i++) {
            Arrays.fill(adj[i], Integer.MAX_VALUE);
        }

        // 다리 연결하기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (arr[r][c] > 1) {
                    makeAdj(r, c);
                }
            }
        }

        // 프림
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[V];
        dist[0] = 0;

        for (int cnt = 0; cnt < V; cnt++) {
            int minIdx = -1;
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < V; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    minIdx = i;
                }
            }

            if (minIdx == -1) {  // 연결이 불가능한 경우
                System.out.println(-1);
                return;
            }

            visited[minIdx] = true;

            for (int i = 0; i < V; i++) {
                if (!visited[i] && adj[minIdx][i] != Integer.MAX_VALUE && adj[minIdx][i] < dist[i]) {
                    dist[i] = adj[minIdx][i];
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            sum += dist[i];
        }
        System.out.println(sum);
    }

    private static void makeAdj(int r, int c) {
        int start = arr[r][c] - 2;

        for (int d = 0; d < 4; d++) {
            int nr = r;
            int nc = c;
            int dist = 0;

            while (true) {
                nr += dr[d];
                nc += dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == arr[r][c]) break;
                if (arr[nr][nc] > 0) {
                    if (dist >= 2) { 
                        int end = arr[nr][nc] - 2;
                        adj[start][end] = Math.min(adj[start][end], dist);
                        adj[end][start] = adj[start][end];
                    }
                    break;
                }
                dist++;
            }
        }
    }
}
