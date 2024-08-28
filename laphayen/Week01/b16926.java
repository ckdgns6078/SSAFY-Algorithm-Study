/*
 * 배열 돌리기 1
 * 106568KB
 * 1440ms
 * Java 8
 * 1795B
 */

import java.util.Scanner;

public class b16926 {
	
	static int [][] map;
	static int n;
	static int m;
	static int rt;
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		rt = sc.nextInt();
		
		map = new int[n][m];
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		/*
		 * 배열 회전하기
		 * 범위 -----j = 0----- m
		 * |		j = 1
		 * |	
		 * |
		 * n                  n, m
		 * 
		 * rt번 회전 -> for
		 * 		바깥부터 안으로 반복 ->  n과 m 중 작은 값 / 2 -> 0, 1 ...
		 * 			현재값 시작값 저장
		 * 			위 왼쪽으로 1칸씩 이동 
		 * 				arr[s][n] = arr[s][n+1] -> n+1 하면 범위 넘어감.
		 * 			우 위쪽으로 1칸씩 이동
		 * 			하 오른쪽으로 1칸씩 이동
		 * 			좌 아래쪽으로 1칸 이동
		 * 			마지막값 = 시작값
		 *
		 */
		
		for (int i = 0; i < rt; i++) {
			
			int cN = (Integer.min(n, m) / 2);
			
			for (int s = 0; s < cN; s++) { // 구성원 만큼 반복
				
				// 초기값 밀리니 저장
				int tmp = map[s][s]; // 00 -> 11 -> 22
				
				// 상
				for (int k = s; k < m-s-1; k++) {
					map[s][k] = map[s][k+1];
				}
				
				// 우
				for (int k = s; k < n-s-1; k++) {
					map[k][m-1-s] = map[k+1][m-1-s];
				}
				
				// 하
				for (int k = s; k < m-s-1; k++) {
					map[n-1-s][m-1-k] = map[n-1-s][m-2-k];
				}
				
				// 좌
				for (int k = s; k < n-s-1; k++) {
					map[n-1-k][s] = map[n-2-k][s];
				}
				
				// 밀린값 [s+1][s]에 넣어주기 
				map[s+1][s] = tmp;
				
			}
			
		}
		
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				System.out.print(map[r][c]+ " ");
			}
			System.out.println("");
		}
		
	}
}
