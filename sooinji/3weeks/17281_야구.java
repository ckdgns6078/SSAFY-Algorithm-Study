import java.util.*;

public class Main {
	
	static int n, ans = 0;
	static int arr[][];
	static int sel[] = new int[9];
	static boolean vis[] = new boolean[9];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][9];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				arr[i][j] = sc.nextInt();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			}
		}
		vis[0] = true;
		order(0);
		System.out.println(ans);
	}

	private static void order(int idx) {
		// basis part
		if (idx == 9) {
			getScore();
			return;
		}
		// inductive part
		if (idx == 3) {
			sel[3] = 0;
			order(idx + 1);
		}
		else {
			for (int i = 0; i < 9; i++) {
				if (!vis[i]) {
					vis[i] = true;
					sel[idx] = i;
					order(idx + 1);
					vis[i] = false;
				}
			}
		}
	}

	private static void getScore() {
		int out, score = 0;
		int man[];
		int ord = 0; // 순서
		for (int i = 0; i < n; i++) {
			out = 0; // 아웃
			man = new int[4]; // 주자
			while(out < 3) {
				// 현재 점수
				int cur = arr[i][sel[ord]];
				if (cur == 1) { // 안타
					score += man[3];
					man[3] = man[2];
					man[2] = man[1];
					man[1] = 1;
				}
				else if (cur == 2) { // 2루타
					score += man[2] + man[3];
					man[3] = man[1];
					man[2] = 1;
					man[1] = 0;
				}
				else if (cur == 3) { // 3루타
					score += man[1] + man[2] + man[3];
					man[3] = 1;
					man[2] = 0;
					man[1] = 0;
				}
				else if (cur == 4) { // 홈런
					score += 1 + man[1] + man[2] + man[3];
					man[1] = 0;
					man[2] = 0;
					man[3] = 0;
				} 
				else if (cur == 0) { // 아웃
					out++;
				}
				ord = ++ord % 9;
			}
		}
		if (score > ans) ans = score;
	}
	
}
