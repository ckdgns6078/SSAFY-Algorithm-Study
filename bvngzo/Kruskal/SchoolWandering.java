import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[] parents;

	static class Edge {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 건물 수
		M = Integer.parseInt(st.nextToken()); // 경로 수

		ArrayList<Edge> edges = new ArrayList<Edge>();

		int best = 0; // best case
		int worst = 0; // worst case

		for (int i = 0; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (i == 0) {
				best = worst = (w == 0 ? 1 : 0);
				continue;
			}

			edges.add(new Edge(s, e, w));

		}

		// worst case
		edges.sort((e1, e2) -> Integer.compare(e1.w, e2.w));

		make();
		int connected = 0;

		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);

			if (union(edge.s, edge.e)) {
				worst += (edge.w == 0 ? 1 : 0);
			
				if(++connected == N-1)break;
			}

		}
		
		// best case
		edges.sort((e1, e2) -> -Integer.compare(e1.w, e2.w));
		
		make();
		connected = 0;
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);

			if (union(edge.s, edge.e)) {
				best += (edge.w == 0 ? 1 : 0);
			
				if(++connected == N-1)break;
			}

		}
		
		System.out.println(worst*worst - best*best);
		
	}

	static void make() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		else
			return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;
		else {
			parents[bRoot] = aRoot;
			return true;
		}

	}

}
