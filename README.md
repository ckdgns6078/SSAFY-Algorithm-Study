# algorithm

# 1일차 재귀함수 문제 풀이

07.21 못푼 문제

import java.util.Arrays;
import java.util.Scanner;

public class Bj2447 {
	public static int N;
	public static String[][] map;

	public static void recursive(int y, int x, int size) {
		// 종료 구문
		if (size == 1) {

			// 출력구문 추가
			return;
		}

//		int center = (size + 1) / 2; // 중간 값
//		int check = size / 3; // 찍는 숫자
//		int min = (check - 1) / 2; // 빼는 값

		cycle(y, x, size);

		int half = size / 3;
		recursive(y, x, half);
	}

	public static void cycle(int y, int x, int size) {
		// x값만 검사한거임
		if(  y>= N) {
			return;
		}
		
		int center = (size + 1) / 2;
		int check = size / 3;
		int value = (check - 1) / 2;
		int xmin = center - value - 1+x;
		int xmax = center + value - 1+x;
		int ymin = center - value -1+y;
		int ymax = center + value -1+y;
	
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				
				if (i >= ymin && i <= ymax && j >= xmin && j <= xmax) {
					map[i][j] = " "; 
				}
			}
		}
		
		if( x+size < N) {
			cycle(y , x+size , size);
		}else {
			cycle(y+size , 0 , size);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new String[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = "*";
			}
		}
		recursive(0, 0, N);

		for( int i = 0 ; i < N ; i++) {
			for( int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
