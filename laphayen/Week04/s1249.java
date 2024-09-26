import java.util.*;

public class Solution {

    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동을 위한 배열
    static int[] dy = {0, 0, -1, 1};

    public static void bfs(int[][] arr, int[][] visited, int[][] time, int N) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0}); // 시작점 (0, 0)
        visited[0][0] = 1; // 시작점 방문 표시

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int k = 0; k < 4; k++) { // 상하좌우 탐색
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) { // 범위 체크
                    if (nx == 0 && ny == 0) continue; // 시작점은 다시 방문하지 않음

                    if (visited[nx][ny] == 0) { // 아직 방문하지 않은 경우
                        visited[nx][ny] = 1;
                        time[nx][ny] = time[x][y] + arr[nx][ny]; // 누적 시간 계산
                        queue.add(new int[]{nx, ny});
                    } else {
                        // 더 짧은 경로가 있을 경우 업데이트
                        if (time[nx][ny] > time[x][y] + arr[nx][ny]) {
                            time[nx][ny] = time[x][y] + arr[nx][ny];
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 배열의 크기
            int[][] arr = new int[N][N]; // 비용 배열
            int[][] visited = new int[N][N]; // 방문 기록 배열
            int[][] time = new int[N][N]; // 최소 시간을 기록할 배열

            // 입력 받기
            for (int i = 0; i < N; i++) {
                String line = sc.next(); // 입력을 한 줄씩 읽음
                for (int j = 0; j < N; j++) {
                    arr[i][j] = line.charAt(j) - '0'; // 문자를 숫자로 변환하여 배열에 저장
                }
            }

            bfs(arr, visited, time, N); // BFS 탐색 시작

            // 결과 출력 (마지막 위치까지 가는 최소 시간)
            System.out.println("#" + tc + " " + time[N - 1][N - 1]);
        }

        sc.close();
    }
}
