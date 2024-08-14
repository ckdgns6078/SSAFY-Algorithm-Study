package algorithm;

import java.util.*;

class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class SWE1767 {
	static List<Point> li = new ArrayList<>();
	static int n;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int maxCnt = 0;
	static int minLen = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t;
		t = sc.nextInt();
		
		for (int test_case = 1; test_case <= t; test_case++) {
			System.out.println("#" + test_case + " ");
			n = sc.nextInt();
			map = new int[n][n];
			maxCnt = 0;
			minLen = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1 && i != 0 && j != 0 && i != n - 1 && j != n - 1) li.add(new Point(i , j));
				}
			}
			func(0, 0, 0);
			System.out.println(minLen);
		}
	}

	private static void func(int idx, int cnt, int len) {
		// basis part
		if (li.size() == idx) {
			if (len < minLen && cnt == maxCnt) minLen = len;
			return;
		}
		if (n - idx - 1 + cnt < maxCnt) return;
		// inductive part
		for (int i = 0; i < dx.length; i++) {
			if (checked(idx, i) != -1) {
				func(idx + 1, cnt + 1, len + checked(idx, i));
				unchecked(idx, i);
				if (maxCnt < cnt + 1) maxCnt = cnt + 1; 
			}
		}
		func(idx + 1, cnt, len);		
	}

	private static void unchecked(int idx, int dir) {
		int cx = li.get(idx).x;
		int cy = li.get(idx).y;
		for (int i = 0; i < n; i++) {
			int nx = cx - dx[dir] * i;
			int ny = cy - dy[dir] * i;
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
			map[nx][ny] = 0;
		}
		
	}

	private static int checked(int idx, int dir) {
		int cx = li.get(idx).x;
		int cy = li.get(idx).y;
		int len = 0;
		// 체크
		for (int i = 0; i < n; i++) {
			int nx = cx + dx[dir] * i;
			int ny = cy + dy[dir] * i;
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
			if (map[nx][ny] > 0) return -1;
		}
		// 칠하기
		for (int i = 0; i < n; i++) {
			int nx = cx + dx[dir] * i;
			int ny = cy + dy[dir] * i;
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
			map[nx][ny] = 2;
			len++;
		}
		return len;
	}

}
