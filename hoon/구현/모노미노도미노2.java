import java.util.*;

public class Main {
	static int n;
	static int[][] map = new int[10][10];
	static int[][] order;
	static int score = 0;
	static PriorityQueue<Integer> greenQueue = new PriorityQueue();
	static PriorityQueue<Integer> blueQueue = new PriorityQueue();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우 t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우
		 * t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우
		 */
		n = sc.nextInt();
		order = new int[n][3];
		for (int i = 0; i < n; i++) {
			order[i][0] = sc.nextInt();
			order[i][1] = sc.nextInt();
			order[i][2] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			move(order[i]);
			breakBluck();
			moveStep();
			specialBluck();
			moveStep();
		}
		System.out.println(score);
		System.out.println(countBluck());

	}

	/*
	 * t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우 t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우
	 * t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우
	 */
	public static void printArr() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	public static void move(int[] data) {
		int r = data[1];
		int c = data[2];
		int green = r;
		int blue = c;
		switch (data[0]) {
		case 1: // 1x1 블록
			while (green < 10 && map[green][c] == 0) {
				green++;
			}
			while (blue < 10 && map[r][blue] == 0) {
				blue++;
			}
			map[green - 1][c] = 1;
			map[r][blue - 1] = 1;
			break;

		case 2: // 1x2 블록
			while (green < 10 && map[green][c] == 0 && map[green][c + 1] == 0) {
				green++;
			}
			map[green - 1][c] = 1;
			map[green - 1][c + 1] = 1;

			while (blue < 10 && map[r][blue] == 0) {
				blue++;
			}
			map[r][blue - 1] = 1;
			map[r][blue - 2] = 1;
			break;

		case 3: // 2x1 블록
			while (green < 10 && map[green][c] == 0) {
				green++;
			}
			map[green - 1][c] = 1;
			map[green - 2][c] = 1;

			while (blue < 10 && map[r][blue] == 0 && map[r + 1][blue] == 0) {
				blue++;
			}
			map[r][blue - 1] = 1;
			map[r + 1][blue - 1] = 1;
			break;
		}

	}

	// 점수는 1점씩 오른다.
	// 부수고 점수를 더하는 함수
	public static void breakBluck() {
		// green
		for (int i = 4; i < 10; i++) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (map[i][j] > 0) {
					cnt++;
				} else {
					break;
				}

			}
			if (cnt == 4) {
				score++;
				Arrays.fill(map[i], 0);
				greenQueue.offer(i);
			}
		}

		for (int i = 4; i < 10; i++) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (map[j][i] > 0) {
					cnt++;
				} else {
					break;
				}
			}
			if (cnt == 4) {
				score++;
				for (int k = 0; k < 4; k++) {
					map[k][i] = 0;
				}
				blueQueue.offer(i);
			}
		}

	}

	// 부숴진 칸을 이동시키는 함수 4
	public static void moveStep() {
		while (!greenQueue.isEmpty()) {
			int idx = greenQueue.poll();
			for (int i = idx; i >= 4; i--) {
				// 이전의 값들을 옮긴다.
				for (int j = 0; j < 4; j++) {
					map[i][j] = map[i - 1][j];
				}
			}
		}

		while (!blueQueue.isEmpty()) {
			int idx = blueQueue.poll();
			for (int i = idx; i >= 4; i--) {
				for (int j = 0; j < 4; j++) {
					map[j][i] = map[j][i - 1];
				}
			}
		}

	}

	// 특수한 칸으로 제거하는 함수
	public static void specialBluck() {
		// green
		for (int i = 4; i <= 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] > 0) {
					greenQueue.offer(i + 4);
					break;
				}
			}
		}

		for (int i = 4; i <= 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[j][i] > 0) {
					blueQueue.offer(i + 4);
					break;
				}
			}
		}
	}

	// 맵의 수량을 확인하는 함수
	public static int countBluck() {
		int cnt = 0;

		for (int i = 6; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] > 0) {
					cnt++;
				}
			}
		}

		for (int i = 6; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[j][i] > 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

}
