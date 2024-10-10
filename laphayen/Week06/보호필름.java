import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K, answer;
    static int[][] arr;
    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            arr = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            v = new int[D];
            answer = K;
            if (K == 1) {
                answer = 0;
            } else {
                film(0, -1);
            }
            
            System.out.println("#" + tc + " " + answer);
        }
    }

    static boolean check() {
        for (int j = 0; j < W; j++) {
            int same = 1;
            for (int i = 0; i < D - 1; i++) {
                if (same == K) break;
                if (arr[i][j] == arr[i + 1][j]) same++;
                else same = 1;
            }
            if (same != K) return false;
        }
        return true;
    }

    static void film(int n, int idx) {
        if (check()) {
            answer = Math.min(answer, n);
            return;
        }
        if (n >= answer) return;

        for (int i = idx + 1; i < D; i++) {
            if (v[i] == 0) {
                v[i] = 1;
                int[] originalRow = arr[i].clone();
                
                // Apply drug A (all 0)
                Arrays.fill(arr[i], 0);
                film(n + 1, i);

                // Apply drug B (all 1)
                Arrays.fill(arr[i], 1);
                film(n + 1, i);

                // Revert the changes
                arr[i] = originalRow;
                v[i] = 0;
            }
        }
    }
}
