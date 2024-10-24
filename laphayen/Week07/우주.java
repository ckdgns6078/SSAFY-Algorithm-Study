import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        parent = new int[N + 1];
        int[][] positions = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            positions[i][0] = sc.nextInt();
            positions[i][1] = sc.nextInt();
        }

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a, b);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dist = getDistance(positions[i], positions[j]);
                edges.add(new Edge(dist, i, j));
            }
        }

        Collections.sort(edges);

        double ans = 0;

        for (Edge edge : edges) {
            int x = edge.node1;
            int y = edge.node2;
            double cost = edge.distance;

            if (find(x) != find(y)) {
                union(x, y);
                ans += cost;
            }
        }

        System.out.printf("%.2f\n", ans);
        sc.close();
    }

    static double getDistance(int[] loc1, int[] loc2) {
        int x1 = loc1[0], y1 = loc1[1];
        int x2 = loc2[0], y2 = loc2[1];
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Edge implements Comparable<Edge> {
        double distance;
        int node1, node2;

        Edge(double distance, int node1, int node2) {
            this.distance = distance;
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}
