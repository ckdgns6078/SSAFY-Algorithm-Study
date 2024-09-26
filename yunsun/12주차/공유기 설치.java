import java.util.ArrayList;
import java.util.Scanner;

// 이분탐색의 비교 기준을 '거리'로 잡음
public class Main {	
	static ArrayList<Integer> housePositions = new ArrayList<Integer>();
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int C = sc.nextInt();
		int start, end, mid;
		int routerNum, newMid;
		
		for (int i = 0; i < N; i++) {
			housePositions.add(sc.nextInt());
		}
		housePositions.sort(null);
		
		start = 1;
		end = housePositions.get(N-1) - housePositions.get(0) + 1;
		mid = (start + end) / 2;   // 최대 거리를 2로 나눈 값
		
		while(true) {
			routerNum = getRouterNum(mid);
			
			if (routerNum < C) {
				// mid(거리)를 줄여야 함
				end = mid;
			} else {
				// mid(거리)를 늘여야 함
				start = mid;
			}
			
			newMid = (start + end) / 2;
			
			if (mid == newMid) {
				break;
			} else {
				mid = newMid;
			}
		}
		System.out.println(mid);
		sc.close();
	}
	
	static int getRouterNum(int distance) {
		int count = 1;
		int currIdx = 0;
		int routerPos = housePositions.get(0);
		
		// 현재 위치가 마지막 집이 아니라면
		while(currIdx < N-1) {
			currIdx++;
			// 현재 위치가 라우터를 설치할 수 있는 위치라면
			if (housePositions.get(currIdx) >= routerPos + distance) {
				count++;
				routerPos = housePositions.get(currIdx);
			}
		}
		return count;
	}
}
