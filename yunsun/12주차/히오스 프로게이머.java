import java.util.Scanner;

// maxLevel 범위 잡는 것에 유의 (처음에는 예시만 보고 maxLevel을 현재 가장 큰 레벨로 할당했었음)
// 1 1 1을 반례로 사용하여 풀었음

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int start, end, mid = 0;
		int minLevel = Integer.MAX_VALUE;
		int maxLevel = Integer.MIN_VALUE;
		long sum;
		
		int[] levels = new int[N];
		
		for (int i = 0; i < N; i++) {
			levels[i] = sc.nextInt();
			minLevel = levels[i] < minLevel ? levels[i] : minLevel;
		}
		
		start = minLevel;
		end = minLevel + K + 1;
		mid = (start + end) / 2;
		
		while(true) {
			sum = 0;
			
			for (int i = 0; i < N; i++) {
				int addedLevels = (mid - levels[i]) > 0 ? mid - levels[i] : 0;
				sum += addedLevels;
			}
			
			if (sum <= K) {   // sum이 K보다 작아도 정답일 수도 있음
				start = mid;   // mid가 정답일 수도 있기 때문에 포함
			} else {
				end = mid;   // 정답에 mid는 포함되지 않는 것이 확실하므로 mid-1까지 탐색할 것이지만, end는 답이 될 수 없을 지도 모르므로 +1을 해줌.
			}
			
			int newMid = (start + end) / 2;
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
