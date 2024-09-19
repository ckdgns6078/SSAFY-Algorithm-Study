import java.util.*;

class Solution {
    static class Visitor {
        int receptionDesk;  // 접수 창구 번호
        int repairDesk;     // 정비 창구 번호

        Visitor() {
            this.receptionDesk = -1;
            this.repairDesk = -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();  // 접수 창구 개수
            int M = sc.nextInt();  // 정비 창구 개수
            int K = sc.nextInt();  // 방문 고객 수
            int A = sc.nextInt();  // 지갑 두고 간 사람이 접수
            int B = sc.nextInt();  // 지갑 두고 간 사람이 정비

            int[] a = new int[N];  // 각 접수
            int[] b = new int[M];  // 각 정비
            int[] t = new int[K];  // 각 고객

            for (int i = 0; i < N; i++) a[i] = sc.nextInt();
            for (int i = 0; i < M; i++) b[i] = sc.nextInt();
            for (int i = 0; i < K; i++) t[i] = sc.nextInt();

            Visitor[] visitorInfo = new Visitor[K];
            for (int i = 0; i < K; i++) {
                visitorInfo[i] = new Visitor();
            }

            int[] receptionDesks = new int[N];
            int[] receptionTimes = new int[N];
            Arrays.fill(receptionDesks, -1);    // -1 비어있음.

            int[] repairDesks = new int[M];
            int[] repairTimes = new int[M];     // 남은 시간 관리
            Arrays.fill(repairDesks, -1);

            Queue<Integer> receptionWaitingLine = new LinkedList<>();
            Queue<Integer> repairWaitingLine = new LinkedList<>();

            int time = 0;
            int tIdx = 0;
            boolean flag = true;

            while (flag) {
                // 정비 창구에서 볼일 다 본 사람 out
                for (int i = 0; i < M; i++) {
                    if (repairDesks[i] != -1 && repairTimes[i] == 0) {
                        repairDesks[i] = -1;
                    }
                }

                // 1. 접수 창구에서 볼일 다 본 사람 정비 창구 대기열로 옮기기
                for (int i = 0; i < N; i++) {
                    if (receptionDesks[i] != -1 && receptionTimes[i] == 0) {
                        repairWaitingLine.add(receptionDesks[i]);
                        receptionDesks[i] = -1;
                    }
                }

                // 2. 이번 시간에 들어온 사람 접수 창구 대기열로 옮기기
                while (tIdx < K && t[tIdx] == time) {
                    receptionWaitingLine.add(tIdx);
                    tIdx++;
                }

                // 3. 정비 창구 비어있는 곳에 대기열 사람 옮기기
                for (int i = 0; i < M; i++) {
                    if (repairDesks[i] == -1 && !repairWaitingLine.isEmpty()) {
                        int visitorIdx = repairWaitingLine.poll();
                        repairDesks[i] = visitorIdx;
                        repairTimes[i] = b[i] - 1;
                        visitorInfo[visitorIdx].repairDesk = i;
                    } else if (repairDesks[i] != -1) {
                        repairTimes[i]--;
                    }
                }

                // 4. 접수 창구 비어있는 곳에 대기열 사람 옮기기
                for (int i = 0; i < N; i++) {
                    if (receptionDesks[i] == -1 && !receptionWaitingLine.isEmpty()) {
                        int visitorIdx = receptionWaitingLine.poll();
                        receptionDesks[i] = visitorIdx;
                        receptionTimes[i] = a[i] - 1;
                        visitorInfo[visitorIdx].receptionDesk = i;
                    } else if (receptionDesks[i] != -1) {
                        receptionTimes[i]--;
                    }
                }

                time++;
                
                if (time > K) {
                    boolean allVisited = true;
                    for (int i = 0; i < K; i++) {
                        if (visitorInfo[i].repairDesk == -1) {
                            allVisited = false;
                            break;
                        }
                    }
                    if (allVisited) {
                        flag = false;
                    }
                }
            }

            int answer = 0;
            for (int i = 0; i < K; i++) {
                if (visitorInfo[i].receptionDesk == A - 1 && visitorInfo[i].repairDesk == B - 1) {
                    answer += (i + 1);
                }
            }

            if (answer == 0) answer = -1;

            System.out.printf("#%d %d%n", tc, answer);
        }

        sc.close();
    }
}
