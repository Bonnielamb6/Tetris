package tetris;

public class Board {
    private final char EMPTY = '-';
    private final char OCCUPIED = '0';
    private final char[][] BOARD;
    private boolean isOnTop = false;
    private final int ROWS;
    private final int COLS;

    public Board(int rows, int cols) {
        ROWS = rows;
        COLS = cols;
        BOARD = new char[rows][cols];
        initializeBoard();
    }

    public void saveBoard(char[][] tempBoard) {
        for (int currentRow = 0; currentRow < tempBoard.length; currentRow++) {
            System.arraycopy(tempBoard[currentRow], 0, this.BOARD[currentRow], 0, COLS);
        }
        checkTopRow();
    }

    public char[][] getBoard() {
        char[][] matrixTemp = new char[ROWS][COLS];
        for (int currentRow = 0; currentRow < ROWS; currentRow++) {
            System.arraycopy(BOARD[currentRow], 0, matrixTemp[currentRow], 0, COLS);
        }
        return matrixTemp;
    }

    public void breakPieces() {
        int piecesInLine;
        for (int currentRow = 0; currentRow < ROWS; currentRow++) {
            piecesInLine = 0;
            for (int currentCol = 0; currentCol < COLS; currentCol++) {
                if (BOARD[currentRow][currentCol] == OCCUPIED) {
                    piecesInLine++;
                }
            }
            if (piecesInLine == COLS) {
                dropBlocks(currentRow);
                for (int lineToCopy = 0; lineToCopy < COLS; lineToCopy++) {
                    BOARD[0][lineToCopy] = EMPTY;
                }
            }
        }
    }

    private void dropBlocks(int row) {
        for (int lineToDrop = row; lineToDrop > 0; lineToDrop--) {
            System.arraycopy(BOARD[lineToDrop - 1], 0, BOARD[lineToDrop], 0, BOARD[lineToDrop].length);
        }
    }

    private void checkTopRow() {
        for (int currentCol = 0; currentCol < COLS; currentCol++) {
            if (BOARD[0][currentCol] == OCCUPIED) {
                isOnTop = true;
                break;
            }
        }
    }

    private void initializeBoard() {
        for (int currentRow = 0; currentRow < ROWS; currentRow++) {
            for (int currentCol = 0; currentCol < COLS; currentCol++) {
                BOARD[currentRow][currentCol] = EMPTY;
            }
        }
    }

    public boolean isOnTop() {
        return isOnTop;
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }
}
