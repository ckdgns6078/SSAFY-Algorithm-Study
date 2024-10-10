import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String testcase = sc.next();
			if (testcase.equals("end")) {
				break;
			}
			
			// X의 개수, O의 개수 구하기
			int xNum = 0;
			int oNum = 0;
			for (int i = 0; i < 9; i++) {
				if (testcase.charAt(i) == 'X') {
					xNum++;
				}
				if (testcase.charAt(i) == 'O') {
					oNum++;
				}
			}
			
			// 가로, 세로, 대각선 방향으로 3칸이 이어져 있어야 함
			boolean xIsBingo = isBingo('X', testcase);
			boolean oIsBingo = isBingo('O', testcase);
			
			// 둘다 빙고인 경우는 불가능
			if (xIsBingo && oIsBingo) {
				System.out.println("invalid");
				continue;
			} 
			// x가 빙고라서 게임이 끝났다면 x가 1개 더 많음
			else if (xIsBingo && !oIsBingo && xNum != oNum + 1) {
				System.out.println("invalid");
				continue;
			} 
			// o가 빙고라서 게임이 끝났다면 o가 1개 더 많음
			else if (!xIsBingo && oIsBingo && xNum != oNum) {
				System.out.println("invalid");
				continue;
			}
			// 빙고가 하나도 없이 게임이 끝난 경우
			else if (!xIsBingo && !oIsBingo) {
				if (xNum != 5 || oNum != 4) {
					System.out.println("invalid");
					continue;
				}
			}
			
			// 가능함
			System.out.println("valid");
			continue;
		}
	}
	
	private static boolean isBingo(char alpha, String testcase) {
		// 가로
		if (testcase.charAt(0) == alpha && testcase.charAt(0) == testcase.charAt(1) && testcase.charAt(1) == testcase.charAt(2)) {
			return true;
		}
		if (testcase.charAt(3) == alpha && testcase.charAt(3) == testcase.charAt(4) && testcase.charAt(4) == testcase.charAt(5)) {
			return true;
		}
		if (testcase.charAt(6) == alpha && testcase.charAt(6) == testcase.charAt(7) && testcase.charAt(7) == testcase.charAt(8)) {
			return true;
		}
		
		// 세로
		if (testcase.charAt(0) == alpha && testcase.charAt(0) == testcase.charAt(3) && testcase.charAt(3) == testcase.charAt(6)) {
			return true;
		}
		if (testcase.charAt(1) == alpha && testcase.charAt(1) == testcase.charAt(4) && testcase.charAt(4) == testcase.charAt(7)) {
			return true;
		}
		if (testcase.charAt(2) == alpha && testcase.charAt(2) == testcase.charAt(5) && testcase.charAt(5) == testcase.charAt(8)) {
			return true;
		}
		
		// 대각선
		if (testcase.charAt(0) == alpha && testcase.charAt(0) == testcase.charAt(4) && testcase.charAt(4) == testcase.charAt(8)) {
			return true;
		}
		if (testcase.charAt(2) == alpha && testcase.charAt(2) == testcase.charAt(4) && testcase.charAt(4) == testcase.charAt(6)) {
			return true;
		}
		
		return false;
	}
}
