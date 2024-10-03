import java.util.*;

public class Main {

	static char[] arr;
	static int cnt;
	static StringBuilder sb = new StringBuilder();
	static int small = Integer.MAX_VALUE;
	static int big = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			String input = sc.next();
			arr = input.toCharArray();
			cnt = sc.nextInt();

			// findSmall
			findCnt();
			System.out.println(sb);
			small = Integer.MAX_VALUE;
			big = Integer.MIN_VALUE;
			sb.setLength(0);
		}

	}

	private static void findCnt() {

		for (int i = 0; i < arr.length; i++) {
			int check = 0;
			char start = arr[i];
			for (int j = i; j < arr.length; j++) {
				if(start == arr[j]) {
					check++;
				}
				//같아질 경우 small 과 big을 처리해야 한다.
				if(check == cnt) {
					small = Math.min(small , j-i+1);
					big = Math.max(big, j-i+1);
					break;
				}
			}
		}
		if(small != Integer.MAX_VALUE && big != Integer.MIN_VALUE) {
			sb.append(small + " " + big);
		}else {
			sb.append(-1);
		}
	}
}
