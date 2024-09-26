import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, X, result;
	static int[][] arr;
	static List<int[]> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			list = new ArrayList<int[]>();
			int[] temp1, temp2;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				temp1 = new int[N];
				temp2 = new int[N];
				for (int j = 0; j < N; j++) {
					temp1[j] = arr[i][j];
					temp2[j] = arr[j][i];
				}
				list.add(temp1);
				list.add(temp2);
			}
//			for (int i = 0; i < list.size(); i++) {
//				int[] a = list.get(i);
//				System.out.println(Arrays.toString(a));
//			}
			result = 0;
			
			for (int i = 0; i < list.size(); i++) {
				int[] obj = list.get(i);
				boolean re = isPossible(obj);
				if(re) result++;
			}
			
			
//			System.out.println(result);
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
		
		
	}
	private static boolean isPossible(int[] obj) {
		// TODO Auto-generated method stub
		int cnt = 1;
		int h = obj[0];
		for (int i = 1; i < N; i++) {
			if(h==obj[i]) {
				cnt++;
			}else if(obj[i]-h == 1) {
				if(cnt < X) {
					return false;
				}else {
					cnt = 1;
					h = obj[i];
				}
			}else if(h-obj[i] == 1) {
				if(N<X+i) {
					return false;
				}
				for (int j = 1; j < X; j++) {
					if(h - obj[++i] != 1) {
						return false;
					}
				}
				h = obj[i];
				cnt = 0;
			}else {
				return false;
			}
		}
		return true;
	}
}



//10
//6 2
//3 3 3 2 1 1
//3 3 3 2 2 1
//3 3 3 3 3 2
//2 2 3 2 2 2
//2 2 3 2 2 2
//2 2 2 2 2 2
