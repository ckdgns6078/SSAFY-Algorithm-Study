import java.util.*;

class Solution {
	static int n;
	static int[] arr;
	static int maxVal = 0;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t;
		t = sc.nextInt();
		
		for (int test_case = 1; test_case <= t; test_case++) {
			System.out.print("#" + test_case + " ");
			n = sc.nextInt();
			arr = new int[n];
			maxVal = 0;
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
				if (maxVal < arr[i]) maxVal = arr[i];
			}
			ans = watering();
			maxVal++;
			if (ans > watering()) ans = watering();
			System.out.println(ans);
		}
	}

	private static int watering() {
		int days = 0;
		int ones = 0;
		int twos = 0;
		for (int i = 0; i < n; i++) {
			ones += (maxVal - arr[i]) % 2;
			twos += (maxVal - arr[i]) / 2;
		}
		// (1, 2) 세트로 만들어야 최소 날짜가 됨
		int mins = Math.min(ones, twos);
		ones -= mins;
		twos -= mins;
		// 이틀 세트로 만들기
		days += mins * 2;
		if (twos == 0)
			// 
			days += (ones - 1) * 2 + 1;
		if (ones == 0)
			// 
			days += twos + 1 + (twos - 1) / 3;
		
		return days;
	}
}
