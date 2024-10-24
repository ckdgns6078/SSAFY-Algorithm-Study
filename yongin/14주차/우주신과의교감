package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 우주신과의교감2 {
    static int V, E;
    static int[] parents;
    static Edge[] edges;
    static Point[] spaceHuman;
    static int edgeCount = 0;

    static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
    }

    // 서로소 집합 생성
    static void make() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    // 부모찾기
    static int findSet(int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);  // 경로 압축
    }

    // 두 집합을 합치기
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;  // 이미 같은 집합
        parents[bRoot] = aRoot;  // bRoot를 aRoot에 합침
        return true;
    }

    // 거리계산
    static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());  // 우주신 (정점 수)
        E = Integer.parseInt(st.nextToken());  // 연결된 통로 갯수 (간선 수)

        spaceHuman = new Point[V + 1];
        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            spaceHuman[i] = new Point(x, y);
        }
        edges = new Edge[V * (V - 1) / 2];

        make();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            union(from, to);  // 이미 연결된 통로를 union 처리
        }

        // 우주신들 거리계산
        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                double distance = getDistance(spaceHuman[i], spaceHuman[j]);
                edges[edgeCount++] = new Edge(i, j, distance);
            }
        }
        // 크루스칼 ㄱㄱ
        Arrays.sort(edges);
        double cost = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {  // 사이클이 없을 때만 간선 선택
            	cnt++;
            	cost += edge.weight;
                if (cnt == V - 1) break;
            }
        }
        System.out.printf("%.2f\n", cost);
    }
}
