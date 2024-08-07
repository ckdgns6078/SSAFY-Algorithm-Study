import java.util.*;

public class Main {
	
	static int n, cnt;
	static int sel[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sel = new int[n];
		cnt = 0;
		func(0);
		System.out.println(cnt);
	}

	private static void func(int idx) {
		// basis part
		if (idx >= n) {
			cnt++;
			return;
		}
		// inductive part
		for (int i = 0; i < n; i++) {
			sel[idx] = i;
			// 체크
			if (checked(idx)) continue;
			func(idx + 1);
		}
	}

	private static boolean checked(int idx) {
		// TODO Auto-generated method stub
		for (int i = 0; i < idx; i++) {
			// 이전 요소들 공격 가능한지 확인
			// 이전 요소: i, sel[i] 현재 요소: idx, sel[idx]
			if (sel[i] == sel[idx]) return true;
			if (sel[i] == (idx - i) + sel[idx]) return true;
			if (sel[i] == -(idx - i) + sel[idx]) return true;
		}
		return false;
	}
}

