import java.util.*;

public class Main {

    static class Coord{
        int x;
        int y;
        int z;

        Coord(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    static boolean checked(int x, int y, int z, int r, int c, int h){
        return 0 <= x && x < r && 0 <=  y && y < c && 0<= z && z <h;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int R = sc.nextInt();
        int H = sc.nextInt();

        int cnt = 0;
        int day= 0;

        sc.nextLine();
        int map[][][] = new int[H][R][C];
        boolean visited[][][] = new boolean[H][R][C];

        Queue<Coord> q = new ArrayDeque();

        for(int h = 0; h < H; h++) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    map[h][r][c] = sc.nextInt();
                    if (map[h][r][c] == 1) {
                        q.add(new Coord(r, c, h));
                        visited[h][r][c] = true;
                        cnt++;
                    }
                    if (map[h][r][c] == -1){
                        cnt++;
                    }
                }
            }
        }

        int[] dx = {-1,0,1,0,0,0};
        int[] dy = {0,1,0,-1,0,0};
        int[] dz = {0,0,0,0,1,-1};

        while(!q.isEmpty()){
            Coord p = q.poll();

            for(int i =0; i < dx.length; i++){
                int nw_x = p.x + dx[i];
                int nw_y = p.y + dy[i];
                int nw_z = p.z + dz[i];

                if(checked(nw_x, nw_y, nw_z, R, C, H) && map[nw_z][nw_x][nw_y] != -1){
                    if(!visited[nw_z][nw_x][nw_y]){
                        q.add(new Coord(nw_x, nw_y, nw_z));
                        visited[nw_z][nw_x][nw_y] = true;
                        map[nw_z][nw_x][nw_y] = map[p.z][p.x][p.y]+1;
                        day = map[nw_z][nw_x][nw_y];
                        cnt++;
                    }else{
                        map[nw_z][nw_x][nw_y] = Math.min(map[nw_z][nw_x][nw_y], map[p.z][p.x][p.y]+1);
                    }

                }
            }

        }

        if(cnt != R*C*H) day = -1;
        System.out.println(day <= 0 ? day : day-1);

    }
}
