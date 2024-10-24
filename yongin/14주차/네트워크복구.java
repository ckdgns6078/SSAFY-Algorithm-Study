package algorithm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크복구 {

    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] adjList = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        // 다익스트라
        dijkstra(N, adjList);
    }

    static void dijkstra(int N, Node[] adjList) {
        final int INF = Integer.MAX_VALUE;
        int[] minDistance = new int[N + 1];
        int[] prev = new int[N + 1]; // 이전 정점
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(minDistance, INF);
        minDistance[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));  // 시작 정점

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currVertex = current.vertex;

            if (visited[currVertex]) continue;
            visited[currVertex] = true;

            for (Node temp = adjList[currVertex]; temp != null; temp = temp.next) {
                if (!visited[temp.vertex] && minDistance[temp.vertex] > minDistance[currVertex] + temp.weight) {
                    minDistance[temp.vertex] = minDistance[currVertex] + temp.weight;
                    pq.offer(new Node(temp.vertex, minDistance[temp.vertex]));
                    prev[temp.vertex] = currVertex;
                }
            }
        }

        System.out.println(N - 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(i + " " + prev[i]);
        }
    }
}
