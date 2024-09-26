import java.util.*;

public class Solution {
    static int W, H, totalBlocks, brokenBlocks;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 수

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 구슬을 쏘는 횟수
            W = sc.nextInt(); // 가로
            H = sc.nextInt(); // 세로
            int[][] blockMaps = new int[H][W];
            totalBlocks = 0;

            // 블록 정보 입력
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    blockMaps[i][j] = sc.nextInt();
                    if (blockMaps[i][j] > 0) {
                        totalBlocks++;
                    }
                }
            }

            brokenBlocks = 0; // 부순 벽돌의 초기값
            comb(N, 0, blockMaps);

            int answer = totalBlocks - brokenBlocks;
            System.out.println("#" + test_case + " " + answer);
        }
        sc.close();
    }

    // 구슬 쏘기
    static void comb(int cnt, int total, int[][] blockMaps) {
        // 벽돌을 모두 깬 경우
        if (total == totalBlocks) {
            brokenBlocks = total;
            return;
        }

        // 구슬을 다 쏘면 더 많이 부순 경우를 기록
        if (cnt == 0) {
            if (total > brokenBlocks) {
                brokenBlocks = total;
            }
            return;
        }

        // 구슬을 쏘기 위한 반복
        for (int i = 0; i < W; i++) {
            // 게임판을 복사
            int[][] arr = copyArray(blockMaps);
            Set<int[]> stack = new HashSet<>(); // 좌표를 저장할 set
            int blocks = 0;

            // 해당 열에서 최상단 블록을 찾기
            for (int row = 0; row < H; row++) {
                if (arr[row][i] > 0) {
                    stack.add(new int[]{row, i});
                    break;
                }
            }

            // 스택이 비어있지 않으면
            while (!stack.isEmpty()) {
                int[] current = stack.iterator().next(); // stack에서 꺼내기
                stack.remove(current);
                int row = current[0], col = current[1];

                // 빈 공간이면 넘어간다
                if (arr[row][col] == 0) continue;

                blocks++; // 벽돌 깨기

                // 4방향 벡터로 폭발 처리
                for (int k = 0; k < 4; k++) {
                    int nextRow = row, nextCol = col;
                    for (int j = 1; j < arr[row][col]; j++) {
                        nextRow += dx[k];
                        nextCol += dy[k];
                        if (0 <= nextRow && nextRow < H && 0 <= nextCol && nextCol < W && arr[nextRow][nextCol] > 0) {
                            stack.add(new int[]{nextRow, nextCol});
                        }
                    }
                }

                // 현재 벽돌 제거
                arr[row][col] = 0;
            }

            // 중력 처리
            for (int y = 0; y < W; y++) {
                int idx = H - 1;
                for (int x = H - 1; x >= 0; x--) {
                    if (arr[x][y] > 0) {
                        arr[idx][y] = arr[x][y];
                        if (idx != x) arr[x][y] = 0;
                        idx--;
                    }
                }
            }

            // 재귀 호출로 다음 구슬 처리
            comb(cnt - 1, total + blocks, arr);
        }
    }

    // 2차원 배열 복사 함수
    static int[][] copyArray(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
}
