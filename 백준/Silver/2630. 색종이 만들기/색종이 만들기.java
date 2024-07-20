import java.util.Scanner;

public class Main {

	static int [][]map;
	static int N;
	static int a =0;
	static int b = 0;
	
	
	public static void recursive(int y , int x , int size) {
		
		if( recycle ( y , x , size )) {
			if( map[y][x] == 0) {
				a++;
			}else {
				b++;
			}
			return;
		}
		
		int newSize = size/2;
		
		recursive(y , x, newSize);	// 왼쪽 위 1사분면
		recursive(y , x+ newSize , newSize);	// 오른쪽 위 2사분면
		recursive(y+newSize , x , newSize);	// 오른쪽 아래 3사분면
		recursive(y+newSize , x+newSize , newSize);	// 왼쪽 아래 4사분면
		
	}
	
	public static boolean recycle(int y , int x , int size) {
		int value = map[y][x];
		boolean check = true;
		
		for( int i = y ; i < y+ size ; i++) {
			for( int j = x ; j < x+ size ; j++) {
				if( value != map[i][j]) {
					check = false;
				}
			}
		}
		
		if( check == false) {
			return false;
		}
		return true;
		
	}
	
	
	public static void main( String args[]) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for( int i = 0 ; i < N ; i++) {
			for( int j = 0 ; j < N ; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		recursive(0 , 0 , N);
		
		System.out.println(a);
		System.out.println(b);
		
		
		
	}
}
