import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static int N;
	static int[] result;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static boolean isMax = false;
	static boolean isSame = false;
	
	static void dfs(int r, int c, int cnt) {
		
		if(cnt > result[1]) {
			result[1] = Math.max(result[1], cnt);
			isMax = true;
		}
		if(cnt == result[1]) isSame=true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0&&nr<N&&nc>=0&&nc<N&&(arr[nr][nc]-arr[r][c])==1) {
//				System.out.println(r + ", " + c);
				
				dfs(nr, nc, cnt+1);
				if(isMax&&cnt==1) {
//					System.out.println(r + ", " + c);
					result[0] = arr[r][c];
//					System.out.println(result[0] + ", " + result[1]);
					isMax = false;
				}
				if(isSame&&cnt==1) {
					result[0] = Math.min(result[0], arr[r][c]);
					isSame = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		/*
		 * 이동하려는 방에 적힌 숫자는 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다
		 * 처음에 출발해야 하는 방 번호와 최대 몇 개의 방을 이동할 수 있는지를 공백을 기준으로 출력
		 * 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은것을 출력
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			result = new int[2];
			result[0] = Integer.MAX_VALUE;
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 1);
				}
			}
			System.out.println("#"+t+" "+result[0]+" "+result[1]);
		}
	}

}
