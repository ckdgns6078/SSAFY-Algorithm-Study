//package date1002;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int [][] map;
	
	static int result;
	
	public static void main(String[] args) {
		//System.setIn(s4013.class.getResourceAsStream("s4013.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		
		for (int tc = 1; tc <= T; tc++) {
			int k = sc.nextInt();
			
			map = new int [4][8];
			
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 8; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			// 검사하는 로직 추가 -> 회전할 것인지 아닌지 판단.
			
			for(int kc = 0; kc < k; kc++) {
//				System.out.println("현재 바퀴=========");
//				print();
//				
				int num = sc.nextInt();
				int dir = sc.nextInt();
				num = num-1;
				
				// 돌리기 전에 왼쪽, 오른쪽 검사
				// 같을 값일 경우 break
				// 다른 값을 경우 chk[] 배열에 저장
				// chk 배열의 방향대로 돌려.
				
				int l = num;
				int lDir = dir * -1;
				int r = num;
				int rDir = dir * -1;
				
				
				// 왼쪽 검사
				int [] chk = new int [4];
				chk [num] = dir;
				
				while (l > 0) {
					if (map[l][6] == map[l-1][2]) {
						break;
					}
					else {
						chk[l-1] = lDir;
						l -= 1;
						lDir *= -1;
					}
					
					
				}
				
				while (r < 3) {
					if (map[r][2] == map[r+1][6]) {
						break;
					}
					else {
						chk[r+1] = rDir;
						r += 1;
						rDir *= -1;
					}
				}
				
//				System.out.println(Arrays.toString(chk));
				
				for (int i = 0 ; i < 4; i++) {
					if (chk[i] == 1) {
						rRotate(i);
					}
					if (chk[i] == -1) {
						lRotate(i);
					}
				}
				
			}
			
			result = 0;
			
			// 빨간색 총 계산
			cal();
	
			System.out.println("#" + tc + " " + result);
		}
	}


	// 빨간색 시작점이 s인 1일 경우만 합계 계산
	private static void cal() {
		int i = 1;
		for (int r = 0; r < 4; r++) {
			if (map[r][0] == 1) {
				result += i;
			}
			i *= 2;
		}
	}

	// 우회전 - 시계 방향 = 1
	private static void rRotate(int num) {
		int temp = map[num][7];
		for (int i = 7; i > 0; i--) {
//			System.out.println(i + " " + map[num][i]);
			map[num][i] = map[num][i-1];
		}
		map[num][0] = temp;

	}

	// 좌회전 반시계 방행 = -1
	private static void lRotate(int num) {
		int temp = map[num][0];
		for (int i = 0; i < 7; i++) {
//			System.out.println(i + " " + map[num][i]);
			map[num][i] = map[num][i+1];
		}
		map[num][7] = temp;
		
	}


	// 출력 함수
	private static void print() {
		for (int r = 0; r < 4; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
}
