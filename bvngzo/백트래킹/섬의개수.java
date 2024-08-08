import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean check(int x, int y, int R, int C){
        return x >= 0 && x < R && y >=  0 && y < C;
    }

    static boolean dfs(int[][] M, int x, int y){
        int r = M.length;
        int c = M[0].length;

        if(!check(x,y,r,c) || M[x][y] != 1){
            return false;
        }else{
            M[x][y] = -1;
            int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
            int[] dy = {-1, 1, 1, -1, 1, 0, 0, -1};

            for(int i = 0; i < dx.length; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                dfs(M,nx,ny);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            int C = sc.nextInt();
            int R = sc.nextInt();
            sc.nextLine();
            int cnt = 0;

            if(C == 0 && R == 0) break;

            int[][] M = new int[R][C];

            for(int i = 0; i < R; i++){
                M[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            for(int i = 0; i < R; i++){
                for(int j= 0; j < C; j++){
                    if(dfs(M,i,j)) cnt++;
                }
            }

            System.out.println(cnt);

        }

    }
}
