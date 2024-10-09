package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 보호필름 {
	static int D, W, K, result;
	static int[][] films;
	static int[] arr;
	static boolean[] sel;
	
	static boolean initCheck() {
		boolean[] pass = new boolean[W];
		for (int c = 0; c < W; c++) {
			
			for (int r = 0; r <= D-K; r++) {
				int cnt = 0;
				for (int k = 1; k < K; k++) {
					if(films[r][c] != films[r+k][c]) {
						break;
					}
					cnt++;
				}
				if(cnt == K-1) {
					pass[c] = true;
				}
			}
		}
		
		// 전부 성능검사 통과하면 list 사이즈 만큼 결과 갱신
		int passCount = 0;
		for (int i = 0; i < W; i++) {
			if(pass[i]) passCount++;
		}
		if(passCount == W) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			films = new int[D][W];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					films[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 초기 보호필름에서 성능검사가 통과하는 경우 체크
			if(initCheck()) {
				result = 0;
				sb.append("#").append(t).append(" ").append(result).append('\n');
				continue;
			}
			copyFilm = new int[D][W];
			// 부분집합
			arr = new int[D];
			for (int i = 0; i < D; i++) {
				arr[i] = i;
			}
			sel = new boolean[arr.length];
			powset(0);
			
//			System.out.println(result);
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	static List<Integer> list = new ArrayList<Integer>();;
	private static void powset(int idx) {
		// TODO Auto-generated method stub
		if(idx == arr.length) {
			list.clear();
			for (int i = 0; i < sel.length; i++) {
				if(sel[i]) {
//					System.out.print(arr[i] + " ");
					list.add(arr[i]);
				}
			}
//			System.out.println();
			// 구한 부분집합에서 다시 한번 부분집합으로 나누기
			if(list.size() == 0) return;
			if(result < list.size()) return;
			boolean[] sel2 = new boolean[list.size()];
			painting(0, sel2);
			return;
		}
		
		sel[idx] = true;
		powset(idx+1);
		sel[idx] = false;
		powset(idx+1);
	}
	
	static int[][] copyFilm;
	private static void painting(int idx, boolean[] sel2) {
		// TODO Auto-generated method stub
		if(idx == sel2.length) {
			// 배열 복사
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					copyFilm[i][j] = films[i][j];
				}
			}
//			System.out.println("powset 2");
			for (int i = 0; i < sel2.length; i++) {
				int row = list.get(i);
				if(sel2[i]) { // true 면, 해당 가로부분 0으로 넣기
//					System.out.print(list.get(i) + " ");
					for (int w = 0; w < W; w++) {
						copyFilm[row][w] = 0;
					}
				}else { // false 면, 해당 가로부분 1로 넣기
					for (int w = 0; w < W; w++) {
						copyFilm[row][w] = 1;
					}
				}
			}
			// 이제 배열에서 성능검사가 되는지 체크하기!
			// 만약 result가 list.size보다 작으면 검사할 필요가 없다 !
			isPass(copyFilm);
			
			return;
		}
		
		
		sel2[idx] = true;
		painting(idx+1, sel2);
		sel2[idx] = false;
		painting(idx+1, sel2);
		
	}
	private static void isPass(int[][] copyFilm) {
		// TODO Auto-generated method stub
		boolean[] pass = new boolean[W];
		for (int c = 0; c < W; c++) {
			
			for (int r = 0; r <= D-K; r++) {
				int cnt = 0;
				for (int k = 1; k < K; k++) {
					if(copyFilm[r][c] != copyFilm[r+k][c]) {
						break;
					}
					cnt++;
				}
				if(cnt == K-1) {
					pass[c] = true;
					break;
				}
				// 현재 검사하는 부분에서 성능검사가 통과하지 않으면 계산을 할 필요가 없으므로 return
				if(cnt < K-1 && r==D-K) {
					return;
				}
			}
		}
		
		// 전부 성능검사 통과하면 list 사이즈 만큼 결과 갱신
		int passCount = 0;
		for (int i = 0; i < W; i++) {
			if(pass[i]) passCount++;
		}
		if(passCount == W && result > list.size()) {
			result = list.size();
		}
	}
}




//1
//6 8 3
//0 0 1 0 1 0 0 1
//0 1 0 0 0 1 1 1
//0 1 1 1 0 0 0 0
//1 1 1 1 0 0 0 1
//0 1 1 0 1 0 0 1
//1 0 1 0 1 1 0 1
