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
		}
		for (int i = 1; i <= n; i++) {
			d[i] = Math.max(d[i - 1], d[i]);
			if (i + t[i] - 1 <= n) {
				d[i + t[i] - 1] = Math.max(d[i + t[i] - 1], d[i - 1] + p[i]);
			}
		}
		System.out.println(d[n]);
	}

}
