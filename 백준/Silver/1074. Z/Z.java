import java.util.Scanner;
public class Main {

	static int value = 0;
	static int r;
	static int c;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		r = sc.nextInt(); // x 축
		c = sc.nextInt(); // y축

		int size = 1 << N;
		recursive(r, c, size);

	}

	// 1.전체 크기 , 사이즈 // x시작값 y시작값?
	public static void recursive(int y, int x, int size) {

		if (size == 1) {
			System.out.println(value);
			return;
		}

		if (size == 2) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (y == i && x == j) {
						System.out.println(value);
						return;
					}
					value++;
				}
			}

		}
		int chunk = (size * size) / 4;
		int half = size / 2;
		// 반복 구문
		if (y < half && x < half) {
			value = value;
			recursive(y, x, half);

		} else if (y < half && x >= half) {
			value += chunk;
			recursive(y, x - half, half);

		} else if (y >= half && x < half) {
			value += (chunk * 2);
			recursive(y - half, x, half);

		} else if (y >= half && x >= half) {
			value += (chunk * 3);
			recursive(y - half, x - half, half);
		}

	}

}
/*
 * recursive를 4분면으로 나눠서 데이터를 넣는다. 넣은 값들 중에서 map에서 뽑아 데이터를 출력한다.
 */
