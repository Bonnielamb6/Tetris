package tetris;

public class Board {
    private final char EMPTY = '-';
    private final char OCCUPIED = '0';
    private final char[][] BOARD;
    private boolean isOnTop = false;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        BOARD = new char[rows][cols];
        initializeBoard();
    }

    public void saveBoard(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.BOARD[i], 0, matrix[i].length);
        }
        checkTopRow();
    }

    public char[][] getBoard() {
        char[][] matrixTemp = new char[BOARD.length][BOARD[0].length];
        for (int i = 0; i < BOARD.length; i++) {
            System.arraycopy(BOARD[i], 0, matrixTemp[i], 0, BOARD[i].length);
        }
        return matrixTemp;
    }

    public void breakPieces() {
        int counter;
        for (int i = 0; i < BOARD.length; i++) {
            counter = 0;
            for (int j = 0; j < BOARD[i].length; j++) {
                if (BOARD[i][j] == OCCUPIED) {
                    counter++;
                }
            }
            if (counter == BOARD[i].length) {
                dropBlocks(i);
                for (int j = 0; j < BOARD[i].length; j++) {
                    BOARD[0][j] = EMPTY;
                }
            }
        }
    }

    private void dropBlocks(int row) {
        for (int i = row; i > 0; i--) {
            System.arraycopy(BOARD[i - 1], 0, BOARD[i], 0, BOARD[i].length);
        }
    }

    private void checkTopRow() {
        for (int i = 0; i < BOARD[0].length; i++) {
            if (BOARD[0][i] == OCCUPIED) {
                isOnTop = true;
                break;
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                BOARD[i][j] = EMPTY;
            }
        }
    }

    public boolean isOnTop() {
        return isOnTop;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
