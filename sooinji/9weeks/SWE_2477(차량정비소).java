import java.util.*;

class Desk {
	int num = 0;
	int time = 0;
	Desk(int num, int time) {
		this.num = num;
		this.time = time;
	}
}

public class Solution {
	static int t, n, m, k, a, b;
	static int[] ai;
	static int[] bj;
	static int[] tk;
	static boolean[] ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int test_case = 1; test_case <= t; test_case++) {
			System.out.print("#" + test_case + " ");
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			a = sc.nextInt();
			b = sc.nextInt();
			ai = new int[n + 1];
			bj = new int[m + 1];
			tk = new int[k + 1];
			Desk[] receptionList = new Desk[n +  1];
			Desk[] repairList = new Desk[m + 1];
			ans = new boolean[k + 1];
			Arrays.fill(ans, true);
			for (int i = 1; i <= n; i++) {
				ai[i] = sc.nextInt();
				receptionList[i] = new Desk(0, 0);
			}
			for (int i = 1; i <= m; i++) {
				bj[i] = sc.nextInt();
				repairList[i] = new Desk(0, 0);
			}
			for (int i = 1; i <= k; i++) {
				tk[i] = sc.nextInt();
			}
			int cnt = k;
			int time = 0;
			Queue<Integer> receptionQueue = new ArrayDeque<>();
			Queue<Integer> repairQueue = new ArrayDeque<>();
			while (cnt > 0) {
				// 접수 창구
				// 시간이 됐으면 접수 대기 큐에 넣기
				for (int i = 1; i <= k; i++) {
					if (time == tk[i]) {
						receptionQueue.offer(i);
					}
				}
				// 자리가 있으면 접수 대기 큐에서 이동
				for (int i = 1; i <= n; i++) {
					if (receptionList[i].time == 0 && !receptionQueue.isEmpty()) {
						receptionList[i].num = receptionQueue.poll();
						receptionList[i].time = ai[i];
						if (i != a) {
							ans[receptionList[i].num] = false;
						}
					}
				}
				// 시간 하나씩 감소 (0이 되면 수리 대기 큐에 넣기)
				for (int i = 1; i <= n; i++) {
					if (receptionList[i].time == 1) {
						receptionList[i].time = 0;
						repairQueue.offer(receptionList[i].num);
					}
					else if (receptionList[i].time != 0) {
						receptionList[i].time--;
					}
				}
				// 수리 창구
				// 자리가 있으면 수리 대기 큐에서 이동
				for (int i = 1; i <= m; i++) {
					if (repairList[i].time == 0 && !repairQueue.isEmpty()) {
						repairList[i].num = repairQueue.poll();
						repairList[i].time = bj[i];
						if (i != b) {
							ans[repairList[i].num] = false;
						}
					}
				}
				// 시간 하나씩 감소 (0이 되면 cnt 하나 감소)
				for (int i = 1; i <= m; i++) {
					if (repairList[i].time == 1) {
						repairList[i].time = 0;
						cnt--;
					}
					else if (repairList[i].time != 0) {
						repairList[i].time--;
					}
				}
				time++;
			}
			int sum = 0;
			for (int i = 1; i <= k; i++) {
				if(ans[i] == true) sum += i;
			}
			if (sum == 0) System.out.println(-1);
			else System.out.println(sum);
		}
	}
}
