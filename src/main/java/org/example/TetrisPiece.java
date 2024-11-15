package org.example;

public class TetrisPiece {
    private static final char EMPTY = '-';
    private static final char OCCUPIED = '0';
    public static final int MAX_SPACES_OCCUPIED = 4;
    public static final int CENTER = 4;
    private int xPosition;
    private int yPosition;
    private boolean hitFloor;
    private boolean hitLeftWall;
    private boolean hitRightWall;
    private int rows;
    private int cols;
    private char[][] piece;
    int[] coordinates;

    public TetrisPiece() {
        initValues();
    }

    private void initValues(){
        xPosition = CENTER;
        yPosition = 0;
        hitFloor = false;
        hitLeftWall = false;
        hitRightWall = false;
    }

    public void choosePiece(char piece){
        switch (piece) {
            case 'I':
                initIPiece();
                break;
            case 'L':
                initLPiece();
                break;
            case 'O':
                initOPiece();
                break;
            case 'Z':
                initZPiece();
                break;
            case 'S':
                initSPiece();
                break;
            case 'J':
                initJPiece();
                break;
            case 'T':
                initTPiece();
                break;
        }
        initPiece();
    }

    private void initIPiece() {
        rows = 4;
        cols = 1;
        coordinates = new int[]{0, 1, 2, 3};
    }

    private void initLPiece() {
        rows = 3;
        cols = 2;
        coordinates = new int[]{0, 2, 4, 5};
    }

    private void initOPiece() {
        rows = 2;
        cols = 2;
        coordinates = new int[]{0, 1, 2, 3};
    }

    private void initZPiece() {
        rows = 2;
        cols = 3;
        coordinates = new int[]{0, 1, 4, 5};
    }

    private void initSPiece() {
        rows = 2;
        cols = 3;
        coordinates = new int[]{1, 2, 3, 4};
    }

    private void initJPiece() {
        rows = 3;
        cols = 2;
        coordinates = new int[]{1, 3, 4, 5};
    }

    private void initTPiece() {
        rows = 2;
        cols = 3;
        coordinates = new int[]{1, 3, 4, 5};
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void rotatePiece() {
        moveDown();
        char[][] pieceTemp = new char[cols][rows];
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentCol = 0; currentCol < cols; currentCol++) {
                pieceTemp[cols - 1 - currentCol][currentRow] = piece[currentRow][currentCol];
            }
        }
        piece = pieceTemp;
        rows = piece.length;
        cols = piece[0].length;

    }

    public void moveDown() {
        if (!isHitFloor()) {
            setyPosition(yPosition + 1);
        }
    }

    public void moveLeft() {
        moveDown();
        if (!isHitLeftWall()) {
            setxPosition(xPosition - 1);
        }
    }

    public void moveRight() {
        moveDown();
        if (!isHitRightWall()) {
            setxPosition(xPosition + 1);
        }
    }

    private void initPiece() {
        initValues();
        piece = new char[rows][cols];
        int coordinatePointer = 0;
        for (int currentRows = 0; currentRows < rows; currentRows++) {
            for (int currentCols = 0; currentCols < cols; currentCols++) {
                if (coordinatePointer < MAX_SPACES_OCCUPIED && (currentRows * cols + currentCols) == coordinates[coordinatePointer]) {
                    piece[currentRows][currentCols] = OCCUPIED;
                    coordinatePointer++;
                } else {
                    piece[currentRows][currentCols] = EMPTY;
                }
            }
        }
    }

    public boolean isCellPartOfPiece(int row, int col) {
        return piece[row][col] == OCCUPIED;
    }

    public boolean isHitFloor() {
        return hitFloor;
    }

    public void hitFloor() {
        hitFloor = true;
    }

    public boolean isHitLeftWall() {
        return hitLeftWall;
    }

    public void setHitLeftWall(boolean hitLeftWall) {
        this.hitLeftWall = hitLeftWall;
    }

    public boolean isHitRightWall() {
        return hitRightWall;
    }

    public void setHitRightWall(boolean hitRightWall) {
        this.hitRightWall = hitRightWall;
    }

    public char[][] getPiece() {
        return piece.clone();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public static char getOCCUPIED() {
        return OCCUPIED;
    }

}
