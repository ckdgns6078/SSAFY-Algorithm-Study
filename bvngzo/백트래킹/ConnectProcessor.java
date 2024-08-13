import java.util.ArrayList;
import java.util.Scanner;
 
public class Solution {
    static ArrayList<int[]> P;
    static int[][] M;
    static int N;
    static int[] ans;
    static int lines;
    static int numOfConnected;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            M = new int[N][N];
            P = new ArrayList<>();
 
            numOfConnected = 0;
            lines = 0;
            ans = new int[] { 0, 0 };
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int val = sc.nextInt();
                    M[i][j] = val;
                    if (val == 1) {
                        P.add(new int[] { i, j });
                    }
                }
            }
 
            connectProcessor(0);
            System.out.println("#" + t + " " + ans[1]);
        }
    }
 
    static void print() {
        for (int[] arr : M) {
            for (int a : arr) {
                System.out.printf("%2d ", a);
            }
            System.out.println();
        }
    }
 
    private static void connectProcessor(int i) {
        if (i == P.size()) {
            if (ans[0] < numOfConnected) {
                ans[0] = numOfConnected;
                ans[1] = lines;
            } else if (ans[0] == numOfConnected) {
                ans[1] = Math.min(lines, ans[1]);
            }
            return;
        }
 
        int[] processor = P.get(i);
 
        if (processor[0] == 0 || processor[0] == N - 1 || processor[1] == 0 || processor[1] == N - 1) {
            numOfConnected++;
            connectProcessor(i + 1);
            numOfConnected--;
        } else {
 
            boolean connect = true;
            for (int d = 0; d < dx.length; d++) {
                int nw_x = processor[0];
                int nw_y = processor[1];
                int line = 0;
 
                while (nw_x > 0 && nw_x < N - 1 && nw_y > 0 && nw_y < N - 1) {
                    nw_x += dx[d];
                    nw_y += dy[d];
 
                    if (M[nw_x][nw_y] == 0) { // 라인 마킹
                        connect = true;
                        M[nw_x][nw_y] = -1;
                        line++;
                    } else if (M[nw_x][nw_y] != 0) {
                        connect = false;
                        for (int l = line; l > 0; l--) { // 언 마킹
                            nw_x -= dx[d];
                            nw_y -= dy[d];
                            M[nw_x][nw_y] = 0;
                        }
                        break;
                    }
                }
                if (connect) {
                    numOfConnected++;
                    lines += line;
                    connectProcessor(i + 1);
                    numOfConnected--;
                    lines -= line;
                    for (int l = line; l > 0; l--) { // 언 마킹
                        M[nw_x][nw_y] = 0;
                        nw_x -= dx[d];
                        nw_y -= dy[d];
 
                    }
                }
 
            }
 
 
                connectProcessor(i + 1);
 
        }
    }
}
