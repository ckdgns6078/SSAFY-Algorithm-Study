import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		
		char[][] stars = new char[N][N];
		
		setStar(stars,0,0,N);

		
		for(char[] cc : stars) {
			for(char c : cc) {
				sb.append(c);
			}
			sb.append('\n');
		}
		System.out.print(sb);
		
	}
	
	static void setStar(char[][] M,int x, int y, int n) {
		
		if(n<3) return;
		
		for(int i = x; i < x + n; i ++) {
			for(int j = y; j < y + n; j++) {
				if(x + n/3 <= i && i < x + 2*n/3 && y + n/3 <= j && j < y + 2*n/3) {
					M[i][j] = ' ';
				}else if(M[i][j] != ' '){
					M[i][j] = '*';
				}
			}
		}
		
		int nw_n = n/3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				setStar(M, x + i*nw_n,y + j*nw_n,nw_n);
			}
		}

		
	}
	
	
}

