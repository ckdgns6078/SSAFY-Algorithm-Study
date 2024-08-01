import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static char[][] M;

    static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean Check(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();

        int cnt = 0;
        boolean exit = false;

        M = new char[R][C];
        Queue<Coord> Fs = new LinkedList<>();
        Queue<Coord> Js = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String input = sc.next();
            for (int j = 0; j < C; j++) {
                M[i][j] = input.charAt(j);
                if(M[i][j] == 'J'){
                    Js.offer(new Coord(i,j));
                }else if(M[i][j] == 'F'){
                    Fs.offer(new Coord(i,j));
                }
            }
        }

        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        L:while(!Js.isEmpty()){

            int n_J = Js.size();

            for(int i = 0; i <n_J; i++) {
                Coord J = Js.poll();

                for (int d = 0; d < dx.length; d++) {
                    int nw_jx = J.x + dx[d];
                    int nw_jy = J.y + dy[d];

                    if (nw_jx < 0 || nw_jx >= R || nw_jy < 0 || nw_jy >= C) {
                        cnt++;
                        exit = true;
                        break L;
                    }

                    if (Check(nw_jx, nw_jy, R, C) && M[nw_jx][nw_jy] == '.') {
                        M[nw_jx][nw_jy] = 'J';
                        Js.offer(new Coord(nw_jx, nw_jy));
                    }
                }
            }
            cnt++;

            int n_f = Fs.size();

            for(int i = 0; i <n_f; i++) {
                Coord F = Fs.poll();

                for (int d = 0; d < dx.length; d++) {
                    int nw_fx = F.x + dx[d];
                    int nw_fy = F.y + dy[d];

                    if (Check(nw_fx, nw_fy, R, C) && (M[nw_fx][nw_fy] == '.' || M[nw_fx][nw_fy] == 'J')) {
                        if(M[nw_fx][nw_fy] == 'J'){
                            Js.removeIf(j->j.x == nw_fx && j.y == nw_fy);
                        }
                        M[nw_fx][nw_fy] = 'F';
                        Fs.offer(new Coord(nw_fx, nw_fy));
                    }
                }
            }

        }

    if(exit) System.out.println(cnt);
    else System.out.println("IMPOSSIBLE");


    }
}
