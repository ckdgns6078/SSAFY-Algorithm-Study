
import java.util.Scanner;

public class Main {
    static int[][] M = new int[9][9];
    static boolean[][] visited_row = new boolean[9][10];
    static boolean[][] visited_col = new boolean[9][10];
    static boolean[][] visited_box = new boolean[9][10];

    static boolean isValid(int r, int c, int v){
        return !(visited_row[r][v] || visited_col[c][v] || visited_box[(r/3)*3 +c/3][v]);
    }

    static boolean sudoku(int x, int y){
        for(int i  = x; i < 9; i++){
            for(int j = y; j < 9; j++){
                if(M[i][j]==0){
                    for(int val = 1; val <= 9; val++){
                        if(!isValid(i,j,val)) continue;

                        M[i][j] = val;
                        visited_row[i][val] = true;
                        visited_col[j][val] = true;
                        visited_box[(i/3)*3+j/3][val] = true;

                        if(sudoku(i,0)) return true;
                        else{
                            M[i][j] = 0;
                            visited_row[i][val] = false;
                            visited_col[j][val] = false;
                            visited_box[(i/3)*3+j/3][val] = false;
                        }
                    }// end for
                    if(M[i][j]==0) return false;
                }
            }//end inner for
        }//end for
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                M[i][j] = sc.nextInt();
                visited_row[i][M[i][j]] = true;
                visited_col[j][M[i][j]] = true;
                visited_box[(i/3)*3 +j/3][M[i][j]] = true;
            }
        }

        sudoku(0,0);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(M[i][j]+" ");
            }sb.append("\n");
        }

        System.out.println(sb);


    }
}
