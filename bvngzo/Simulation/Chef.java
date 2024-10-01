import java.util.ArrayList;
import java.util.Scanner;
 
public class Solution {
    static int ans = Integer.MAX_VALUE;
    static int N = 0;
    static int[][] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int t = 1; t <= T; t++) {
            ans = Integer.MAX_VALUE;
             
            N = sc.nextInt();
             
            graph = new int[N][N];
             
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    graph[i][j] = sc.nextInt();
                }
            }
             
            powerset(0,0,new boolean[N]);
            System.out.printf("#%d %d\n",t,ans);
        }
    }
    private static void powerset(int idx, int cnt, boolean[] selected) {
        if(idx == N) return;
         
        if(cnt == N/2) {
            ArrayList<Integer> A = new ArrayList<Integer>();
            ArrayList<Integer> B = new ArrayList<Integer>();
            for(int i = 0; i < N; i++) {
                if(selected[i]) A.add(i);
                else B.add(i);
            }
            int sumA = getSum(A);
            int sumB = getSum(B);
             
            ans = Math.min(ans, Math.abs(sumA-sumB));
            return;
        }
         
        selected[idx] = true;
        powerset(idx+1,cnt+1,selected);
         
        selected[idx] = false;
        powerset(idx+1,cnt,selected);
         
    }
     
    private static int getSum(ArrayList<Integer> a) {
        int sum = 0;
        for (int i1 : a) {
            for (int i2 : a) {
                sum += graph[i1][i2];
            }
        }
        return sum;
    }
 
}
