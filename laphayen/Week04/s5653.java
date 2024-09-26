import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 초기 세포 배열의 행 개수
            int M = sc.nextInt(); // 초기 세포 배열의 열 개수
            int K = sc.nextInt(); // 경과 시간

            // 넉넉하게 배열을 선언
            int[][][] brd = new int[N + K * 2][M + K * 2][2]; // 생명력 배열 [현재 생명력, 활성화 타이머]

            int[][] inputCells = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    inputCells[i][j] = sc.nextInt();
                }
            }

            List<int[]> cells = new ArrayList<>(); // 세포의 위치 저장
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    int X = inputCells[r][c];
                    if (X > 0) {
                        brd[K + r][K + c][0] = X;
                        brd[K + r][K + c][1] = X;
                        cells.add(new int[]{K + r, K + c});
                    }
                }
            }

            // 경과 시간 K 동안 세포가 활성화되고 분열
            for (int time = 0; time < K; time++) {
                List<int[]> newCells = new ArrayList<>(); // 새로 태어날 세포들 저장

                for (int[] cell : cells) {
                    int r = cell[0], c = cell[1];

                    // 아직 활성화되지 않은 세포일 경우
                    if (brd[r][c][1] > 0) {
                        brd[r][c][1]--;
                    }
                    // 활성화된 세포의 경우 분열 시작
                    else if (brd[r][c][1] == 0) {
                        int X = brd[r][c][0]; // 세포의 생명력
                        // 상하좌우로 분열 가능
                        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
                        for (int[] d : directions) {
                            int newR = r + d[0];
                            int newC = c + d[1];
                            if (brd[newR][newC][0] == 0) {
                                newCells.add(new int[]{newR, newC, X});
                            }
                        }
                        // 세포 생명력 감소
                        brd[r][c][1]--;
                        brd[r][c][0]--;
                    }
                    // 활성화 후 생명력 감소
                    else {
                        if (brd[r][c][0] > 0) {
                            brd[r][c][0]--;
                        }
                    }
                }

                // 새롭게 태어난 세포들 배열에 추가
                for (int[] newCell : newCells) {
                    int r = newCell[0], c = newCell[1], X = newCell[2];
                    if (brd[r][c][0] == 0) {
                        brd[r][c][0] = X;
                        brd[r][c][1] = X;
                        cells.add(new int[]{r, c});
                    } else {
                        // 생명력이 더 센 세포가 자리를 차지
                        if (brd[r][c][0] < X) {
                            brd[r][c][0] = X;
                            brd[r][c][1] = X;
                        }
                    }
                }
            }

            // 살아남은 세포 개수 세기
            int answer = 0;
            for (int r = 0; r < brd.length; r++) {
                for (int c = 0; c < brd[0].length; c++) {
                    if (brd[r][c][0] > 0) {
                        answer++;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}
