import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr = new int[9][9];
    
    private static void dfs(int r, int c) {
        
        // basis part
        if(c==9) {
            dfs(r+1, 0);
            return;
        }
        
        if(r==9) {
//        	System.out.println("-----------------");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]).append(' ');
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        
        // inductive part
        if(arr[r][c] == 0) {
        	// 값 있는지 확인
        	for (int i = 1; i < 10; i++) {
				if(isTrue(r, c, i)) {
					arr[r][c] = i;
					dfs(r, c+1);
				}
			}
        	arr[r][c]=0;
        	return;
        }
        dfs(r, c+1);
        
    }
    
    private static boolean isTrue(int r, int c, int val) {
        // 행 검사, 열 검사
    	// 해당 위치가 있는 행과 열에 val가 있으면 return
        for (int i = 0; i < 9; i++) {
            if(arr[r][i] == val) {
                return false;
            }
            if(arr[i][c] == val) {
                return false;
            }
        }
        
        
        int a = (r/3) * 3;
        int b = (c/3) * 3;
        for (int i = a; i < a+3 ; i++) {
            for (int j = b; j < b+3; j++) {
                if(arr[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.println(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        
        dfs(0, 0);
    	
    }

}


//입력
//0 0 0 0 0 0 0 0 0
//7 8 2 1 3 5 6 4 9
//4 6 9 2 7 8 1 3 5
//3 2 1 5 4 6 8 9 7
//0 0 0 0 0 0 0 0 0
//5 9 6 8 2 7 4 1 3
//9 1 7 6 5 2 3 8 4
//6 4 3 7 8 1 9 5 2
//0 0 0 0 0 0 0 0 0

//출력
//1 3 5 4 6 9 2 7 8 
//7 8 2 1 3 5 6 4 9 
//4 6 9 2 7 8 1 3 5 
//3 2 1 5 4 6 8 9 7 
//8 7 4 9 1 3 5 2 6 
//5 9 6 8 2 7 4 1 3 
//9 1 7 6 5 2 3 8 4 
//6 4 3 7 8 1 9 5 2 
//2 5 8 3 9 4 7 6 1
