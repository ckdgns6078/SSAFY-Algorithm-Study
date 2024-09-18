import java.util.Scanner;

// 바닥은 계단으로 치지 않는다는 규칙때문에 1,2번째 계단만 예외적으로 처음에 계산하고 진행한다.
// 계단이 1개인 경우를 고려하지 않아서 디버깅하는데 많은 시간을 소요함ㅜ
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] oneStair = new int[N+1];
		int[] twoStair = new int[N+1];
		
		int firstStair = sc.nextInt();
		
		if (N == 1) {
			System.out.println(firstStair);
			sc.close();
			return;
		}
		
		int secondStair = sc.nextInt();
		
		oneStair[1] = firstStair;   // 0 -> 1
		oneStair[2] = oneStair[1] + secondStair;   // 0 -> 1 -> 2
		twoStair[2] = secondStair;   // 0 -> 2
		
		for (int i = 3; i <= N; i++) {
			int thisStair = sc.nextInt();
			oneStair[i] = twoStair[i-1] + thisStair;   // 2칸 -> 1칸 올라온 경
			twoStair[i] = oneStair[i-2] > twoStair[i-2] ? oneStair[i-2] + thisStair : twoStair[i-2] + thisStair;
		}
		
		System.out.println(oneStair[N] > twoStair[N] ? oneStair[N] : twoStair[N]);
		sc.close();
	}
}
