import java.util.Scanner;

public class Main {

	static class Block {
		int type;
		int r;
		int c;

		public Block(int type, int r, int c) {
			this.type = type;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Block [type=" + type + ", r=" + r + ", c=" + c + "]";
		}
		
		
	}
	// 라인 전체 이동 - 행별 clone
	// 행 별 타일 개수로 꽉찼는지 확인

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] cmds = new int[n][3];

		boolean[][] green = new boolean[6][4];
		boolean[][] blue = new boolean[6][4];

		int[] greenLine = new int[6]; // 녹색 영역 행 별 타일 개수
		int[] blueLine = new int[6]; // 청색 영역 행 별 타일 개수

		int score = 0; // 점수

		// 낙하 개시
		for (int i = 0; i <n; i++) {
			// 블록 결정
			int type = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();
			Block gBlock = new Block(type, r, c);
			Block bBlock = null;
			if(type == 1) bBlock = new Block(type, c, 3-r);
			else if(type == 2)bBlock = new Block(3, c, 3-r);
			else if(type == 3)bBlock = new Block(2, c, 3-r-1);
	

			// 낙하
			score += fall(green, greenLine, gBlock);
			score += fall(blue, blueLine, bBlock);
		}

		// 낙하 동료 후 처리
		int tiles = 0;
		for(int i = 2; i < 6; i++) {
			tiles += greenLine[i];
			tiles += blueLine[i];
		}
		
		System.out.println(score);
		System.out.println(tiles);
	}

	private static int fall(boolean[][] board, int[] line, Block Block) {
		int score = 0;
		int r = 0;
		int c = Block.c;
		switch (Block.type) {
		case 1:
			// 낙하
			while (r + 1 < board.length && !board[r + 1][c])
				r++;

			board[r][c] = true;

			// full row
			if (++line[r] == 4) {
				score++;

				// 블록 하강
				for (int i = r; i > 0; i--) {
					board[i] = board[i - 1].clone();
					line[i] = line[i - 1];
				}
			}

			break;
		case 2:
			// 낙하
			while (r + 1 < board.length && !board[r + 1][c] && !board[r + 1][c + 1])
				r++;
			
			board[r][c] = true;
			board[r][c+1] = true;
			// full row
			line[r] += 2;
			if (line[r] == 4) {
				score++;

				// 블록 하강
				for (int i = r; i > 0; i--) {
					board[i] = board[i - 1].clone();
					line[i] = line[i - 1];
				}
			}

			break;

		case 3:
			r = -2;
			// 낙하
			while (r + 2 < board.length && !board[r + 2][c])
				r++;

			board[r][c] = true;
			board[r + 1][c] = true;
			line[r]++;
			line[r+1]++;
			boolean flag=false;
			// full row
			for (int k = r + 1; k >= r; k--) {
				while (line[k] == 4) {
					score++;

					// 블록 하강
					for (int i = k; i > 0; i--) {
						board[i] = board[i - 1].clone();
						line[i] = line[i - 1];
					}
				}
			}

			break;
		}
		// 연한 행 확인
		while (line[1] > 0) {
			for (int i = board.length - 1; i > 0; i--) {
				board[i] = board[i - 1].clone();
				line[i] = line[i - 1];
			}
			board[0] = new boolean[4];
			line[0] = 0;
		}

		return score;
	}
	

}
