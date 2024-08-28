/*
 * 기출1
 * 
 */


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class p1 {

	static int[][] map;

	static int n;

	public static void main(String[] args) {
		System.setIn(t3.class.getResourceAsStream("t3.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= 1; t++) {

			int n = sc.nextInt();
			map = new int[n][n];

			// map 입력부
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			// 구현부
			// 1. 하 하강
			/*
			 * map에서 0번째 행을 탐색을 하고 -> for
			 * 
			 * 블록인 경우에 내린다.
			 * 
			 * 
			 * 
			 * 2. 블록 다음번째를 확인해서 1인 경우와 0인경우를 처리한다. -> for 문 n-1까지 다음이 0인 경우는 현재 블록의 크기 * 현재
			 * 하강력 * 1.9를 하고 하강한다.
			 * 
			 * 
			 * if 0 1인 경우는 블록의 크기를 구한다. 현재 하강력과 블록 크기를 비교해서 하강력이 크면 블록의 크기를 현재 블록의 크기와 더해서
			 * 하강한다. if 1 * 작으면 멈춘다. while != 0 - 다음블록의 크기를 구한다. if curF > blockSize 2 block
			 * down else break;
			 * 
			 * 그린다. 시작점부터 블록 크기만큼 그린다.
			 * 
			 * for arr[] = 1 나머지 값은 = 0으로
			 * 
			 * 
			 * 
			 */

			for (int c = 0; c < n; c++) {
				if (map[0][c] == 1) {
					int curR = 0; // 현재 위치
					int curS = 1; // 현재 블록 사이즈
					double curF = 1; // 현재 위치

					for (int i = 0; i < n - curS; i++) {
						if (map[curS + i][c] == 1) { // 현재 블록의 다음이 1인 경우
							int nextS = 0; // 저항 블록의 크기
							while (curR + curS + nextS < n && map[curR + curS + nextS][c] == 1) {
								
								nextS += 1;
							}
							//System.out.println("nextS : "+nextS);
							
							if (curF > nextS) {
								curS += nextS;
								curF = curS * curF;
							}
							if (curF <= nextS) {
								break;
							}
						}
						if (curR + curS  < n && map[curR + curS][c] == 0) { // 현재 블록의 다음이 0인 경우
							curF = curS * curF * 1.9;
							curR += 1;
						}

//						 System.out.print("curR: " + curR + " ");
//						 System.out.println("curS: " + curS);

						for (int j = curR; j < curS+curR; j++) {
							map[j][c] = 1;
						}
						map[curR - 1][c] = 0;
					}
				}

			}

			
			for (int r = 0; r < n; r++) {
				if (map[r][0] == 1) {
					int curC = 0; // 현재 위치
					int curS = 1; // 현재 블록 사이즈
					double curF = 1; // 현재 위치

					for (int i = 0; i < n - curS; i++) {
						if (map[r][curS + i] == 1) { // 현재 블록의 다음이 1인 경우
							int nextS = 0; // 저항 블록의 크기
							while (curC + curS + nextS < n && map[r][curC + curS + nextS] == 1) {
								
								nextS += 1;
							}
							//System.out.println("nextS : "+nextS);
							
							if (curF > nextS) {
								curS += nextS;
								curF = curS * curF;
							}
							if (curF <= nextS) {
								break;
							}
						}
						if (curC + curS  < n && map[r][curC + curS] == 0) { // 현재 블록의 다음이 0인 경우
							curF = curS * curF * 1.9;
							curC += 1;
						}

//						 System.out.print("curR: " + curR + " ");
//						 System.out.println("curS: " + curS);

						for (int j = curC; j < curS+curC; j++) {
							map[r][j] = 1;
						}
						map[r][curC - 1] = 0;
					}
				}

			}

			// 출력부
			// 2가지를 출력해야 하는데
			// 맨 아랫줄 n-1의 열과 n-1행에서 1의 개수를 출력해야한다.

			// 그럼 먼저, n-1행의 1의 개수를 출력해
//			System.out.println("-----------------------");
			int rCnt = 0;
			for (int i = 0; i < n; i++) {
				rCnt += map[n - 1][i];
			}

			int cCnt = 0;
			for (int i = 0; i < n; i++) {
				cCnt += map[i][n - 1];
			}
			System.out.println("#" + t + " " + rCnt + " " + cCnt);

		}

	}

}
