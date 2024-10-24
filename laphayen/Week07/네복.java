import java.util.*;

public class Main {
    static final int INF = (int) 1e9;
    static int n, m;
    static int[] distance, parent;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        distance = new int[n + 1];
        parent = new int[n + 1];
        Arrays.fill(distance, INF);
        Arrays.fill(parent, 0);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        dijkstra(1);

        System.out.println(n - 1);
        for (int i = 2; i <= n; i++) {
            System.out.println(i + " " + parent[i]);
        }

        sc.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.cost;
            int now = node.index;

            if (distance[now] < dist) continue;

            for (Node next : graph.get(now)) {
                int cost = dist + next.cost;

                if (distance[next.index] > cost) {
                    parent[next.index] = now;
                    distance[next.index] = cost;
                    pq.add(new Node(next.index, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index, cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);  // 비용 기준 오름차순 정렬
        }
    }
}
