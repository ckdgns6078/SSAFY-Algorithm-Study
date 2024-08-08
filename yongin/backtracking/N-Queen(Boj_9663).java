import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int result, N;
	static int[] col;
	
	private static void dfs(int row) {
		if(row==N) {
			++result;
		}else {
			for (int i = 1; i <= N; i++) {
				col[row + 1] = i;
				if(isPossible(row + 1)) {
					dfs(row + 1);
				}else {
					col[row + 1] = 0;
				}
			}
		}
		col[row] = 0;
	}
	
	private static boolean isPossible(int c) {
		for (int i = 1; i < c; i++) {
			if(col[i] == col[c]) {
				return false;
			}
			if(Math.abs(col[i] - col[c]) == Math.abs(i - c)) {
				return false;
			}
		}
		return true;
	}
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			col = new int[15];
			col[1] = i;
			dfs(1);
		}
		System.out.println(result);
	}

}
