package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 원자소멸시뮬레이션 {
	static int N, x, y, fw, K, result;
	static List<atom> list;
	static int arr[][] = new int[4001][4001];;
	// 문제에서 나온대로 -> 상, 하, 좌, 우
	static int[] dr = { 0, 0 , -1, 1 };
	static int[] dc = { 1, -1 , 0, 0 };
	
	
	
	static class atom {
		int x, y, fw, K;
		atom(int x, int y, int fw, int K){
			this.x=x;
			this.y=y;
			this.fw=fw;
			this.K=K;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			list = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			result = 0;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				fw = Integer.parseInt(st.nextToken());
				K = Integer.parseInt(st.nextToken());
				list.add(new atom(x, y, fw, K));
				arr[x][y] = K;
			}
			check();
			
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}


	private static void check() {
		// TODO Auto-generated method stub
		while(!list.isEmpty()) {
			move();
			for (int i = 0; i < list.size(); i++) {
				atom at = list.get(i);
				if(arr[at.x][at.y] != at.K) {
					result+=arr[at.x][at.y];
					arr[at.x][at.y] = 0;
					list.remove(i);
					i--;
				}
			}
		}
	}

	private static void move() {
		// TODO Auto-generated method stub
		// 1초 후 이동
		for (int i = 0; i < list.size(); i++) {
			atom at = list.get(i);
			arr[at.x][at.y] = 0;
			at.x += dr[at.fw];
			at.y += dc[at.fw];
			if(at.x > 4000 || at.x < 0 || at.y > 4000 || at.y < 0) {
				list.remove(i);
				i--;
				continue;
			}
			arr[at.x][at.y] += at.K; 
		}
	}
}
