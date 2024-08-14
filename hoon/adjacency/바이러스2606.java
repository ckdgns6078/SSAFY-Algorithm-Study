import java.util.*;

class N {

	int n;

	N(int n) {
		this.n = n;
	}

}



public class 바이러스2606 {

	static ArrayList<N>[] list;
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int computer = sc.nextInt();
		int net = sc.nextInt();
		list = new ArrayList[computer+1];	//컴퓨터는 1부터 시작함으로 0은 사용하지않고 1부터 사용한다

		for (int i = 1; i <= computer; i++) {
			list[i] = new ArrayList<N>();
		}

		for (int i = 0; i < net; i++) {
			int cnt = sc.nextInt();
			int cnt2 = sc.nextInt();
			list[cnt].add(new N(cnt2));
			list[cnt2].add(new N(cnt));
		}

		// 양방향으로 이동이 가능해야한다.
		bfs(1 , new boolean[computer+1]);
		System.out.println(result);
	}

	private static void bfs(int idx ,boolean[] v) {
		
		Queue<Integer> q = new ArrayDeque();
		q.offer(idx);
		v[idx] = true;

		while (!q.isEmpty()) {
			Integer p = q.poll();

			for (int i = 0; i < list[p].size(); i++) {
				if (!v[list[p].get(i).n]) {
					v[list[p].get(i).n] = true;
					N n = list[p].get(i);
					q.offer(n.n);
					result++;
				}
			}

		}

	}

}
