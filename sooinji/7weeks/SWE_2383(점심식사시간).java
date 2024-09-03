import java.util.*;

class Stairs {
	int x, y, cnt, size;
	Stairs(int x, int y, int cnt, int size) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.size = size;
	}
}

public class Solution {
	static int n, ans;
	static int[][] map;
	static Stairs[] stairs;
	static boolean[] sel;
	static ArrayList<Integer>[] dist;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int test_case = 1; test_case <= t; test_case++) {
			System.out.print("#" + test_case + " ");
			n = sc.nextInt();
			map = new int[n][n];
			stairs = new Stairs[2];
			dist = new ArrayList[2];
			for (int i = 0; i < 2; i++) dist[i] = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] > 1 && stairs[0] == null) {
						stairs[0] = new Stairs(i, j, 0, map[i][j]);
					}
					else if (map[i][j] > 1) stairs[1] = new Stairs(i, j, 0, map[i][j]);
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 1) {
						dist[0].add(Math.abs(stairs[0].x - i) + Math.abs(stairs[0].y - j));
						dist[1].add(Math.abs(stairs[1].x - i) + Math.abs(stairs[1].y - j));
					}
				}
			}
			ans = Integer.MAX_VALUE;
			sel = new boolean[dist[0].size()];
			comb(0);
			System.out.println(ans);
		}
	}
	private static void comb(int idx) {
		if (idx == sel.length) {
			//System.out.println("사람 시간 계산");
			ArrayList<Integer> people = new ArrayList<>();
			ArrayList<Integer> people2 = new ArrayList<>();
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) people.add(dist[0].get(i));
				else people2.add(dist[1].get(i));
			}
			int time = Math.max(move(people, 0), move(people2, 1));
			ans = Math.min(ans, time);
			return;
		}
		sel[idx] = false;
		comb(idx + 1);
		sel[idx] = true;
		comb(idx + 1);
	}
	private static int move(ArrayList<Integer> people, int stairsidx) {
		if (people.size() == 0) return 0;
		ArrayList<Integer> wait = new ArrayList<>();
		boolean[] hasArrived = new boolean[people.size()];
		int time = 0;
		stairs[stairsidx].cnt = 0;
		while (++time < ans) {
			// 계단 내려가는 사람 처리
			for (int i = 0; i < people.size(); i++) {
				if (people.get(i) < 0 && !hasArrived[i]) {
					people.set(i, people.get(i) - 1);
					if (people.get(i) < -stairs[stairsidx].size) {
						stairs[stairsidx].cnt--;
						hasArrived[i] = true;
					}
				}
			}
			for (int i = 0; i < people.size(); i++) {
				if (people.get(i) > 0) people.set(i, people.get(i) - 1);
				else if (people.get(i) == 0) {
					boolean exists = false; // 대기자에 존재하는지
					int idx = -1; // 존재한다면 인덱스
					for (int j = 0; j < wait.size(); j++) {
						if (wait.get(j) == i) {
							exists = true;
							idx = j;
						}
					}
					if (stairs[stairsidx].cnt < 3) {
						// 자리가 있는 경우
						stairs[stairsidx].cnt++;
						people.set(i, people.get(i) - 1);
						if (exists) {
							wait.remove(idx);
						}				
					}
					else if (!exists){
						// 자리가 없고 대기자에도 없는 경우
						wait.add(i);
					}
				}
			}
			// 대기 시간
			for (int i = 0; i < wait.size(); i++) {
				people.set(wait.get(i), 0);
			}
			boolean stop = true;
			for (int i = 0; i < hasArrived.length; i++) {
				if (!hasArrived[i]) stop = false;
			}
			if (stop) {
				return time;
			}
		}
		return time;		
	}
}
