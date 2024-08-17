import java.util.*;

public class Main {
	static ArrayList<Integer> data = new ArrayList();
	static ArrayList<Integer>[] list;

	static int score = Integer.MAX_VALUE;
	static int cnt = 0;
	static ArrayList<Integer> resultList = new ArrayList();
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int people = sc.nextInt();
		list = new ArrayList[people + 1];
		for (int i = 1; i <= people; i++) {
			list[i] = new ArrayList<>();
		}

		while (true) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			if (i == -1 && j == -1) {
				break;
			}
			list[i].add(j);
			list[j].add(i);
		}

		for( int i = 1 ; i<=people ; i++) {			
			bfs(i, new boolean[people + 1]);
		}
		StringBuilder sb = new StringBuilder();
		for( int i = 0 ; i<resultList.size();i++) {
			sb.append(resultList.get(i));
			sb.append(" ");
		}
		System.out.println(score + " " + cnt);
		System.out.println(sb);


	}

	private static void bfs(int idx, boolean[] v) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		v[idx] = true;
		q.offer(idx);
		int result = 0;

			
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int p = q.poll();
				for (int j = 0; j < list[p].size(); j++) {
					if (!v[list[p].get(j)]) {
						v[list[p].get(j)]=true;
						int re = list[p].get(j);
						q.offer(re);
					}
				}
			}
			result++;
		}
		boolean check = true;
		for( int i = 1; i < v.length ; i++) {
			if(!v[i]) {
				check = false;
				break;
			}
		}
		result--;
		if(check) { 
			if(result < score) {
				score = result;
				cnt=1;
				resultList.clear();
				resultList.add(idx);
			}else if(result == score) {
				cnt++;
				resultList.add(idx);
			}
		}
		
		
		
		
		
	}
}
