import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] adjMatrix = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = sc.nextInt();
			}
		}

		boolean[] selected = new boolean[N];
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[0] = 0;

		for (int i = 0; i < N - 1; i++) {

			int minIdx = -1;
			int minV = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if (!selected[j] && dist[j] < minV) {
					minIdx = j;
					minV = dist[j];
				}

			}
			selected[minIdx] = true;
			for (int k = 0; k < N; k++) {
				if(!selected[k] && k != minIdx) {
					dist[k] = Math.min(dist[k],adjMatrix[minIdx][k]);
				}
			}

		} // end for
		long sum = 0;
		for (int d : dist) {
			sum += d;
		}
		System.out.println(sum);
	}

}
