import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<int[]> list = new ArrayList();
	static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	static int result = Integer.MIN_VALUE;
	
	static void permutation(int k, int[] sel, boolean[] v) {
		// basis part
		if(k==sel.length) {
//			System.out.println(Arrays.toString(sel));
			if(sel[3] == 1) {
//				System.out.println(Arrays.toString(sel));
				list.add(Arrays.copyOf(sel, sel.length));
			}
			return;
		}
		
		
		// inductive part
		for (int i = 0; i < arr.length; i++) {
			if(v[i] == false) {
				v[i] = true;
				sel[k] = arr[i];
				permutation(k+1, sel, v);
				v[i] = false;
			}
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
			// 1~9 조합, 1번은 4번 고정
			permutation(0, new int[9], new boolean[9]);
			
			
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(Arrays.toString(list.get(i)));
//			}

			// 각 이닝마다 치는 공
			StringTokenizer st;
			// 각 선수가 치는 공, 안타: 1, 2루타: 2, 3루타: 3, 홈런: 4, 아웃: 0
			int[][] map = new int[N][9];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for(int[] a : map) {
//				for(int b : a) {
//					System.out.print(b + " ");
//				}
//				System.out.println();
//			}
			/*
			 * list는 선수 1번이 4 위치에 고정된 중복없는 순열
			 * map은 각 선수들이 치는 공 점수
			 * 
			 * list에서 하나 씩 가져올 때 마다, map에 저장되어있는 값을 계산 후 result 갱신
			 */
			// 현재 위치
			int idx;
			int out;
			int sum;
			int[] order;
			int[] test = {9, 8, 7, 1, 6, 5, 4, 3, 2};
			for (int i = 0; i < list.size(); i++) {
				sum = 0;
				idx = 0;
				int[] BatterOrder = list.get(i);
				for (int j = 0; j < N; j++) {
					out = 0;
					order = new int[3];
					while(out < 3) {
						if(idx == 9) idx=0;
						// 타자가 치는 공
						int BatterScore = map[j][BatterOrder[idx]-1];
//						System.out.println(BatterScore);
						if(BatterScore == 1) {
							sum+=order[2];
							order[2] = order[1];
							order[1] = order[0];
							order[0] = 1;
						}else if(BatterScore == 2) {
							sum+=(order[2] + order[1]);
							order[2] = order[0];
							order[1] = 1;
							order[0] = 0;
						}else if(BatterScore == 3) {
							sum+=(order[2] + order[1] + order[0]);
							order[2] = 1;
							order[1] = 0;
							order[0] = 0;
						}else if(BatterScore == 4) {
							sum+=(order[2] + order[1] + order[0] + 1);
							order[2] = 0;
							order[1] = 0;
							order[0] = 0;
						}else if(BatterScore == 0) {
							out++;
						}
						idx++;
					}
				}

				result = Math.max(result, sum);
			}
			System.out.println(result);
		
	}

}
