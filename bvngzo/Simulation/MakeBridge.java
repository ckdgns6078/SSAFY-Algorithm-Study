import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[][] M;
    static int N;
    static int bridge = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean checked(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void print(int[][] M){
        for(int[] arr : M){
            for(int i : arr){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    static boolean dfs(int v, int r, int c){
        if(!checked(r,c) || M[r][c] != 1) return false;

        M[r][c] = v;
        for(int i = 0; i < dx.length; i++){
            int nr = r + dx[i];
            int nc = c + dy[i];
            dfs(v,nr,nc);
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = new int[N][N];

        for(int i = 0; i <N; i++){
            for(int j = 0; j < N; j++){
                M[i][j] = sc.nextInt();
            }
        }

        /// 섬 마킹
        int v = -1;
        for(int i = 0 ; i<N; i++){
            for(int j = 0; j < N; j++){
                if(dfs(v, i, j)){
                    v--;
                }
            }
        }

        int numberOfIsland = -v;
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 1; i <= numberOfIsland; i++){
            int[][] dist = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            for (int x = 0; x < N; x++){
                for (int y = 0; y < N; y++){
                    if(M[x][y] == -i && !visited[x][y]){
                        q.offer(new int[]{x,y});
                        visited[x][y] = true;

                        while(!q.isEmpty()){
                            int[] coord = q.poll();
                            int curr_x = coord[0];
                            int curr_y = coord[1];

                            for(int d = 0; d < dx.length; d++){
                                int nx = curr_x + dx[d];
                                int ny = curr_y + dy[d];

                                if(checked(nx,ny)){
                                    if(M[nx][ny] == -i){ // 같은 섬 내부
                                        if(!visited[nx][ny]) {
                                            q.offer(new int[]{nx, ny});
                                            visited[nx][ny] = true;
                                        }
                                    }
                                    else if(M[nx][ny] == 0){    // 바다
                                        if(dist[nx][ny] == 0) {
                                            q.offer(new int[]{nx,ny});
                                            dist[nx][ny] = (M[curr_x][curr_y] == -i) ? 1 :dist[curr_x][curr_y]+1;
                                        }else{
                                            dist[nx][ny] = Math.min(dist[curr_x][curr_y]+1, dist[nx][ny]);
                                        }
                                    }
                                    else if (M[nx][ny] < 0) {   //다른 섬 도착
                                        bridge = Math.min(bridge, dist[curr_x][curr_y]);
                                    }
                                }// end if
                            }// end for dx dy
                        }//end while
                    }
                }
            }

        }//end for all

        System.out.println(bridge);
    }
}
