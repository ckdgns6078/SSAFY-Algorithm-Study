import java.util.*;

public class Main {
	static int n;
	static int m;
	static int diceX;
	static int diceY;
	static int[][] map;
	static int orderCnt;
//	static int[] orders;
	static int[][] dice = new int[4][4];
	static StringBuilder sb = new StringBuilder();
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		diceY = sc.nextInt();
		diceX = sc.nextInt();
		orderCnt = sc.nextInt();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 이동할 수 없을 경우 이동하지 않고 출력도 하지 않는다.
		for (int i = 0; i < orderCnt; i++) {
			int order = sc.nextInt();
			if (checkMove(order)) {		//움직일 수 있을 경우 이동
				if (order == 1) {
					sb.append(moveEast());
				} else if (order == 2) {
					sb.append(moveWest());
				} else if (order == 3) {
					sb.append(moveNorth());
				} else if (order == 4) {
					sb.append(moveSouth());
				}
				diceY+= dr[order];
				diceX+= dc[order];
				sb.append("\n");
				changeValue(diceY , diceX);
				
			}

		}
		System.out.println(sb);

	}

	//주사위와 맵을 변경하는 함수
	private static void changeValue(int r , int c) {
		//마지막은 dice[3][1]
		if(map[r][c]==0) {
			map[r][c] = dice[3][1];
		}else {
			dice[3][1]= map[r][c];
			dice[1][3]= map[r][c];
			map[r][c]= 0;
		}
	}

	private static boolean checkMove(int order) {
		int r = diceY + dr[order];
		int c = diceX + dc[order];

		if (r >= 0 && r < n && c >= 0 && c < m) {
			return true;
		}
		return false;
	}

	// 1. 동쪽
	private static int moveEast() {
		//이동하고 검사를 해야한다.
		int temp = dice[1][3];
		for(int i = 3 ; i >0 ; i--) {
			dice[1][i]= dice[1][i-1];
		}
		dice[1][0] = temp;
		dice[3][1] = dice[1][3];
		
		return dice[1][1];
	}

	// 2. 서쪽
	private static int moveWest() {
		int temp = dice[1][0];
		for(int i = 0 ; i < 3 ; i++) {
			dice[1][i] = dice[1][i+1];
		}
		dice[1][3] = temp;
		dice[3][1] = dice[1][3];

		return dice[1][1];
	}

	// 4. 남쪽
	private static int moveSouth() {
		//커지는경우
		int temp = dice[0][1];
		for(int i=0 ; i <3 ; i++) {
			dice[i][1] = dice[i+1][1];
		}
		dice[3][1] = temp;
		dice[1][3] = dice[3][1];
		
		return dice[1][1];
	}

	// 3. 북쪽
	private static int moveNorth() {
		//작아지는경우
		int temp = dice[3][1];
		for(int i=3 ; i >0 ; i--) {
			dice[i][1] = dice[i-1][1];
		}
		dice[0][1] = temp;
		dice[1][3] = dice[3][1];
		
		return dice[1][1];
	}

}
