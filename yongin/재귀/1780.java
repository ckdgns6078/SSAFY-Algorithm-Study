import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int m_one = 0;
	static int zero = 0;
	static int one = 0;
	static int[][] arr;
	
	public static void recursive(int row, int col, int n) {
		if(check(row, col, n)) {
			if(arr[row][col] == -1) {
				m_one++;
			}else if(arr[row][col] == 0) {
				zero++;
			}else {
				one++;
			}
			return;
		}
		
		int divide_three_n = n/3;
		
		recursive(row, col, divide_three_n);
		recursive(row, col+divide_three_n, divide_three_n);
		recursive(row, col+divide_three_n*2, divide_three_n);
		
		recursive(row+divide_three_n, col, divide_three_n);
		recursive(row+divide_three_n, col+divide_three_n, divide_three_n);
		recursive(row+divide_three_n, col+divide_three_n*2, divide_three_n);
		
		recursive(row+divide_three_n*2, col, divide_three_n);
		recursive(row+divide_three_n*2, col+divide_three_n, divide_three_n);
		recursive(row+divide_three_n*2, col+divide_three_n*2, divide_three_n);
		
	}
	static boolean check(int row, int col, int n) {
		
		int num = arr[row][col];
		for (int i = row; i < row+n; i++) {
			for (int j = col; j < col+n; j++) {
				if(arr[i][j] != num) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	arr = new int[N][N];
    	StringTokenizer st;
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    	
    	recursive(0, 0, N);
    	System.out.println(m_one+"\n"+zero+"\n"+one);

    }
}
 
