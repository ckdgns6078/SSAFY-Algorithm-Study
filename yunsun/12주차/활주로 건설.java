import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int X = sc.nextInt();
            int[][] map = new int[N][N];
            int answer = 0;
            int ramp = 0;
            int prev, count;
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
 
            // 행 기준
            L: for (int i = 0; i < N; i++) {
                // 행의 첫번째 칸 (가장 왼쪽 칸)
                prev = map[i][0];
                count = 1;
                ramp = 0;
                for (int j = 1; j < N; j++) {
                    // 경사가 높아지면
                    if (map[i][j] > prev) {
                        // 높이 차가 1 초과이면 경사로 설치 불가 -> 활주로 건설 불가
                        if (map[i][j] - prev > 1)
                            continue L;
 
                        // 아직 경사로를 설치할 구간에 있으면
                        if (ramp != 0) {
                            continue L;
                        }
 
                        // 아래 구간이 경사로 길이보다 짧음 -> 활주로 건설 불가
                        if (count < X)
                            continue L;
 
                        count = 1;   // 높이가 올라왔으니 (올라가는) 경사로를 위한 공간 초기화
                    }
                    // 경사가 낮아지면
                    else if (map[i][j] < prev) {
                        // 높이 차가 1 초과이면 경사로 설치 불가 -> 활주로 건설 불가
                        if (prev - map[i][j] > 1)
                            continue L;
 
                        // 아직 경사로를 설치할 구간에 있으면
                        if (ramp != 0) {
                            continue L;
                        }
 
                        count = 0; // 지금까지 위쪽 높이 칸 세온 것은 의미 없으므로 초기화
 
                        if (j + X - 1 >= N) continue L;   // 경사로가 지형을 벗어남 -> 활주로 건설 불가
                         
                        // 경사로 설치를 위해 앞으로 X칸만큼 count를 0으로 고정한다.
                        ramp = X;
                        ramp--;
 
                        // 이렇게 코드를 작성했더니 경사로를 겹쳐서 사용할 수 없는 조건에 부합하지 않음
                        // 경사로를 설치할 구간이 끝날 때까지 count가 0으로 유지되어야 하는데
                        // 이 코드는 경사로 길이가 몇이든 첫번째 칸에서 검사를 끝내버리고 그 이후는 신경을 안쓰기 때문에 안됨
//                      // 경사로 설치할 구간이 부족함 -> 활주로 건설 불가
//                      for (int k = 1; k < X; k++) {
//                          if (j+k >= N)   // 지형을 초과함
//                              continue L;
//                          else if (map[i][j+k] != map[i][j])   // 경사로 설치할 공간이 나오기 전에 높이가 바뀜
//                              continue L;
//                      }
                    }
                    // 높이가 이전 칸과 같으면
                    else {
                        // 아직 경사로를 설치할 구간에 있으면
                        if (ramp != 0) {
                            ramp--;
                        }
                        // 경사로 설치할 구간이 아니면 다음 (올라가는)경사로를 설치할 수 있는 구간을 하나 늘려준다.
                        else {
                            count++;
                        }
                         
                    }
                    prev = map[i][j];
                }
                answer++;
            }
 
            // 열 기준
            L: for (int j = 0; j < N; j++) {
                // 열의 첫번째 칸 (가장 위 칸) 
                prev = map[0][j];
                count = 1;
                ramp = 0;
                for (int i = 1; i < N; i++) {
                    // 경사가 높아지면
                    if (map[i][j] > prev) {
                        // 높이 차가 1 초과이면 경사로 설치 불가 -> 활주로 건설 불가
                        if (map[i][j] - prev > 1)
                            continue L;
 
                        // 아직 경사로를 설치할 구간에 있으면
                        if (ramp != 0) {
                            continue L;
                        }
 
                        // 아래 구간이 경사로 길이보다 짧음 -> 활주로 건설 불가
                        if (count < X)
                            continue L;
 
                        count = 1;   // 높이가 올라왔으니 (올라가는) 경사로를 위한 공간 초기화
                    }
                    // 경사가 낮아지면
                    else if (map[i][j] < prev) {
                        // 높이 차가 1 초과이면 경사로 설치 불가 -> 활주로 건설 불가
                        if (prev - map[i][j] > 1)
                            continue L;
 
                        // 아직 경사로를 설치할 구간에 있으면
                        if (ramp != 0) {
                            continue L;
                        }
 
                        count = 0; // 지금까지 위쪽 높이 칸 세온 것은 의미 없으므로 삭제
 
                        if (i + X - 1 >= N) continue L;   // 경사로가 지형을 벗어남 -> 활주로 건설 불가
                         
                        // 경사로 설치를 위해 앞으로 X칸만큼 count를 0으로 고정한다.
                        ramp = X;
                        ramp--;
                    }
                    // 높이가 이전 칸과 같으면
                    else {
                        // 아직 경사로를 설치할 구간에 있으면
                        if (ramp != 0) {
                            ramp--;
                        }
                        // 경사로 설치할 구간이 아니면 다음 (올라가는)경사로를 설치할 수 있는 구간을 하나 늘려준다.
                        else {
                            count++;
                        }
                    }
                    prev = map[i][j];
                }
                answer++;
            }
            System.out.println("#" + test_case + " " + answer);
        }
        sc.close();
    }
 
}
