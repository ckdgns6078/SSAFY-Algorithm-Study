import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int cnt = 0;
    static int[][] M;
    static int N;

    static void marking(int v, char opt, int x, int y){
        for(int i = x+1; i < N; i++){
            for(int j = 0; j < N; j++){
                if(opt == 'M'){
                    if((x == i || y == j || (i-j) == (x-y) || (i+j) == (x+y)) && M[i][j] == 0){
                        M[i][j] = -v;
                    }
                }
                else if(opt == 'U'){
                    if((x == i || y == j || (i-j) == (x-y) || (i+j) == (x+y)) && M[i][j] == -v){
                        M[i][j] = 0;
                    }
                }
            }
        }
    }
    static void N_Queen(int s, int q){
        if(q-1 == N){
            cnt ++;
            return;
        }
        if(s == N) return;

        for(int j = 0; j < N; j++){
                if(M[s][j] != 0) continue;
                else{
                    M[s][j] = q;
                    marking(q, 'M',s,j);
                    N_Queen(s+1,q+1);
                    M[s][j] = 0;
                    marking(q, 'U',s,j);
                }
            }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        M = new int[N][N];

        
            N_Queen(0, 1);
        

        System.out.println(cnt);
    }
}
