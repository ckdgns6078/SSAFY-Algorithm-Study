import java.util.*;

public class Main {
	static int n, ans;
	static int[] arr;
	static int[] d;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		d = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		ans = 0;
		for (int i = 0; i < n; i++) {
			d[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] < arr[j]) d[i] = Math.max(d[i], d[j] + 1);
			}
			ans = Math.max(ans, d[i]);
		}
		System.out.println(ans);
	}

}
