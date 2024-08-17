import java.util.*;

public class Main {
	static int n, a, b;
	static ArrayList<Integer>[] adjList;
	static int cnt, minScore = 50;
	static int score[]; 
	// 점수 -> 탐색 최장 거리
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		adjList = new ArrayList[n + 1];
		score = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}
		while(sc.hasNext()) {
			a = sc.nextInt();
			b = sc.nextInt();
			if (a == -1 && b == -1) break;
			adjList[a].add(b);
			adjList[b].add(a);
		}
		for (int i = 1; i <= n; i++) {
			bfs(i);
		}
		System.out.println(minScore + " " + cnt);
		for (int i = 1; i <= n; i++) {
			if (score[i] == minScore) System.out.print(i + " ");
		}
	}
	private static void bfs(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		int dis[] = new int[n + 1];
		q.offer(idx);
		dis[idx] = 1;
		int maxDis = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < adjList[cur].size(); i++) {
				int nxt = adjList[cur].get(i);
				if (dis[nxt] == 0) {
					q.offer(nxt);
					dis[nxt] = dis[cur] + 1;
					if (maxDis < dis[nxt]) maxDis = dis[nxt];
				}
			}
		}
		score[idx] = maxDis - 1;
		if (score[idx] < minScore) {
			minScore = score[idx];
			cnt = 1;
		}
		else if (score[idx] == minScore) {
			cnt++;
		}
	}
}
