import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static char[][] maze = new char[8][8];
	static ArrayList<int[]> obs = new ArrayList();
	
	static int[] dr = {-1,0,1,0,1,1,-1,-1,0};
	static int[] dc = {0,1,0,-1,1,-1,-1,1,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 8; i++) {
			char[] cc = sc.next().toCharArray();
			for(int j = 0; j < 8; j++) {
				maze[i][j] = cc[j];
				if(maze[i][j] == '#') {
					obs.add(new int[] {i,j});
				}
			}
		}
		
		int ans = (escape(7,0)) ? 1 : 0;
		
		System.out.println(ans);
		
	}

	private static boolean escape(int r, int c) {
		if(r == 0 && c == 7) return true;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			int prev = Collections.binarySearch(obs, new int[] {nr,nc}, 
					(o1,o2) -> (o1[0] == o2[0]) ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

			if(nr < 0 || nr >= 8 || nc < 0 || nc >= 8 || prev >= 0) continue;
			
			down();
			int aft = Collections.binarySearch(obs, new int[] {nr,nc}, 
					(o1,o2) -> (o1[0] == o2[0]) ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

			if(aft >= 0) {	// 장애물 깔림
				up();
				continue;
			}
			
			if(escape(nr,nc)) return true;
			else {
				up();
			}
		}
		
		return false;
	}

	private static void down() {
		for(int i = 0; i < obs.size(); i++) {
			//System.out.println("pre: "+Arrays.toString(obs.get(i)));
			obs.get(i)[0]++;
			//System.out.println("aft: "+Arrays.toString(obs.get(i)));
		}
	}
	
	private static void up() {
		for(int i = 0; i < obs.size(); i++) {
			obs.get(i)[0]--;
		}
	}

}
