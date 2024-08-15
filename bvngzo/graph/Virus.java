import java.util.Scanner;

public class Main {

    static int N;
    static int[][] M;
    static boolean[]V;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = new int[N+1][N+1];
        V = new boolean[N+1];

        int pair = sc.nextInt();

        for(int i = 0 ; i < pair; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            M[v1][v2] = 1;
            M[v2][v1] = 1;
        }

        dfs(1);
        System.out.println(cnt-1);
    }

    static void dfs(int s){
        cnt++;
        V[s] = true;

        for(int i = 1; i < N+1; i++){
            if(M[s][i] == 1 && !V[i]){
                dfs(i);
            }
        }
    }
}
