import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String map = br.readLine();
			if (map.equals("end"))
				break;

			ArrayList<Integer> listX = new ArrayList();
			ArrayList<Integer> listO = new ArrayList();
			int blank = 0;
			
			for (int i = 0; i < map.length(); i++) {
				char c = map.charAt(i);

				if (c == 'X')
					listX.add(i);
				else if (c == 'O')
					listO.add(i);
				else
					blank++;
			}

			String result = "valid";

			if (listO.size() > listX.size() || listX.size() - listO.size() > 1) {
				result = "invalid";
			} else {
				boolean lineO = check(listO);
				boolean lineX = check(listX);
				
				if((lineO && lineX) || (lineO && (listX.size() > listO.size())) ||
				  (lineX && (listX.size() == listO.size()))	|| (!lineO && !lineX && (blank>0))) {
					result = "invalid";
				}
				
			}

			System.out.println(result);

		}
	}

	static boolean check(ArrayList<Integer> arr) {
		int[][] ttt = new int[3][3];

		for (int i : arr) {
			ttt[i / 3][i % 3] = 1;
		}
		int cnt = 0;

		// 가로 검사
		for (int i = 0; i < 3; i++) {
			cnt = 0;
			for (int j = 0; j < 3; j++) {
				if (ttt[i][j] == 1) {
					if (++cnt == 3)
						return true;
				}
			}
		}

		// 세로 검사
		for (int i = 0; i < 3; i++) {
			cnt = 0;
			for (int j = 0; j < 3; j++) {
				if (ttt[j][i] == 1) {
					if (++cnt == 3)
						return true;
				}
			}
		}

		// 대각 검사
		cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (ttt[i][i] == 1) {
				if (++cnt == 3)
					return true;
			}
		}
		cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (ttt[2 - i][i] == 1) {
				if (++cnt == 3)
					return true;
			}
		}

		return false;
	}

}
