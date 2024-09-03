import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R,C,map[][];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		List<int[]> cheese = new LinkedList<>();
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheese.add(new int[] {i,j});
				}
			}
		}
		
		// 상하 가장 자리
		for(int r = 0; r <R; r += R-1) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 0) {
					dfs(r,c);
				}
			}
		}
		// 좌우 가장 자리
		for(int c = 0; c <C; c += C-1) {
			for(int r = 0; r < R; r++) {
				if(map[r][c] == 0) {
					dfs(r,c);
				}
			}
		}
		
		int time = 0;
		
		Queue<int[]> expired = new LinkedList();
		while(cheese.size()>0) {
			// 치즈 부식
			for(int i = 0; i < cheese.size(); i++) {
				int[] chz = cheese.get(i);
				
				int cnt = 0;
				for(int d = 0; d < dr.length; d++) {
					int nr = chz[0] + dr[d];
					int nc = chz[1] + dc[d];
					
					if(map[nr][nc] == -1)cnt++;
					
				}
				if(cnt>=2) {
					expired.offer(chz);
				}
				
				
			}
			
			// 치즈 삭제
			while(!expired.isEmpty()) {
				int[] temp = expired.poll();
				cheese.remove(temp);
				
				map[temp[0]][temp[1]] = 0;
				dfs(temp[0],temp[1]);
			}

			time++;
		}// end while
		
		System.out.println(time);
		
	}

	private static void dfs(int i, int j) {
		map[i][j] = -1;
		for(int d = 0; d < dr.length; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] != 0) continue;
		
			dfs(nr,nc);
		}
		
	}
	
	static void print(int[][] M) {
		for (int[] aa : M) {
			for (int a : aa) {
				System.out.printf("%2d ",a);
			}System.out.println();
		}
	}
}
