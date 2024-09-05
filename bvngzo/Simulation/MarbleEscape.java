import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static final int RED = 0;
	static final int BLUE = 1;
	static int R,C;
	static String[][] map;
	static int[] Exit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new String[R][C];
		
		
		int[] Red = null;
		int[] Blue = null;
		
		int[][][] dist = new int[2][R][C];
		
		for(int i = 0; i < R; i++) {
			String[] str = sc.next().split("");
			for(int j = 0; j < C; j++) {
				String val = str[j]; 
				map[i][j] = val;
				
				if(val.equals("R")) {
					Red = new int[] {i, j};
				}
				else if(val.equals("B")) {
					Blue = new int[] {i, j};
				}
				else if(val.equals("O")) {
					Exit = new int[] {i,j};
				}
			}
		}
		
		Queue<int[]> Q = new ArrayDeque();
		
		Q.offer(Blue);
		Q.offer(Red);
		int cnt = 1;
		boolean success = false;
		
		L:while(cnt <= 10) {
			int qSize = Q.size();
			
			for(int i = 0; i < qSize/2; i++) {
				int[] blue = Q.poll();
				int[] red = Q.poll();	
				L2:for(int d = 0; d < dr.length; d++) {		// 0 : 상 / 1 : 우 / 2 : 하 / 3 : 좌 
					int[] nBlue = move(blue[0], blue[1],d);
					int[] nRed = move(red[0], red[1],d);
					
					// 1. 새 좌표가 통로
					if(isIn(nRed) && !isIn(nBlue)) {
						success = true;
						dist[RED][nRed[0]][nRed[1]] = cnt;
						break L;
					}
					else if(isIn(nBlue)) {
						continue L2;
					}
					
					
					// 2. 새 좌표가 겹칠 때
					// 이동 전부터 수직 or 수평 방향 동일선상
					if(nBlue[0] == nRed[0] && nBlue[1] == nRed[1]) {
						switch(d) {
						case 0:			// 위쪽 기울이기
							if(blue[0] < red[0]) {	// 파란 구슬이 위
								nRed[0]+=1;
							}else {
								nBlue[0]+=1;
							}
							break;
						case 1:			// 오른쪽 기울이기
							if(blue[1] < red[1]) {	// 빨간 구슬이 오른쪽
								nBlue[1]-=1;
							}else {
								nRed[1]-=1;
							}
							break;
						case 2:			// 아래쪽 기울이기
							if(blue[0] < red[0]) {	// 파란 구슬이 위
								nBlue[0]-=1;
							}else {
								nRed[0]-=1;
							}
							break;
						case 3:			// 왼쪽 기울이기
							if(blue[1] < red[1]) {	// 파란 구슬이 위
								nRed[1]+=1;
							}else {
								nBlue[1]+=1;
							}
							break;
						}
					}
					
					if(red[0] == nRed[0] && red[1] == nRed[1] && blue[0] == nBlue[0] && blue[1] == nBlue[1]) continue L2;
					
					// 좌표 업데이트
					dist[BLUE][nBlue[0]][nBlue[1]] = cnt;
					dist[RED][nRed[0]][nRed[1]] = cnt;
					
					Q.offer(new int[] {nBlue[0], nBlue[1]});
					Q.offer(new int[] {nRed[0], nRed[1]});
				}
			}
			
			cnt++;
		}
		
		int ans = success ? 1 : 0;
		//int ans = success ? dist[RED][Exit[0]][Exit[1]] : -1;
		System.out.println(ans);
		
	}

	private static int[] move(int r, int c, int d) {
		int nr = r;
		int nc = c;
		while(!map[nr + dr[d]][nc + dc[d]].equals("#")) {
			nr += dr[d];
			nc += dc[d];
			if(map[nr][nc].equals("O")) break;
		}
		return new int[] {nr, nc};
	}
	
	static boolean isIn(int[]coord) {
		return (Exit[0] == coord[0] && Exit[1] == coord[1]);
	}

}
