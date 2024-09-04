import java.util.*;

class Vertex implements Comparable<Vertex> {
	int edge;
	int weight;

	Vertex(int edge, int weight) {
		this.edge = edge;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex o) {
		return Integer.compare(this.weight, o.weight);

	}

}

public class Main {
	static int n, m;
	static ArrayList<Vertex>[] list;
	static int result = 0;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] v = new boolean[n + 1];
		list = new ArrayList[n + 1];
		for (int i = 1; i < list.length; i++) {
			list[i] = new ArrayList();
		}

		// 양방향 인접 리스트 생성
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int w = sc.nextInt();
			list[x].add(new Vertex(y, w));
			list[y].add(new Vertex(x, w));
		}

		PriorityQueue<Vertex> pq = new PriorityQueue();
		pq.offer(new Vertex(1, 0));
		dist[1] = 0;

		while (!pq.isEmpty()) {
			Vertex p = pq.poll();

			if (v[p.edge]) { // queue에 들어있는 노드를 방문했었으면 continue
				continue;
			}
			result += p.weight;
			max = Math.max(max, p.weight);
			v[p.edge] = true;
			for (Vertex next : list[p.edge]) {
				if (!v[next.edge] && next.weight < dist[next.edge]) {
					pq.offer(next);
					dist[next.edge] = next.weight;
				}

			}

		}
//		System.out.println(Arrays.toString(dist));
		System.out.println(result - max);
		
	}

}
