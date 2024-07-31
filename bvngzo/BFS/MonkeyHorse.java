
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static boolean check(int x, int y, int R, int C){
        return x>=0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int C = sc.nextInt();
        int R = sc.nextInt();

        int[][] M = new int[R][C];
        int[][][] V = new int[K+1][R][C]; // 그냥 갔을 때 경로, 말의 이동으로 갔을 때 , 말 이동 횟수

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int[] hx = {2,2, -1, 1, -2, -2, -1, 1};
        int[] hy = {-1,1, 2, 2, -1, 1, -2, -2};

        for(int i =0; i < R; i++){
            for(int j = 0; j < C; j++){
                M[i][j] = -sc.nextInt();
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,0});            // x,y,k

        for(int i = 0; i < K+1; i++){
            V[i][0][0] = 1;
        }

        while(!q.isEmpty()){
            int[] data = q.poll();
            int k = data[0], x = data[1], y = data[2];

            if(k < K){
                for(int i =0; i < hx.length; i++){
                    int nx = x + hx[i];
                    int ny = y + hy[i];

                    if(check(nx,ny,R,C) && M[nx][ny] != -1){
                        if(V[k+1][nx][ny] == 0){
                            V[k+1][nx][ny] = V[k][x][y] + 1;
                            q.offer(new int[] {k+1,nx,ny});
                        }
                    }
                }
            }
            for(int i = 0; i < dx.length; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(check(nx, ny, R, C) && M[nx][ny] != -1){
                    if(V[k][nx][ny] == 0){
                        V[k][nx][ny] = V[k][x][y] + 1;
                        q.offer(new int[] {k,nx,ny});
                    }
                }
            }

        }//while
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < K+1; i++){
            answer = (V[i][R-1][C-1] == 0) ? answer : Math.min(V[i][R-1][C-1], answer);
        }

        if(answer == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer-1);
        




    }
}
