package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈도둑_7733 {
	static int N, maxNumber, result;
	static int[][] arr;
	static List<boolean[][]> vList;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	static class Point{
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void bfs(int r, int c, boolean[][] visited) {
//		System.out.println(r + " " + c);
		visited[r][c] = true;
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(r, c));
		
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc));
					
				}
			} 
		}
	}
	
	public static void main(String[] args) throws Exception{
		/*
		 * 100일 중에서 치즈 덩어리가 가장 많을 때의 덩어리 개수를 구하는 프로그램을 작성하라.
		 * 
		 * input
		 * T
		 * N
		 * N개의 줄에 걸쳐서 각 칸의 맛있는 정도가 1~100 사이 숫자로 입력
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			vList = new ArrayList<boolean[][]>();
			maxNumber = Integer.MIN_VALUE;
			result = 0;
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxNumber = Math.max(maxNumber, arr[i][j]);
				}
			}
			int number = 0;
			boolean[][] v;
			while(number != maxNumber) {
				v = new boolean[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(arr[i][j]<=number) {
							v[i][j] = true;
						}
					}
				}
				boolean[][] temp = new boolean[N][N];
				for (int i = 0; i < N; i++) {
					temp[i] = Arrays.copyOf(v[i], v[i].length);
				}
//				for(boolean[] a : temp) {
//					System.out.println(Arrays.toString(a));
//				}
//				System.out.println();
				vList.add(temp);
				number++;
			}
//			for (int i = 0; i < vList.size(); i++) {
//				for(boolean[] b : vList.get(i)) {
//					for(boolean c : b) {
//						System.out.print(c + " ");
//					}
//					System.out.println();
//				}
//				System.out.println("==================");
//			}
			
			for(int k = 0; k < vList.size(); k++) {
				boolean[][] visited = vList.get(k);
				int cnt = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(!visited[i][j]) {
							bfs(i, j, visited);
							cnt++;
						}
					}
				}
				result = Math.max(result, cnt);
			}
			
			System.out.println("#"+t+" "+result);
		}
		
	}

}
