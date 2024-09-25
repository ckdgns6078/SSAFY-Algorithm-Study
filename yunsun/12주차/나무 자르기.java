import java.util.Scanner;

// 나무 높이가 0일 수도 있음
// 상근이는 최소 1의 나무를 가져감

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] trees = new int[N];
		int maxHeight = Integer.MIN_VALUE;
		long start, end, mid;
		long sum;
		
		for (int i = 0; i < N; i++) {
			trees[i] = sc.nextInt();
			maxHeight = trees[i] > maxHeight ? trees[i] : maxHeight;
		}
		
		start = 0;
		end = maxHeight;
		mid = (start + end) / 2;
		
		while(true) {
			sum = 0;
			for (int i = 0; i < trees.length; i++) {
				long add = trees[i] - mid > 0 ? trees[i] - mid : 0;
				sum += add;
			}               
			if (sum < M) {
				// 나무를 더 잘라야하면 -> 절단기의 높이를 낮춘다
				end = mid;
			} else {
				// 나무를 덜 잘라야하거나 같으면 -> 절단기의 높이를 높인다
				start = mid;
			}
			long newMid = (start + end) / 2;
			if (mid == newMid) {
				break;
			} else {
				mid = newMid;		
			}
		}
		
		System.out.println(mid);
		sc.close();
	}
}
