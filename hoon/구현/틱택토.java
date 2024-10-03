import java.util.*;

public class Main {

	static char[][][] map;

	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * list로 데이터를 받고 list의 사이즈만큼 3차원 3 * 3 배열을 만든다.
		 * 
		 */
		ArrayList<String> inputList = new ArrayList();
		while (true) {
			String str = sc.next();
			if (str.equals("end")) {
				break;
			}
			inputList.add(str);
		}

		map = new char[inputList.size()][3][3];
		for (int i = 0; i < inputList.size(); i++) {
			String str = inputList.get(i);
			int cnt = 0;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					map[i][j][k] = str.charAt(cnt);
					cnt++;
				}
			}

		}

		
		
		int size = inputList.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(checkData(map[i]));
			sb.append("\n");
		}
		System.out.println(sb);

	}

	private static String checkData(char[][] arr) {
		// 1. x,o 숫자 검사
		int xCnt = 0;
		int oCnt = 0;
		int dot = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == 'X') {
					xCnt++;
				} else if (arr[i][j] == 'O') {
					oCnt++;
				} else if (arr[i][j] == '.') {
					dot++;
				}
			}
		}

		if (xCnt < 3 && oCnt < 3) {
			return "invalid";
		} else if (oCnt > xCnt) {
			return "invalid";
		} else if (xCnt - oCnt > 1) {
			return "invalid";
		} else if (xCnt > oCnt && oxCheck(arr, 'O')) {
			return "invalid";
		} else if( xCnt == oCnt && oxCheck(arr ,'X')) {
			return "invalid";
		} else if( dot > 0) {
			return terminateCheck(arr);
		}
		return "valid";
	}

	private static String terminateCheck(char[][] arr) {
		boolean check = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] != '.') {
					if (successCheck(arr, i, j)) {
						return "valid";
					}
				}
			}
		}
		return "invalid";
	}

	private static boolean oxCheck(char[][] arr, char ox) {
		boolean check = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == ox) {
					if (successCheck(arr, i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean successCheck(char[][] arr, int r, int c) {
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3 && arr[nr][nc] == arr[r][c]) {
				nr += dr[i];
				nc += dc[i];
				if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3 && arr[nr][nc] == arr[r][c]) {
					return true;
				}
			}
		}
		return false;
	}

}
