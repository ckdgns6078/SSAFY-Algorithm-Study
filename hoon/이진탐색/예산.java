import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];

		long merge = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			merge += arr[i];
		}
		int budget = sc.nextInt();
		Arrays.sort(arr);

		if (merge <= budget) {
			System.out.println(arr[n - 1]);
		} else {
			int start = 0;
			int end = 1000000000;

			int answer = Integer.MIN_VALUE;
			while (start <= end) {
				int center = (start + end) / 2;
				long sum = 0;
				int cnt = 0;
				for (int i = 0; i < n; i++) {
					if (arr[i] < center) {
						sum += arr[i];
						cnt++;
					} else {
						break;
					}
				}
				sum += center * (n - cnt);

				if (sum <= budget) {
					answer = Math.max(answer, center);
					// sum이 작아서 start를 증가할때
					start = center + 1;
				} else {
					// sum이 커서 end를 감소시킬때
					end = center - 1;
				}
			}

			System.out.println(answer);

		}
	}

}
