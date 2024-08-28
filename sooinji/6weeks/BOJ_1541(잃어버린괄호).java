import java.util.*;

public class BOJ_1541 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int ans = 0;
		int sum = 0;
		boolean minus = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+') {
				if (minus) ans -= sum;
				else ans += sum;
				sum = 0;
			}
			else if (c == '-') {
				// 이거 뒤에 있는 값부터는 다 마이너스임
				if (minus) ans -= sum;
				else ans += sum;
				minus = true;
				sum = 0;
			}
			else {
				sum *= 10;
				sum += (int)c - '0';
			}
		}
		if (minus) ans -= sum;
		else ans += sum;
		System.out.println(ans);
	}
}
