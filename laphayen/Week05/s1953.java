import java.util.*;
 
public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] rev = {1, 0, 3, 2};
 
    static int[][] tunnel = {
        {},
        {1, 1, 1, 1},
        {1, 1, 0, 0},
        {0, 0, 1, 1},
        {1, 0, 0, 1},
        {0, 1, 0, 1},
        {0, 1, 1, 0},
        {1, 0, 1, 0}
    };
 
    public static int bfs(int sr, int sc, int N, int M, int L, int[][] A) {
        int[][] visited = new int[N][M];  // To track visited cells
        visited[sr][sc] = 1;
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{sr, sc});
        int result = 1;
 
        while (!Q.isEmpty()) {
            int qlen = Q.size();
            for (int i = 0; i < qlen; i++) {
                int[] pos = Q.poll();
                int r = pos[0], c = pos[1];
 
                for (int d = 0; d < 4; d++) {
                    if (tunnel[A[r][c]][d] == 0) continue;
 
                    int nr = r + dr[d];
                    int nc = c + dc[d];
 
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (A[nr][nc] == 0 || visited[nr][nc] > 0) continue;
 
                    if (tunnel[A[nr][nc]][rev[d]] == 1) {
                        if (visited[r][c] + 1 > L) return result;
 
                        visited[nr][nc] = visited[r][c] + 1;
                        Q.offer(new int[]{nr, nc});
                        result++;
                    }
                }
            }
        }
        return result;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();
            int L = sc.nextInt();
 
            int[][] A = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    A[i][j] = sc.nextInt();
                }
            }
 
            System.out.println("#" + tc + " " + bfs(R, C, N, M, L, A));
        }
        sc.close();
    }
}
