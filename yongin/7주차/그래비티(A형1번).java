package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그래비티 {
    static int[][] arr;
    static int N;

    static void func() {
        for (int c = 0; c < N; c++) {
            if (arr[0][c] == 0) // 한 row 맨 위가 0인 경우 continue
                continue;
            if (arr[0][c] == 1 && arr[1][c] == 1) // 한 row 맨 위가 1 이고 바로 그 밑에가 1인 경우 continue
                continue;
            // 맨 위 블럭이 있는 경우
            double curPower = 1; // 맨 위 블럭 파워 1로 초기화
            int blockCnt = 1; // 현재 블럭 갯수 1로 초기화
            for (int r = 1; r < N; r++) {
                // 다음 블럭이 0 이면 현재 파워 * 1.9
                if (arr[r][c] == 0) {
                    curPower *= 1.9;
                }
                // 다음 블럭이 1이면
                if (arr[r][c] == 1) {
                    int nextPower = 0; // 다음 블럭의 저항력 0으로 초기화
                    for (int i = r; i < N; i++) {
                        // 해당 위치의 블럭 '저항' 갱신
                        if (arr[i][c] == 0) // 0이면 break
                            break;

                        if (arr[i][c] == 1) { // 1이면 
                            nextPower++; // 저항 + 1
                            blockCnt++; // 블럭갯수 + 1
                        }
                    }
                    r+=nextPower-1; // 방문한 저항을 방문하지 않기 위해 r의 값을 저항-1을 더해줌
                    
                    // 해당 위치의 위부터 해당위치-(블럭갯수-저항) -> 저항을 증가시키면서 블럭갯수도 같이 + 1 해줬기 때문
//                    System.out.println("r: "+r+", c: "+c+", blockCnt: "+blockCnt+", nextPower: "+nextPower);
                    for (int i = r-1; i > r - (blockCnt - nextPower); i--) {
                        arr[i][c] = 1;
                    }
                    for (int i = r - (blockCnt - nextPower )-3; i >= 0; i--) {
                        arr[i][c] = 0;
                    }
                    
                    if (curPower > nextPower) {
                        curPower += nextPower;
                    }
                    if (curPower <= nextPower) {
                        break;
                    }
                }
                
                if(r==N-1) {
                    for (int i = N-1; i > N-1-blockCnt; i--) {
                        arr[i][c] = 1;
                    }
                    for (int i = N-1-blockCnt; i >= 0; i--) {
                        arr[i][c] = 0;
                    }
                    break;
                }
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 1; t < 11; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            func();

            int[][] arr2 = new int[N][N];
            int rIdx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr2[j][i] = arr[i][j];  
                }
                rIdx--;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = arr2[i][j];
                }
            }
            func();
            
            int colNumber = 0;
            int rowNumber = 0;
            for (int i = 0; i < N; i++) {
                if(arr[N-1][i]==1) colNumber++;
                if(arr[i][N-1]==1) rowNumber++;
            }
            System.out.println("#"+t+ " " + rowNumber + " " + colNumber);
        }

    }

}
