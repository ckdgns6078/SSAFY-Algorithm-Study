package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모노미노도미노2 {
    static int N;
    static int[][] green, blue;
    static int score = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        green = new int[6][4];
        blue = new int[4][6];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            fill(t, x, y);
            boolean circle = IsLineAllSame();
            while(circle) circle = IsLineAllSame();
            IslightColor();
            

        }
        System.out.println(score);
        System.out.println(total());

    }
    
    // 
    static int total() {
    	int sum = 0;
    	for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j] == 1) sum++;
			}
		}
    	
    	for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if(blue[i][j] == 1) sum++;
			}
		}
    	
    	return sum;
    }
    
    // 블록 채우기
	private static void fill(int t, int x, int y) {
		// TODO Auto-generated method stub
		int ig = 6;
		int ib = 6;
		
		if(t==1) {
			for(int i = 0; i < 6; i++) {
				if(green[i][y] != 0) {
					ig = i;
					break;
				}
			}
			green[ig-1][y] = 1;
			
			for(int i = 0; i < 6; i++) {
				if(blue[x][i] != 0) {
					ib = i;
					break;
				}
			}
			blue[x][ib-1] = 1;
		}else if(t==2) {
			for (int i = 0; i < 6; i++) {
				if(green[i][y] != 0 || green[i][y+1] != 0) {
					ig = i;
					break;
				}
			}
			green[ig-1][y] = 1;
			green[ig-1][y+1] = 1;
			
			for (int i = 0; i < 6; i++) {
				if(blue[x][i] != 0) {
					ib = i;
					break;
				}
			}
			blue[x][ib-1] = 1;
			blue[x][ib-2] = 1;	
		}else {
			for (int i = 0; i < 6; i++) {
				if(green[i][y] != 0) {
					ig = i;
					break;
				}
			}
			green[ig-1][y] = 1;
			green[ig-2][y] = 1;
			
			for (int i = 0; i < 6; i++) {
				if(blue[x][i] != 0 || blue[x+1][i] != 0) {
					ib = i;
					break;
				}
			}
			blue[x][ib-1] = 1;
			blue[x+1][ib-1] = 1;
			
		}
	}
	
	// 보드 이동
	static void move(int d, int i) {
		if(d==0) {
			for (int j = i; 0 < j; j--) {
				green[j][0] = green[j-1][0];
				green[j][1] = green[j-1][1];
				green[j][2] = green[j-1][2];
				green[j][3] = green[j-1][3];
				green[j-1][0] = green[j-1][1] = green[j-1][2] = green[j-1][3] = 0;
			}
		}else {
			for (int j = i; 0 < j; j--) {
				blue[0][j] = blue[0][j-1];
				blue[1][j] = blue[1][j-1];
				blue[2][j] = blue[2][j-1];
				blue[3][j] = blue[3][j-1];
				blue[0][j-1] = blue[1][j-1] = blue[2][j-1] = blue[3][j-1] = 0;
			}
		}
	}
	
	static boolean IsLineAllSame() {
		boolean check = false;
		for (int i = 0; i < 6; i++) {
			if(green[i][0]==1 && green[i][1]==1 && green[i][2]==1 && green[i][3]==1) {
				move(0, i);
				score++;
				check = true;
				break;
			}
		}
		
		for (int i = 0; i < 6; i++) {
			if(blue[0][i]==1 && blue[1][i]==1 && blue[2][i]==1 && blue[3][i]==1) {
				move(1, i);
				score++;
				check = true;
				break;
			}
		}
		return check;		
	}
	
	static void IslightColor() {
		if(green[1][0] == 1 || green[1][1] == 1 || green[1][2] == 1 || green[1][3] == 1) {
			move(0, 5);
		}
		if(green[1][0] == 1 || green[1][1] == 1 || green[1][2] == 1 || green[1][3] == 1) {
			move(0, 5);
		}
		
		if(blue[0][1] == 1 || blue[1][1] == 1 || blue[2][1] == 1 || blue[3][1] == 1) {
			move(1, 5);
		}
		if(blue[0][1] == 1 || blue[1][1] == 1 || blue[2][1] == 1 || blue[3][1] == 1) {
			move(1, 5);
		}
		
		
	}
}
