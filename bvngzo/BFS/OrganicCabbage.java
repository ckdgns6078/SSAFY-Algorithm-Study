
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static class Coord{
		int x;
		int y;
		
		Coord(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
	}
	
	static boolean isChecked(int x, int y,int r, int c) {
		return x>=0 && x < r && y >= 0 && y < c;  
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 0 ; t < T; t++) {
			
			int cnt = 0;
			
			int r = sc.nextInt();
			int c = sc.nextInt();
			int n = sc.nextInt();
			
			int[][]M = new int[r][c];
			
			int[] dx = {0,0,1,-1};
			int[] dy = {1,-1,0,0};
			
			for(int i=0; i < n; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				M[x][y] = 1;
			}
			
			Queue<Coord> q = new LinkedList<Coord>();
			
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					if(M[i][j] == 1) {
						M[i][j] = -1;
						q.add(new Coord(i,j));
						
						while(!q.isEmpty()) {
							Coord temp = q.poll();
							
							for(int d = 0; d < 4; d++) {
								int nw_x = temp.x + dx[d];
								int nw_y = temp.y + dy[d];
								
								if(isChecked(nw_x, nw_y,r,c) && M[nw_x][nw_y] == 1) {
									M[nw_x][nw_y] = -1;
									q.add(new Coord(nw_x, nw_y));
								}
							}
							
						}
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
			
			
		}
		
	}
}
