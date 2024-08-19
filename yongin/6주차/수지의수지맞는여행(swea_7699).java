package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 수지의수지맞는여행_7699 {
	static int N, M, result;
	static char[][] arr;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	
	
	static void dfs(int r, int c, int cnt, List<Character> list) {
		

		if(cnt > result) {
			result = Math.max(result, cnt);
		}		
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
//			System.out.println(nr + " " + nc);

			if(nr>=0&&nr<N&&nc>=0&&nc<M&&!list.contains(arr[nr][nc])) {
				list.add(arr[nr][nc]);
				dfs(nr, nc, cnt+1, list);
//				list.remove(arr[nr][nc]);
			}
		}
//		for(int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i)+" ");
//		}
		list.remove(list.size()-1);
	}
	
	public static void main(String[] args) throws Exception{
//		List<Character> list = new ArrayList<Character>();
//		list.add('a');
//		list.add('b');
//		list.add('c');
//		System.out.println(list);
//		list.remove(list.size()-1);
//		System.out.println(list);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;
			arr = new char[N][M];
//			list = new ArrayList<Character>();
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = str.charAt(j);
				}
			}
			
//			for(char[] a : arr) {
//				for(char b : a) {
//					System.out.print(b);
//				}
//				System.out.println();
//			}
			
			List<Character> list = new ArrayList<Character>();
			list.add(arr[0][0]);
			dfs(0, 0, 1, list);
//			for (int i = 0; i < list.size(); i++) {
//				System.out.print(list.get(i)+" ");
//			}
//			System.out.println();
			
			System.out.println("#"+t+" "+result);
		}
	}

}



//3
//2 4
//CAAB
//ADCB
//3 6
//HFDFFB
//AJHGDH
//DGAGEH
//5 5
//IEFCJ
//FHFKC
//FFALF
//HFGCF
//HMCHH


