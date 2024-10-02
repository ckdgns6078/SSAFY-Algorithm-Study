import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, result;
	static int[][] arr;
	static List<int[]> combiList;
	static void print() {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			combiList = new ArrayList<int[]>();
//			print();
			
			/*
			 * N은 짝수
			 * N/2개로 음식을 만든다.
			 * N이 6이면 3개씩 나누어 두 개의 요리를 한다.
			 */
			
			// N개에서 N/2개의 조합을 구해
			int[] a = new int[N];
			for (int i = 0; i < a.length; i++) {
				a[i] = i;
			}
			int[] sel = new int[a.length/2];
			combination(0, 0, a, sel);
			
			for (int i = 0; i < combiList.size(); i++) {
				int[] f1Arr = combiList.get(i);
				List<Integer> f1List = new ArrayList<Integer>();
				for (int j = 0; j < f1Arr.length; j++) {
					f1List.add(f1Arr[j]);
				}
				int f2Idx=0;
				int[] f2Arr = new int[f1Arr.length];
				for (int j = 0; j < N; j++) {
					if(!f1List.contains(j)) {
						f2Arr[f2Idx] = j;
						f2Idx++;
					}
				}
				
				int food1 = sum(f1Arr);
				int food2 = sum(f2Arr);
				result = Math.min(result, Math.abs(food1-food2));

			}
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
		
	}

	private static int sum(int[] Arr) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = 0; i < arr.length/2; i++) {
			for (int j = i; j < arr.length/2; j++) {
				if(i!=j) {
//					System.out.println(Arr[i] + " " + Arr[j]);
					count += (arr[Arr[i]][Arr[j]] + arr[Arr[j]][Arr[i]]);
				}
			}
		}
		return count;
	}


	private static void combination(int idx, int cnt, int[] a, int[] sel) {
		// TODO Auto-generated method stub
		if(cnt==sel.length) {
			int[] s = new int[sel.length];
			for (int i = 0; i < sel.length; i++) {
				s[i] = sel[i];
			}
//			System.out.println(Arrays.toString(sel));
			combiList.add(s);
			return;
		}
		if(idx==a.length)return;
		
		// 선택한 경우
		sel[cnt] = a[idx];
		combination(idx+1, cnt+1, a, sel);
		// 선택안한경우
		sel[cnt] = a[idx];
		combination(idx+1, cnt, a, sel);
	}
}
