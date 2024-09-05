import java.util.*;

class Node {
	int s;
	int e;
	int w;

	public Node(int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;
	}	
}

public class Main {

	static int[] unf;
	static ArrayList<Node> list;
	static int n, m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt(); // 건물의 개수 (정점의 개수)
		m = sc.nextInt(); // 도로의 개수 (간선의 개수)

		unf = new int[n + 1]; // 부모 배열 초기화
		list = new ArrayList<>();

		int t1 = sc.nextInt();
		int t2 = sc.nextInt();
		int t3 = sc.nextInt();
		
		int test =0;
		if(t3 == 0) {
			test++;
		}
		
		
		// 간선 정보를 입력받아 리스트에 저장
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();

			list.add(new Node(a, b, c));
		}

		// 최소 피로도 계산
		Collections.sort(list, (o1, o2) -> Integer.compare(o2.w, o1.w));
		long min = kruskal() + test; // 최소 피로도
		min = (long)Math.pow(min, 2);
		
		// 최대 피로도 계산
		Collections.sort(list, (o1, o2) -> Integer.compare(o1.w, o2.w));
		long max = kruskal() + test;
		max = (long)Math.pow(max, 2);
		
		
		
		System.out.println(max - min);

	}

	private static long kruskal() {
		// 부모 배열 초기화
		for (int i = 1; i <= n; i++) {
			unf[i] = i;
		}

		int output = 0; // 오르막길의 개수
		int edgeCount = 0;

		for (Node node : list) {
			int s = node.s;
			int e = node.e;
			int w = node.w;
			if (find(s) != find(e)) {
				union(s, e);
				if (w == 0) {
					output++;
				}
				edgeCount++;
				if (edgeCount == n - 1) {
					break;
				}
			}
		}
		return output; // 피로도는 오르막길 개수의 제곱
	}

	public static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra != rb) {
			unf[ra] = rb;
		}
	}

	public static int find(int v) {
		if (unf[v] == v) {
			return v;
		}
		return unf[v] = find(unf[v]);
	}
}
