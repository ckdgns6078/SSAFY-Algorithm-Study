import java.util.*;

public class Main {
	
	static int n;
	static ArrayList<Integer>[] adjList;
	static int people[];
	static int deg[];
	static boolean sel[];
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		adjList = new ArrayList[n + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList();
		}
		people = new int[n + 1];
		deg = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			people[i] = sc.nextInt();
		}
		for (int i = 1; i <= n; i++) {
			deg[i] = sc.nextInt();
			for (int j = 1; j <= deg[i]; j++) {
				int tmp = sc.nextInt();
				adjList[i].add(tmp);
			}
		}
		sel = new boolean[n + 1];
		func(1);
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}

	private static void func(int idx) {
		// basis part
		if (idx > n) {
			if (checked(sel)) {
				int sum1 = 0;
				int sum2 = 0;
				for (int i = 1; i <= n; i++) {
					if(sel[i]) {
						sum1 += people[i];
					}
					else {
						sum2 += people[i];
					}
				}
				int minVal = Math.abs(sum1 - sum2);
				if (minVal < ans) ans = minVal;
			}
			return;
		}
		// inductive part
		sel[idx] = false;
		func(idx + 1);
		sel[idx] = true;
		func(idx + 1);
	}

	private static boolean checked(boolean[] sel) {
		// 모두 트루가 아닌 경우만 탐색
		for (int i = 1; i <= n; i++) {
			if (sel[i] == false) break;
			if (sel[n] == true) return false;
		}
		// 첫 번째 구역 탐색
		boolean vis[] = new boolean[n + 1];
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (sel[i]) {
				q.offer(i);
				vis[i] = true;
				break;
			}
		}
		while(!q.isEmpty()) {
			int p = q.poll();
			for (int i = 0; i < adjList[p].size(); i++) {
				if (!vis[adjList[p].get(i)] && sel[adjList[p].get(i)]) {
					vis[adjList[p].get(i)] = true;
					int n = adjList[p].get(i);
					q.offer(n);
				}
			}
		}
		// 두 번째 구역 탐색
		for (int i = 1; i <= n; i++) {
			if (!sel[i]) {
				q.offer(i);
				vis[i] = true;
				break;
			}
		}
		while(!q.isEmpty()) {
			int p = q.poll();
			for (int i = 0; i < adjList[p].size(); i++) {
				if (!vis[adjList[p].get(i)] && !sel[adjList[p].get(i)]) {
					vis[adjList[p].get(i)] = true;
					int n = adjList[p].get(i);
					q.offer(n);
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			if (!vis[i]) return false;
		}
		return true;
	}	
}
