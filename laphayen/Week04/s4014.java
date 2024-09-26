import java.util.Scanner;

public class Solution {
    static int N, X;

    // 경사로 설치 가능 여부 체크 함수
    public static int checkSlope(int[] row) {
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (row[i] == row[i - 1]) { // 높이가 같다면
                cnt++;
            } else if (row[i] - row[i - 1] == 1 && cnt >= X) { // 높이가 1 높아지는 경우
                cnt = 1;
            } else if (row[i - 1] - row[i] == 1 && cnt >= 0) { // 높이가 1 낮아지는 경우
                cnt = -X + 1;
            } else { // 높이 차이가 2 이상일 경우
                return 0;
            }
        }
        return cnt >= 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 배열 크기
            X = sc.nextInt(); // 경사로 길이

            int[][] A = new int[N][N]; // 지도 배열
            int result = 0;

            // 배열 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    A[i][j] = sc.nextInt();
                }
            }

            // 행 체크
            for (int i = 0; i < N; i++) {
                result += checkSlope(A[i]);
            }

            // 열 체크
            for (int i = 0; i < N; i++) {
                int[] temp = new int[N];
                for (int j = 0; j < N; j++) {
                    temp[j] = A[j][i]; // 열을 행처럼 저장하여 체크
                }
                result += checkSlope(temp);
            }

            // 결과 출력
            System.out.println("#" + tc + " " + result);
        }

        sc.close();
    }
}
