import java.util.*;

class Point3 implements Comparable<Point3> {
	int edge;
	double weight;

	public Point3(int edge, double weight) {
		this.edge = edge;
		this.weight = weight;
	}

	public int compareTo(Point3 o) {
		return Double.compare(this.weight, o.weight);
	}

}

public class Main {
	static int n;
	static int m;
	static List<Point3>[] list;
	static double[] dist;
	static PriorityQueue<Point3> q;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		/*
		 * 1. 입력한 값들을 저장한다. 2. 저장한 값들을 모든 노드에 연결을 한다. 3. 이미 연결되어 있는 값들을 받아온다. 4. 새로 연결된
		 * 값들의 가중치를 0으로 만들어서 list에 저장한다. 5. 프림을 사용한다.
		 */
		visited = new boolean[n + 1];
		q = new PriorityQueue();
		list = new List[n + 1];
		dist = new double[n + 1];
		Arrays.fill(dist, Double.MAX_VALUE);

		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList();
		}

		int[][] inputData = new int[n + 1][2];
		for (int i = 1; i < n + 1; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			inputData[i][0] = x;
			inputData[i][1] = y;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i != j) {
					int x = inputData[i][0] - inputData[j][0];
					int y = inputData[i][1] - inputData[j][1];
//					double edge = Math.sqrt(x * x + y * y);
//					double edge = Math.pow(x, 2) + Math.pow(y, 2);
					double edge = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
					list[i].add(new Point3(j, edge));
				}
			}
		}

		for (int i = 0; i < m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			list[start].add(new Point3(end, 0));
			list[end].add(new Point3(start, 0));
		}

		q.offer(new Point3(1, 0));
		dist[1] = 0;
		double sum = 0;
		while (!q.isEmpty()) {
			Point3 p = q.poll();

			if (visited[p.edge]) {
				continue;
			}
			visited[p.edge] = true;
			sum += p.weight;
			for (Point3 next : list[p.edge]) {
				if (!visited[next.edge] && next.weight < dist[next.edge]) {
					q.offer(next);
					dist[next.edge] = next.weight;
				}
			}
		}
//		System.out.println(Arrays.toString(dist));
		System.out.println(String.format("%.2f", sum));
	
	}

}
