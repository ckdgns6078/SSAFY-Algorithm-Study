import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int R,C;
	static int[][] M;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int[][] adjM;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		M = new int[R][C];
		ArrayList<int[]> lands = new ArrayList();		// 육지 죄표
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				M[i][j] = Integer.parseInt(st.nextToken());
				if(M[i][j] == 1) {
					lands.add(new int[] {i,j});
				}
			}
		}
		
		// 1. 섬 마킹
		int v = -1;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j< C; j++) {
				if(dfs(i,j,v)) v--;
			}
		}
		
		// 2. 섬간 최소 거리
		int numOfIsland = -v-1;		// 성의 갯수
		
		adjM = new int[numOfIsland+1][numOfIsland+1];
		
		for(int[] loc : lands) {
			int origin = -M[loc[0]][loc[1]];
			for(int d = 0; d < dr.length; d++) {
				getLen(loc[0], loc[1], dr[d], dc[d], origin, 0);
			}
		}
		
		// 3. mst 생성
		int[] dist = new int[numOfIsland+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] selected = new boolean[numOfIsland+1];
		
		dist[1] = 0;
		
		boolean connected = true;
		L:for(int i = 1; i < numOfIsland; i++) {
			int minIdx = -1;
			int minV = Integer.MAX_VALUE;
			
			for(int j = 1; j <= numOfIsland; j++) {
				if(!selected[j] && dist[j] < minV) {
					minIdx = j;
					minV = dist[j];
				}
			}
			if(minIdx == -1) {
				connected = false;
				break L;
			}
			selected[minIdx] = true;
			
			for(int k = 1; k <= numOfIsland; k++) {
				if(!selected[k] && adjM[minIdx][k] != 0) {
					dist[k] = Math.min(dist[k], adjM[minIdx][k]);
				}
			}
		}
		
		int ans = 0;
		
		for(int i = 1; i <= numOfIsland; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				connected = false;
				break;
			}
			ans += dist[i];
		}
		
		ans = connected ? ans : -1;
		
		System.out.println(ans);
		
	}
	
	static void getLen(int r, int c, int dr, int dc, int origin, int len) {
		int nr = r+dr;
		int nc = c+dc;
		if(nr < 0 || nr >= R|| nc < 0 || nc >= C || M[nr][nc] == -origin)return;
		
		if(M[nr][nc] != 0 && M[nr][nc] != -origin) {// 다른 섬 도착
			//업데이트
			if(len<2) return;
			
			int end = -M[nr][nc];
			adjM[origin][end] = (adjM[origin][end]==0) ? len : Math.min(len, adjM[origin][end]);
			adjM[end][origin] = (adjM[end][origin]==0) ? len : Math.min(len, adjM[end][origin]);
			return;
		}
		
		getLen(nr,nc,dr,dc,origin,len+1);
		
	}

	
	static boolean dfs(int r ,int c, int v) {
		if(r<0 || r >= R || c < 0 || c >= C || M[r][c] != 1) {
			return false;
		}
		
		M[r][c] = v;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r+dr[d];
			int nc = c + dc[d];
			
			dfs(nr,nc,v);
		}
		
		return true;
		
	}
}
