import java.util.*;

class Node {
	int p;

	Node(int p) {
		this.p = p;
	}
}

public class Boj17471 {

    static int n;
    static int[] people;
    static ArrayList<Node>[] list;
    static ArrayList<Integer> comb;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        list = new ArrayList[n + 1];
        people = new int[n + 1];
        comb = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            comb.add(i + 1);
        }

        for (int i = 1; i <= n; i++) {
            people[i] = sc.nextInt();
            list[i] = new ArrayList<>(); // 리스트 초기화
        }

        for (int i = 1; i <= n; i++) {
            int area = sc.nextInt();
            for (int j = 0; j < area; j++) {
                int adj = sc.nextInt();
                list[i].add(new Node(adj));
            }
        }

        // 조합 생성
        for (int i = 1; i <= n; i++) {
            combination(0, i, new ArrayList<>());
        }
        
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }

    }

    // 조합을 구하는 코드
    private static void combination(int idx, int r, List<Integer> sel) {

        if (sel.size() == r) {
            // bfs 호출
            findMin(sel);
            return;
        }

        if (idx == comb.size()) {
            return;
        }
        for (int i = idx; i < comb.size(); i++) {
            sel.add(comb.get(i)); 
            combination(i + 1, r, sel); 
            sel.remove(sel.size() - 1); 
        }

    }

    // 값을 검사하는 코드
    private static boolean bfs(List<Integer> sel) {
        boolean[] v = new boolean[n + 1];
        v[sel.get(0)] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(sel.get(0));

        int cnt = 1; 
        while (!q.isEmpty()) {
            Integer p = q.poll();
            for (Node node : list[p]) { // node를 사용하여 리스트 탐색
                if (!v[node.p] && sel.contains(node.p)) {
                    v[node.p] = true;
                    cnt++;
                    q.offer(node.p);
                }
            }
        }

        return cnt == sel.size(); 
    }

    // 인구수 검사
    private static void findMin(List<Integer> a) {
        // A구역 정상 검사
        if (!bfs(a)) {
            return;
        }

        // b 구역 생성
        ArrayList<Integer> b = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!a.contains(i)) {
                b.add(i);
            }
        }

        // b구역 검사
        if (!bfs(b)) {
            return;
        }

        int ra = 0;
        int rb = 0;

        for (int i : a) {
            ra += people[i];
        }
        for (int i : b) {
            rb += people[i];
        }

        int result = Math.abs(ra - rb);
        min = Math.min(min, result);

    }
}
