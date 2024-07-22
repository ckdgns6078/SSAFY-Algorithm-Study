# algorithm

# 1일차 재귀함수 문제 풀이

07.21 못푼 문제 / 07.22 못푼 문제 수정2

package Boj;

import java.util.Scanner;

public class Boj2447 {

	public static String[][] map;
	public static int N;

	public static void recursive(int y, int x, int size) {
		// 종료
		if (size == 1) {
			return;
		}

		// 실행
		int min = ((size + 1) / 2) - (((size / 3) - 1) / 2) - 1;
		int max = ((size + 1) / 2) + (((size / 3) - 1) / 2) - 1;

		int newSize = size / 3; // 3번

		for (int i = y + min; i <= max + y; i++) {
			for (int j = x + min; j <= x + max; j++) {
				if(map[i][j].equals("*"))
				map[i][j] = " ";
			}
		}

		// 반복
		if (newSize > 0) { // 새로운 조건 추가
            if (x + size < N) {
                recursive(y, x + size, size);
            } else {
                if (y + size < N) {
                    recursive(y + size, 0, size);
                } else {
                    recursive(0, 0, newSize);
                }
            }
        }

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		map = new String[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = "*";
			}
		}

		recursive(0, 0, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

}
