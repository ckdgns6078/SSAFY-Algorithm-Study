import java.util.Scanner;
 
public class Solution {
    static int N;
    static int[][] matrix;
    static boolean[] visited;
    static int ans;
 
    public static int synergy(int[] lst) {
        int total = 0;
        for (int i = 0; i < lst.length - 1; i++) {
            for (int j = i + 1; j < lst.length; j++) {
                total += matrix[lst[i]][lst[j]] + matrix[lst[j]][lst[i]];
            }
        }
        return total;
    }
 
    public static void dfs(int n, int k) {
        if (n == N / 2) {
            int[] A = new int[N / 2];
            int[] B = new int[N / 2];
            int idxA = 0, idxB = 0;
 
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    A[idxA++] = i;
                } else {
                    B[idxB++] = i;
                }
            }
 
            int totalA = synergy(A);
            int totalB = synergy(B);
 
            ans = Math.min(ans, Math.abs(totalA - totalB));
            return;
        }
 
        for (int i = k; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(n + 1, i + 1);
                visited[i] = false;
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            matrix = new int[N][N];
            visited = new boolean[N];
            ans = Integer.MAX_VALUE;
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
 
            dfs(0, 0);
 
            System.out.println("#" + tc + " " + ans);
        }
        sc.close();
    }
}
