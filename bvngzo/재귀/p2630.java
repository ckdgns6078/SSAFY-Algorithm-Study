import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();
		int[][] M = new int[N][N];
		
		for (int i = 0 ; i < N; i++) {
			M[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[] answer = {0,0};
		cut(M,0,0,N,answer);
		
		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}
	
	static void cut(int[][] M, int x, int y, int length, int[] answer) {
		boolean isOne = true;
		
		L:for(int i = x; i< x+length; i++) {
			for(int j = y; j < y+length; j++) {
				if(M[x][y] != M[i][j]) {
					isOne = false;
					break L;
				}
			}
		}
		
		if(isOne) {
			if(M[x][y] == 0) answer[0]++;
			else if(M[x][y] == 1) answer[1]++;
			return;
		}else {
			cut(M,x,y,length/2,answer);
			cut(M,x + length/2,y,length/2,answer);
			cut(M,x,y + length/2,length/2,answer);
			cut(M,x + length/2,y + length/2,length/2,answer);
		}
		
		
		
		
	}
	
}
