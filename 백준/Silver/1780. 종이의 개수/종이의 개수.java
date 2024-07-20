import java.util.Collections;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int[][] map;
	static int N;

	static int a = 0;
	static int b = 0;
	static int c = 0;

	public static void recursive(int y, int x, int size) {
		// end
		if (y >= N) {
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			return;
		}

		cycle(y, x, size);
		
		// y값 바꾸기 필여
		if (size >= N) {
			recursive(y + 3, 0, 3);
		} else {
			recursive(y, x + 3, size + 3);
		}
		// x값 검사하는 코드 필요ㅕ

	}

	public static void cycle(int y, int x, int size) {
		int value = map[y][x];
		int m1 = 0;
		int m2 = 0;
		int m3 = 0;
		boolean check = true;
		for (int i = y; i < y+3; i++) {
			for (int j = x; j < size; j++) {
				if (value != map[i][j]) {
					check = false;
				}
				if (map[i][j] == -1) {
					m1++;
				} else if (map[i][j] == 0) {
					m2++;
				} else if (map[i][j] == 1) {
					m3++;
				}
			}
		}
		if (check) {
			if (value == -1) {
				a++;
			} else if (value == 0) {
				b++;
			} else if (value == 1) {
				c++;
			}

		} else {
			a += m1;
			b += m2;
			c += m3;
		}

	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];

		int check;
		boolean result =true;
		
		// 입력부
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		check = map[0][0];
		for( int i = 0 ; i < N ; i++) {
			for( int j = 0 ; j < N ; j++) {
				if(check != map[i][j]) {
					result=false;
				}
			}
		}
		if(result) {
			if (check == -1) {
				a++;
			} else if (check == 0) {
				b++;
			} else if (check == 1) {
				c++;
			}
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
		}else {
			recursive(0 , 0 ,3);			
		}
		


	}
}