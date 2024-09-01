import java.util.ArrayList;
import java.util.Scanner;
 
public class Solution {
 
    static int ans, N, K;
    static int[][] M;
    static ArrayList<int[]> hills;
 
    static int[] dr = { 1, 0, -1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
 
            N = sc.nextInt();
            K = sc.nextInt();
            ans = 0;
 
            M = new int[N][N];
 
            int maxHeight = 0;
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    M[i][j] = sc.nextInt();
                    maxHeight = Math.max(maxHeight, M[i][j]);
                }
            }
 
            hills = new ArrayList();
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (M[i][j] == maxHeight) {
                        hills.add(new int[] { i, j });
                    }
                }
            }
 
            for (int i = 0; i < hills.size(); i++) {
                int[] hill = hills.get(i);
                dfs(hill[0], hill[1], false, 1, new boolean[N][N]);
            }
 
            System.out.printf("#%d %d\n", tc, ans);
        }
 
    }
 
    private static void dfs(int r, int c, boolean cut, int len, boolean[][] visited) {
        visited[r][c] = true;
        for (int d = 0; d < dr.length; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
 
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;
 
            if (M[nr][nc] >= M[r][c]) {
                if (!cut && !visited[nr][nc] && M[nr][nc] - M[r][c] < K) {
                    int temp = M[nr][nc];
                    M[nr][nc] = M[r][c] - 1;
                    dfs(nr, nc, true, len + 1, visited);
                    M[nr][nc] = temp;
                }
            } else {
                dfs(nr, nc, cut, len + 1, visited);
            }
 
        }
        visited[r][c] = false;
        ans = Math.max(ans, len);
 
    }
}
