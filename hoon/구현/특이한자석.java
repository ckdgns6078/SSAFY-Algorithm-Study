import java.util.*;
 
public class Solution {
    static int[][] gear;
    static int k;
    static int[][] cycle;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        /*
         * 12시 방향부터 입력을 저장한다. 비교하는 숫자는 2번과 옆에 있는 6번을 비교한다. 1은 시계방향 , -1은 반시계 방향 N극은 0
         * S극은 1 1번 N 1 , 2번 N 2 , 3번 N 4 , 4번 N 8
         */
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            k = sc.nextInt();
            cycle = new int[k][2];
            gear = new int[5][8];
 
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    gear[i][j] = sc.nextInt();
                }
            }
             
            for(int i = 0 ; i < k ;i++) {
                for(int j = 0 ; j < 2 ; j++) {
                    cycle[i][j] = sc.nextInt();
                }
            }
             
             
            //실행하는 함수
            for(int i = 0 ; i < k ; i++) {
                int gearNum = cycle[i][0];
                int direction = cycle[i][1];
                visited=new boolean[5];
                //검사하는 코드
                findMoveGear(gearNum , direction , visited);
                 
            }
             
            int result=0;
            /*
             * s극은 1
             * 1번 s극이면 1점
             * 2번 s극이면 2점
             * 3번 s극이면 4점
             * 4번 s극이면 8점
             */
             
            result+= gear[1][0]== 1 ? 1 : 0;
            result+= gear[2][0]== 1 ? 2 : 0;
            result+= gear[3][0]== 1 ? 4 : 0;
            result+= gear[4][0]== 1 ? 8 : 0;
             
            System.out.println("#"+tc + " " + result);
 
        }
    }
     
     
    //방향은 어떻게 저장을 하는가?
    private static void findMoveGear(int gearNum, int direction , boolean[] visited) {
        if(gearNum ==0 || gearNum >4) {
            return;
        }
        int changeDir = direction==1 ? -1 : 1;
        visited[gearNum] = true;
         
        // 2->6검사    6->2 검사 
         
        //더하기 검사
        if(gearNum+1<5 && !visited[gearNum+1] && gear[gearNum][2] != gear[gearNum+1][6]) {
            findMoveGear(gearNum+1 , changeDir , visited);
        }
        if(gearNum-1>0 && !visited[gearNum-1] && gear[gearNum][6] != gear[gearNum-1][2]) {
            findMoveGear(gearNum-1 , changeDir , visited);
        }
        //자기자신 위치 변경
        rotate(gearNum , direction);
         
    }
 
 
 
    //회전함수 시계방향 = 1 , 반 시계 방향 = -1
    public static void rotate(int gearNumber , int direction) {
        //마지막 값이 1로 바뀐다
        int cnt = 0;
        if(direction == 1) { //시계방향 ㅇ회전
            cnt =gear[gearNumber][7];
            for(int i = 7 ; i >0 ; i--) {
                gear[gearNumber][i] = gear[gearNumber][i-1];
            }
            gear[gearNumber][0] = cnt;
             
        }else { //반시계방향 회전
            cnt = gear[gearNumber][0];
            for(int i = 0  ; i < 7 ; i++) {
                gear[gearNumber][i] = gear[gearNumber][i+1];
            }
            gear[gearNumber][7]=cnt;
        }
    }
     
 
}
