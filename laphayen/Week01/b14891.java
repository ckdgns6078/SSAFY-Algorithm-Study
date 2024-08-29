import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        /*
         * 톱니바퀴는 4개로 고정 각각의 바퀴는 8개의 1과 0으로 구성
         * 1. 처음에 바퀴 값 입력
         * 2. 회전 값 입력
         *         - 방향에 맞춰서 회전
         * 
         *           0            1         2          3          
         * 
         *         - 7  0  1     7  0  1  7  0  1  7  0  1 
         *           6        2    6      2  6       2  6        2
         *           5  4  3   5  4  3  5  4  3  5  4  3    
         * 
         * 
         * -----------------------------------------------
         *         - 일단 본인 먼저 돌고
         *         - 인접 검사해서 돈다.
         * -----------------------------------------------
         * 
         *         - 회전 시킨 바퀴 번호의 인접한 바퀴의 인덱스의 값이 같은 값인지 판별
         *         - 다르면 회전
         *     
         * 
         *          - 연쇄적으로 회전 -> 3번 회전일경우 3-> 2->1 ->
         *         -                  0번 회전일 경우 ->1 -> 2-> 3
         *         - while문으로 0이 될때까지 왼쪽 회전 검사 -> 같다면 탈출.
         *         - 2[6] != 1[2] -> 1번 회전
         *         - 1[6] != 0[2] -> 0번 회전
         *         - circleN[6] != (circleN-1)[2]:
         *                 // (반대 방향으로 회전-현재 방향 * -1)
         *                 rotation(circleN-1, dir *-1);
         *         - circleN[6] == (circleN-1)[2]:
         *                 // 같은 값이면 탈출.
         * 
         * 
         *         - while문으로 3이 될때까지 오른쪽 검사  -> 같다면 탈출.
         *         - n[2] != n+1[6]:
         *                 n+1배열 회전
         *                 n 증가
         * 
         *         - 줄의 위치를 받아서 
         *         - 시계 방향으로 회전해주는 함수 dir = 1
         *         - 반시계 방향으로 회전해주는 함수 dir = -1
         */

        // 입력부 -----------------------------------
        map = new int[4][8];
        int result = 0;

        for (int r = 0; r < 4; r++) {
            String input = sc.nextLine();
            for (int c = 0; c < 8; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }

        // 회전부 -----------------------------------
        int k = sc.nextInt();
        for (int kc = 0; kc < k; kc++) {
            int orderN = sc.nextInt();
            int dir = sc.nextInt();
            
            int [] chk = new int [4];
            chk[orderN-1] = dir;
            
            // 왼쪽 검사
            int orderL = orderN-1;
            int dirL = dir * (-1);
            while (orderL -1 >= 0) {
                if (map[orderL-1][2] == map[orderL][6]) {
                    break;
                }
                chk[orderL-1] = dirL;
                dirL = dirL * (-1);
                orderL -= 1;
            }
            
            // 오른쪽 검사
            int orderR = orderN-1;
            int dirR = dir * (-1);
            while (orderR +1 <= 3) {
                if (map[orderR][2] == map[orderR+1][6]) {
                    break;
                }
                
                chk[orderR+1] = dirR;
                dirR = dirR * (-1);
                orderR += 1;
            }
            
            // 회전
            for (int i = 0; i < chk.length; i++) {
                if (chk[i] == 1) {
                    rRotate(i);
                }
                else if (chk[i] == -1) {
                    lRotate(i);
                }
            }

        }

        // 출력부 --------------
        int s = 1;
        for (int r = 0; r < 4; r++) {
            if (map[r][0] == 1) {
                result += s;
            }
            s *= 2;
        }
        System.out.println(result);

    }

    // 반 시계 방향 왼쪽으로 회전하는 함수.
    private static void lRotate(int r) {
        int temp = map[r][0];
        for (int i = 0; i < 7; i++) {
            map[r][i] = map[r][i + 1];
        }
        map[r][7] = temp;

    }

    // 시계방향 오른쪽으로 회전해주는 함수.
    private static void rRotate(int r) {
        int temp = map[r][7];
        for (int i = 7; i > 0; i--) {
            map[r][i] = map[r][i - 1];
        }
        map[r][0] = temp;
    }
}
