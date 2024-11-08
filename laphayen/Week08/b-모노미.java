import java.util.Scanner;

public class Main {
    static int[][] green_board = new int[6][4];
    static int[][] blue_board = new int[6][4];
    static int score = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int t = sc.nextInt();
            int y = sc.nextInt();
            int x = sc.nextInt();
            greenBlock(t, x);
            blueBlock(t, y);
        }

        System.out.println(score);
        int left_block = 0;
        for (int i = 0; i < 6; i++) {
            left_block += sum(green_board[i]);
            left_block += sum(blue_board[i]);
        }
        System.out.println(left_block);

        sc.close();
    }

    public static void checkBlock(String color) {
        int[][] board;
        if (color.equals("green")) {
            board = green_board;
        } else {
            board = blue_board;
        }
        int idx = 5;
        while (idx >= 0) {
            if (sum(board[idx]) == 4) {
                shiftDown(board, idx);
                score++;
            } else {
                idx--;
            }
        }
    }

    public static int sum(int[] row) {
        int sum = 0;
        for (int value : row) {
            sum += value;
        }
        return sum;
    }

    public static void greenBlock(int t, int x) {
        if (t == 1) {
            dropBlock(green_board, x);
        } else if (t == 2) {
            dropHorizontalBlock(green_board, x);
        } else {
            dropVerticalBlock(green_board, x);
        }
        checkBlock("green");
        checkSpecialGreen();
    }

    public static void blueBlock(int t, int y) {
        if (t == 1) {
            dropBlock(blue_board, y);
        } else if (t == 2) {
            dropVerticalBlock(blue_board, y);
        } else {
            dropHorizontalBlock(blue_board, y);
        }
        checkBlock("blue");
        checkSpecialBlue();
    }

    public static void dropBlock(int[][] board, int col) {
        for (int row = 1; row < 6; row++) {
            if (board[row][col] != 0) {
                board[row - 1][col] = 1;
                return;
            }
            if (row == 5) {
                board[row][col] = 1;
            }
        }
    }

    public static void dropHorizontalBlock(int[][] board, int col) {
        for (int row = 1; row < 6; row++) {
            if (board[row][col] != 0 || board[row][col + 1] != 0) {
                board[row - 1][col] = 1;
                board[row - 1][col + 1] = 1;
                return;
            }
            if (row == 5) {
                board[row][col] = 1;
                board[row][col + 1] = 1;
            }
        }
    }

    public static void dropVerticalBlock(int[][] board, int col) {
        for (int row = 1; row < 5; row++) {
            if (board[row][col] != 0 || board[row + 1][col] != 0) {
                board[row - 1][col] = 1;
                board[row][col] = 1;
                return;
            }
            if (row == 4) {
                board[row][col] = 1;
                board[row + 1][col] = 1;
            }
        }
    }

    public static void shiftDown(int[][] board, int row) {
        for (int i = row; i > 0; i--) {
            board[i] = board[i - 1].clone();
        }
        board[0] = new int[4];
    }
    
    public static void checkSpecialGreen() {
        if (sum(green_board[0]) > 0) {
            shiftDown(green_board, 5);
        }
        if (sum(green_board[1]) > 0) {
            shiftDown(green_board, 5);
        }
    }

    public static void checkSpecialBlue() {
        if (sum(blue_board[0]) > 0) {
            shiftDown(blue_board, 5);
        }
        if (sum(blue_board[1]) > 0) {
            shiftDown(blue_board, 5);
        }
    }
}
