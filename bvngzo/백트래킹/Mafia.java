import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] guilties;
	static int[][] scores;
	static int me;
	static int ans = -1;
	static ArrayList<Person> list;

	static class Person {
		int n;
		int guilty;
		boolean death;

		public Person(int n, int guilty, boolean death) {
			super();
			this.n = n;
			this.guilty = guilty;
			this.death = death;
		}

		@Override
		public String toString() {
			return "Person [n=" + n + ", guilty=" + guilty + ", death=" + death + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<Person>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		guilties = new int[N];
		for (int i = 0; i < N; i++) {
			list.add(new Person(i, Integer.parseInt(st.nextToken()), false));
		}

		scores = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		me = Integer.parseInt(br.readLine());

		mafia(N - 1, me, 0);
		
		System.out.println(ans);

	}

	private static void mafia(int civil, int me, int cnt) {
	
		if(list.get(me).death || civil==0) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		if ((civil + 1) % 2 == 0) { // 밤
			for (int i = 0; i < N; i++) {
				if (i == me || list.get(i).death)continue;
				
				list.get(i).death = true;
				calGuil(1, i);
				mafia(civil - 1, me, cnt + 1);
				calGuil(-1, i);
				list.get(i).death = false;
			}
		} else {// 낮
			int minIdx = Integer.MAX_VALUE;
			int maxV = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				Person temp = list.get(i);
				if (temp.death)
					continue;

				if (maxV < temp.guilty) {
					maxV = temp.guilty;
					minIdx = i;
				} else if (maxV == temp.guilty && minIdx > i) {
					minIdx = i;
				}
			}

			list.get(minIdx).death = true;
			mafia(civil - 1, me, cnt);
			list.get(minIdx).death = false;
			
		}
	}

	private static void calGuil(int v, int idx) {
		for (int i = 0; i < N; i++) {
			Person temp = list.get(i);
			if (!temp.death) {
				temp.guilty += v * scores[idx][i];
			}
		}
	}

}
