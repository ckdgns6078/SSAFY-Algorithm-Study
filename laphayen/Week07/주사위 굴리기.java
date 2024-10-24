import java.util.Scanner;

public class Main {
    static int n, m, x, y, k;
    static int[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = {0, 0, 0, 0, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        k = sc.nextInt();

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int[] comm = new int[k];
        for (int i = 0; i < k; i++) {
            comm[i] = sc.nextInt();
        }

        int nx = x;
        int ny = y;

        for (int i = 0; i < k; i++) {
            int dir = comm[i];
            nx += dx[dir - 1];
            ny += dy[dir - 1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                nx -= dx[dir - 1];
                ny -= dy[dir - 1];
                continue;
            }

            turn(dir);

            if (board[nx][ny] == 0) {
                board[nx][ny] = dice[5];
            } else {
                dice[5] = board[nx][ny];
                board[nx][ny] = 0;
            }

            // 주사위 윗면 출력
            System.out.println(dice[0]);
        }

        sc.close();
    }

    static void turn(int dir) {
        int a = dice[0], b = dice[1], c = dice[2], d = dice[3], e = dice[4], f = dice[5];

        switch (dir) {
            case 1: // 동
                dice[0] = d;
                dice[1] = b;
                dice[2] = a;
                dice[3] = f;
                dice[4] = e;
                dice[5] = c;
                break;

            case 2: // 서
                dice[0] = c;
                dice[1] = b;
                dice[2] = f;
                dice[3] = a;
                dice[4] = e;
                dice[5] = d;
                break;

            case 3: // 북
                dice[0] = e;
                dice[1] = a;
                dice[2] = c;
                dice[3] = d;
                dice[4] = f;
                dice[5] = b;
                break;

            case 4: // 남
                dice[0] = b;
                dice[1] = f;
                dice[2] = c;
                dice[3] = d;
                dice[4] = a;
                dice[5] = e;
                break;
        }
    }
}
