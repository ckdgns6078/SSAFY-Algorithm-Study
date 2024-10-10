import java.util.*;


public class Solution {
	static int d;
	static int w;
	static int k;
	static int result;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=1 ; tc <=T ; tc++) {
			
			d = sc.nextInt();	// 보호필름의 두께
			w = sc.nextInt();	// 보호필림의 가로 두께
			k = sc.nextInt();	// 합격 기준
			result =Integer.MAX_VALUE;
			map = new int[d][w];
			for(int i = 0 ; i < d ; i++) {
				for(int j = 0 ; j < w ; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			
			/*
			 * 한줄 검사하고 다음줄로 넘어가도록 설정한다.
			 * 
			 */
			//시작하는 row , 
			dfs(0 , 0);
			System.out.println("#" + tc + " " + result);
		}
	}
	private static void dfs(int r, int cnt) {
		if(cnt >=result) {	//최솟값을 구하기 때문에 의미가 없음
			return;
		}
		
		if(r ==d) {
			L : for(int i = 0 ; i < w ; i++) {
				int check = 1;
				for(int j = 0 ; j < d-1 ; j++) {
					if(map[j][i] == map[j+1][i]) {
						check++;
					}else {
						check=1;
					}
					
					if(check >=k) {
						continue L;
					}
				}
				if(check < k) {
					return;					
				}
			}
			result = Math.min(result, cnt);
			return;
		}
		
		int[] clone = map[r].clone();
		
		
		//이동 없이 다음 칸 검사
		dfs(r+1 , cnt);
	
		//0으로 채워서 다음칸 검사
		Arrays.fill(map[r], 0);
		dfs(r+1 , cnt+1);
		
		//1로 채워서 다음칸 검사
		Arrays.fill(map[r], 1);
		dfs(r+1 , cnt+1);
		map[r] = clone;
	}

}
