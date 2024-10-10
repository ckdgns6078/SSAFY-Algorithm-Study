import java.util.*;
 
public class Solution {
 
    // 방향 벡터 (di, dj)
    static int[] di = {1, 1, -1, -1, 0};
    static int[] dj = {-1, 1, 1, -1, 0};
 
    static int N;
    static int[][] arr;
    static int ans;
    static int startX, startY;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        // 테스트 케이스 수
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
            // 지역의 한 변의 길이 N
            N = sc.nextInt();
            arr = new int[N][N];
 
            // 디저트 배열 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
 
            // 정답 초기화
            ans = -1;
 
            // 범위 확인
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    // 시작 좌표 설정
                    startX = i;
                    startY = j;
                    DFS(0, i, j, new HashSet<>());
                }
            }
 
            // 결과 출력
            System.out.printf("#%d %d\n", tc, ans);
        }
 
        sc.close();
    }
 
    // DFS 메서드
    public static void DFS(int n, int ci, int cj, Set<Integer> visited) {
        // 가지치기: 절반 갔을 때 정답의 두 배보다 작으면 중단
        if (n == 2 && ans >= visited.size() * 2) {
            return;
        }
 
        // 종료 조건
        if (n > 3) {
            return;
        }
 
        // 정답 갱신: 회전 3번, 복귀, 디저트 종류 수 최대
        if (n == 3 && ci == startX && cj == startY && ans < visited.size()) {
            ans = visited.size();
            return;
        }
 
        // 직진 또는 방향 전환
        for (int k = n; k <= n + 1; k++) {
            int ni = ci + di[k];
            int nj = cj + dj[k];
 
            // 범위 내, 중복되지 않으면
            if (ni >= 0 && ni < N && nj >= 0 && nj < N && !visited.contains(arr[ni][nj])) {
                visited.add(arr[ni][nj]);
                DFS(k, ni, nj, visited);
                visited.remove(arr[ni][nj]);
            }
        }
    }
}
