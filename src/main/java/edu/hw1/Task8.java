package edu.hw1;

public final class Task8 {
    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        int[][] expand = new int[board.length + 4][board[0].length + 4];
        for (int i = 2; i < expand.length - 2; i++) {
            System.arraycopy(board[i - 2], 0, expand[i], 2, expand.length - 2 - 2);
        }

        for (int i = 2; i < board.length - 2; i++) {
            for (int j = 2; j < board[0].length - 2; j++) {
                if (board[i][j] == 1) {
                    if (board[i + 1][j + 2] == 1 || board[i - 1][j + 2] == 1
                        || board[i + 1][j - 2] == 1
                        || board[i - 1][j - 2] == 1
                        || board[i + 2][j + 1] == 1 || board[i + 2][j - 1] == 1 || board[i - 2][j + 1] == 1
                        || board[i - 2][j - 1] == 1
                    ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
