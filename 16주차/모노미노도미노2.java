import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] board = new boolean[11][11];
    static int points = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commands = Integer.parseInt(reader.readLine());

        // 경계선을 채워서 처리하기 쉽게 설정
        for (int i = 0; i < 10; i++) {
            board[10][i] = true;
            board[i][10] = true;
        }

        while (commands-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
            int row = Integer.parseInt(tokenizer.nextToken());
            int col = Integer.parseInt(tokenizer.nextToken());

            addBlock(type, row, col);
            clearFullRowsAndColumns();
            shiftBoard();
        }

        System.out.println(points);
        System.out.println(countOccupiedCells());
    }

    static void addBlock(int type, int row, int col) {
        switch (type) {
            case 1:
                for (int i = 6; i <= 10; i++) {
                    if (board[i][col]) {
                        board[i - 1][col] = true;
                        break;
                    }
                }
                for (int j = 6; j <= 10; j++) {
                    if (board[row][j]) {
                        board[row][j - 1] = true;
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 6; i <= 10; i++) {
                    if (board[i][col] || board[i][col + 1]) {
                        board[i - 1][col] = true;
                        board[i - 1][col + 1] = true;
                        break;
                    }
                }
                for (int j = 6; j <= 10; j++) {
                    if (board[row][j]) {
                        board[row][j - 1] = true;
                        board[row][j - 2] = true;
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 6; i <= 10; i++) {
                    if (board[i][col]) {
                        board[i - 1][col] = true;
                        board[i - 2][col] = true;
                        break;
                    }
                }
                for (int j = 6; j <= 10; j++) {
                    if (board[row][j] || board[row + 1][j]) {
                        board[row][j - 1] = true;
                        board[row + 1][j - 1] = true;
                        break;
                    }
                }
                break;
        }
    }

    static void clearFullRowsAndColumns() {
        int checkCount = 4;
        int currentRow = 9, currentColumn = 9;

        while (checkCount-- > 0) {
            if (board[currentRow][0] && board[currentRow][1] && board[currentRow][2] && board[currentRow][3]) {
                points++;
                for (int i = currentRow; i > 3; i--) {
                    for (int j = 0; j < 4; j++) {
                        board[i][j] = board[i - 1][j];
                    }
                }
                currentRow++;
            }
            if (board[0][currentColumn] && board[1][currentColumn] && board[2][currentColumn] && board[3][currentColumn]) {
                points++;
                for (int j = currentColumn; j > 3; j--) {
                    for (int i = 0; i < 4; i++) {
                        board[i][j] = board[i][j - 1];
                    }
                }
                currentColumn++;
            }
            currentRow--;
            currentColumn--;
        }
    }

    static void shiftBoard() {
        int greenShift = 0, blueShift = 0;
        for (int i = 4; i < 6; i++) {
            if (board[i][0] || board[i][1] || board[i][2] || board[i][3]) greenShift++;
            if (board[0][i] || board[1][i] || board[2][i] || board[3][i]) blueShift++;
        }

        while (greenShift-- > 0) {
            for (int i = 9; i > 3; i--) {
                for (int j = 0; j < 4; j++) {
                    board[i][j] = board[i - 1][j];
                }
            }
        }
        while (blueShift-- > 0) {
            for (int j = 9; j > 3; j--) {
                for (int i = 0; i < 4; i++) {
                    board[i][j] = board[i][j - 1];
                }
            }
        }
    }

    static int countOccupiedCells() {
        int occupiedCells = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j]) occupiedCells++;
            }
        }
        return occupiedCells;
    }
}
