import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R, minLength;
    static int[][] arr;
    static boolean[][] v;
    
//    긴거와 짧은 거 중에 짧은거 / 2

    
//4 4 2
//1 2 3 4
//5 6 7 8
//9 8 7 6
//5 4 3 2

    
    static void huijeun() {
        v = new boolean[N][M];
    	for (int k = 0; k < minLength/2; k++) {
    		int r = k;
    		int c = k;
            int temp = arr[r][++c];
            v[r][c] = true;
            int forward = 1;
            while(true) {
            	if(forward==1) {
            		if(c+1 >= M || v[r][c+1]) {
            			forward=2;
            			continue;
            		}
            		arr[r][c] = arr[r][++c];
            		v[r][c] = true;
            	}
            	if(forward==2) {
            		if(r+1 >= N || v[r+1][c]) {
            			forward=3;
            			continue;
            		}
            		arr[r][c] = arr[++r][c];
            		v[r][c] = true;
            	}
            	if(forward==3) {
            		if(c-1 < 0 || v[r][c-1]) {
            			forward=4;
            			continue;
            		}
            		arr[r][c] = arr[r][--c];
            		v[r][c] = true;
            	}
            	if(forward==4) {
            		if(r-1 < 0 || v[r-1][c]) {
            			arr[r][c] = temp;
            			break;
            		}
            		arr[r][c] = arr[--r][c];
            		v[r][c] = true;
            	}
            }
		}
        
       
    }
    
    
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(N>=M) minLength=M;
        if(N<M) minLength=N;
        
        for (int i = 0; i < R; i++) {
            huijeun();
		}
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append('\n');
		}
        System.out.println(sb);
    }

}
