import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[][] v;
	static int result, h, w;
	//팔방
	static int[] dr = {1, -1, 0, 0, 1, -1, 1, -1};
	static int[] dc = {0, 0, 1, -1, 1, -1, -1, 1};
	
	private static void dfs(int r, int c) {
		// 지도에서 땅은 1, 바다는 0
		
		// 현재 위치가 섬이고, 방문을 하지 않았으면 
		v[r][c] = true;
		
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>=0 && nr<h && nc>=0 && nc<w && !v[nr][nc] && arr[nr][nc] == 1){
				dfs(nr, nc);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w==0 && h==0) {
				break;
			}
			arr = new int[h][w];
			v = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(arr[i][j] == 1 && !v[i][j]) {
						dfs(i, j);
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}

}
