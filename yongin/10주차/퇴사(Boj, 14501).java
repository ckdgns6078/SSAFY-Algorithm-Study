import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
//        for (int[] a : arr) {
//            System.out.println(Arrays.toString(a));
//        }
        int[] dp = new int[N+1];

        for (int i=0; i<N; i++) {
            for (int j=i+arr[i][0]; j<N+1; j++) {
                if(dp[j]<dp[i]+arr[i][1]){
                    dp[j] = dp[i] + arr[i][1];
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }
}
