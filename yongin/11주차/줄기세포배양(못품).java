package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄기세포배양 {
	static int N, M, K, result;
	static sepo[][] arr;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<sepo> sepoInfoList;
	
	static class sepo{
		int r, c, curValue;
		boolean status, isdead;
		sepo(int r, int c, int curValue, boolean status, boolean isdead){
			this.r=r;
			this.c=c;
			this.curValue=curValue;
			this.status=status;
			this.isdead=isdead;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = 0;
			arr = new sepo[K*2+N][K*2+M];
			sepoInfoList = new ArrayList();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
//					arr[i][j] = Integer.parseInt(st.nextToken());
//					int[] a = new int[4];
//					a[0] = i;
//					a[1] = j;
//					a[2] = Integer.parseInt(st.nextToken());
//					a[3] = 0;
					int a = Integer.parseInt(st.nextToken());
					arr[i+K][j+K] = new sepo(i+K, j+K, a, false, false);
					// r, c, 현재생명력, 상태(0:비활성, 1:활성, 2:죽음)
					if(a > 0) {
						sepoInfoList.add(new sepo(i+K, j+K, a, false, false));
					}
				}
			}
			for (int i = 0; i < sepoInfoList.size(); i++) {
				sepo a = sepoInfoList.get(i);
				System.out.println(a.r + " " +a.c + " " + a.curValue + " " + a.status + " " + a.isdead);
			}
			
			for (int sp = 0; sp < sepoInfoList.size(); sp++) {
				sepo s = sepoInfoList.get(sp);	
			}
			
			bfs();
			
			// 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우 "생명력 수치가 높은 줄기 세포"가 해당하는 그리드 셀을 혼자 차지
			
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<sepo> queue = new ArrayDeque();
		for (int i = 0; i < sepoInfoList.size(); i++) {
			sepo a = sepoInfoList.get(i);
			queue.offer(a);
		}
		while(!queue.isEmpty()){
			sepo sp = queue.poll();
			sp.status=true;
			for (int d = 0; d < 4; d++) {
				int nr = sp.r + dr[d];
				int nc = sp.c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M) {
					if(arr[nr][nc].curValue==0) {
						queue.offer(new sepo(nr, nc, sp.curValue, false, false));
					}else if(!arr[nr][nc].status && arr[nr][nc].curValue<arr[sp.r][sp.c].curValue) {
						arr[nr][nc].curValue = arr[sp.r][sp.c].curValue;
						queue.offer(sp);
					}else if (sp.curValue==0){
						sp.isdead=true;
					}
				}
			}
		}
		
		
		
	}
}



//5
//2 2 10
//1 1
//0 2
//5 5 19
//3 2 0 3 0
//0 3 0 0 0
//0 0 0 0 0
//0 0 1 0 0
//0 0 0 0 2
