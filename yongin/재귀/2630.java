import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;



public class Main {
	static int white = 0;
	static int blue = 0;
	static int[][] arr;
	
	
	public static void recursive(int row, int col, int N) {
		
		// 같은색인지 확인
		if(check(row, col, N)) {
			if(arr[row][col] == 0) {
				white++;
			}else {
				blue++;
			}
			return;
		}
		
		int half_size = N/2;
		recursive(row, col, half_size);
		recursive(row, col+half_size, half_size);
		recursive(row+half_size, col, half_size);
		recursive(row+half_size, col+half_size, half_size);
	}
	public static boolean check(int row, int col, int N) {
		
		int color = arr[row][col];
		for (int i = row; i < row+N; i++) {
			for (int j = col; j < col+N; j++) {
				if(arr[i][j] != color) {
					return false;
				}
			}
		}
		return true;
		
	}

    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 배열의 길이
        int N = Integer.parseInt(br.readLine());
        // 배열 생성
        arr = new int[N][N];
        for (int i = 0; i < arr.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // row, col, N 
        recursive(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }
}
