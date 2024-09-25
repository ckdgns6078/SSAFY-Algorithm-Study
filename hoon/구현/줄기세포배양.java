import java.util.*;
 
class Cell implements Comparable<Cell> {
    int r;
    int c;
    int time;
    int life;
 
    Cell(int r, int c, int time, int life) {
        this.r = r;
        this.c = c;
        this.time = time;
        this.life = life;
    }
 
    @Override
    public int compareTo(Cell o) {
 
        return Integer.compare(o.life, this.life);
    }
 
}
 
public class Solution {
    static int n;
    static int m;
    static int k;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static PriorityQueue<Cell> q;
    static ArrayList<Cell> cellList;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
 
            map = new int[n + k + 2][m + k + 2];
            visited = new boolean[n + k + 2][m + k + 2];
            q = new PriorityQueue();
            cellList = new ArrayList();
            // 입력부
            for (int i = k / 2 + 1; i < n + k / 2 + 1; i++) {
                for (int j = k / 2 + 1; j < m + k / 2 + 1; j++) {
                    int input = sc.nextInt();
                    if (input != 0) {
                        map[i][j] = input;
                        visited[i][j] = true;
                        cellList.add(new Cell(i, j, input, input));
 
                    }
                }
            }
            findCell();
            int result = 0;
            for(int i=0; i < cellList.size(); i++) {
                //cell Time + life >=k
                int cnt = cellList.get(i).time;
                int life = cellList.get(i).life;
                 
                if(cnt+life> k) {
                    result++;
                }   
                 
            }
             
            System.out.println("#" + tc + " "+result);
        }
    }
 
    private static void findCell() {
        int time = 1;
 
        while (time <= k) {
 
            for (int i = 0; i < cellList.size(); i++) {
                Cell cell = cellList.get(i);
                if (cell.time == time-1) {
                    int r = cell.r;
                    int c = cell.c;
                    int life = cell.life;
 
                    for (int j = 0; j < 4; j++) {
                        int nr = r + dr[j];
                        int nc = c + dc[j];
                        if (nr >= 0 && nr < n + k + 2 && nc >= 0 && nc < m + k + 2 && !visited[nr][nc]) {
                            q.offer(new Cell(nr, nc, time + life, life));
                        }
                    }
                }
 
            }
            while (!q.isEmpty()) {
                Cell cell = q.poll();
                int r = cell.r;
                int c = cell.c;
                int life = cell.life;
                if (map[r][c] < life) {
                    map[r][c] = life;
                    cellList.add(cell);
                    visited[r][c] = true;
                }
            }
            time++;
        }
 
    }
 
}
