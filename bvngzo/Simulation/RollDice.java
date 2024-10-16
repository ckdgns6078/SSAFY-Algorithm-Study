import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static class Dice{
		int r;
		int c;
		LinkedList<Integer> piece3;
		LinkedList<Integer> piece4;
		
		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
			this.piece3 = new LinkedList<Integer>();
			this.piece4 = new LinkedList<Integer>();
			
			for(int i = 0; i < 4; i++) {
				if(i<3) piece3.add(0);
				piece4.add(0);
			}
		}
			
	}
	static int[] dr = {0,0,0,-1,1};
	static int[] dc = {0,1,-1,0,0};
	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		
		Dice dice = new Dice(sr,sc);
		
		int cmdNum = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				int v = Integer.parseInt(st.nextToken());
				map[i][j] = v;
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < cmdNum; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			
			roll(dice, cmd);

		}
		
		
	}


	private static void roll(Dice dice, int cmd) {
		int nr = dice.r + dr[cmd];
		int nc = dice.c + dc[cmd];
		
		if(nr < 0 || nr >= R || nc < 0 || nc >= C) return;
		
		LinkedList<Integer> piece3 = dice.piece3;
		LinkedList<Integer> piece4 = dice.piece4;
		switch(cmd) {
		case 1:
			piece3.addFirst(piece4.removeLast());
			piece4.addLast(piece3.removeLast());
			piece4.set(1, piece3.get(1));
			break;
		case 2:
			piece3.addLast(piece4.removeLast());
			piece4.addLast(piece3.removeFirst());
			piece4.set(1, piece3.get(1));
			break;
		case 3:
			piece4.addLast(piece4.removeFirst());
			piece3.set(1, piece4.get(1));
			break;
		case 4:
			piece4.addFirst(piece4.removeLast());
			piece3.set(1, piece4.get(1));
			break;
		}
		
		if(map[nr][nc] == 0) {
			map[nr][nc] = piece4.getLast();
		}else {
			piece4.set(3, map[nr][nc]);
			map[nr][nc] = 0;
		}
		
		dice.r = nr;
		dice.c = nc;

		System.out.println(piece4.get(1));
		
	}

	
}
