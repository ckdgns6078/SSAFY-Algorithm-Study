package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마피아 {
	static int N, number, result, people;
	static int[][] R;
	
	// 짝수, 홀수 판별 함수
	static boolean func(int[] g) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(g[i] != 0) cnt++;
		}
		if(cnt%2==0) {
			return true; // true : 짝수
		}
		return false; // false : 홀수
	}
	
	
	public static void main(String[] args) throws Exception{
		/*
		 * 각 사람의 유죄 지수
		 * 유죄 지수는 낮에 시민들이 어떤 참가자를 죽일 것인지 고를 때 쓰인다.
		 * and 참가자 간의 반응을 나타내는 2차원 배열 R이 주어진다.
		 * 
		 * 게임 진행
		 * 1. 밤에 마피아가 죽일 사람 한명 고름. 
		 * 이 경우 각 사람의 유죄 지수 바뀜 
		 * -> 만약 참가자 i가 죽었다면, 다른 참가자 j의 유죄 지수는 R[i][j]만큼 변함
		 * 
		 * 2. 낮에는 현재 게임에 남아있는 사람 중에 유죄 지수가 가장 높은 사람을 죽임.
		 * 그런 사람이 여러명일 경우 그 중 번호가 가장 작은 사람이 죽는다. 
		 * 이 경우 유죄 지수는 바뀌지 않는다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] g = new int[N];
		R = new int[N][N];
		
		// 유죄 지수
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			g[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열 R
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		number = Integer.parseInt(br.readLine());
		result = Integer.MIN_VALUE;
		start(g, 0);
		System.out.println(result);
		
	}


	private static void start(int[] g, int cnt) {
		if(g[number]==0) {
			if(result < cnt) {
				result = cnt;
			}
			return;
		}
		
		int c = 0;
		for (int i = 0; i < N; i++) {
			if(g[i] != 0) c++;
		}
		if(c == 1) {
			if(g[number] != 0) {
				result = Math.max(result,  cnt);
			}
			return;
		}
		
		if(func(g)) { // 밤, 마피아가 죽임
			// 마피아 번호를 제외한 나머지를 순서대로 죽인다.
			for (int i = 0; i < N; i++) {
				if(i != (number) && g[i] != 0) {
					int val = g[i];
					g[i] = 0;
					
					// 2차원 배열 R 갱신
					for (int j = 0; j < N; j++) {
						if(g[j] != 0) {
							g[j] += R[i][j];
						}
					}
					start(g, cnt + 1);
					for (int j = 0; j < N; j++) {
						if(g[j] != 0) {
							g[j] -= R[i][j];
						}
					}
					g[i] = val;
				}
			}
		}else { // 낮, 유죄 지수 높은 사람 죽임
			int idx = -1;
			int maxValue = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				if(g[i] != 0 && g[i] > maxValue) {
					idx = i;
					maxValue = g[i];
				}
			}
			int val = g[idx];
			g[idx] = 0;
			start(g, cnt);
			g[idx] = val;
		}
	}
}
