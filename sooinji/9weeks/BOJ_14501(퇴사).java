import java.util.*;

public class Main {
	static int n, ans = 0;
	static int[] t;
	static int[] p;
	static int[] d;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		t = new int[n + 1];
		p = new int[n + 1];
		d = new int[n + 2];
		for (int i = 1; i <= n; i++) {
			t[i] = sc.nextInt();
			p[i] = sc.nextInt();
			for (int j = i + t[i]; j <= n + 1; j++) {
				d[j] = Math.max(d[j], d[i] + p[i]);
			}
			ans = Math.max(ans, d[i]);
		}
		ans = Math.max(ans, d[n + 1]);
		System.out.println(ans);
	}

}
