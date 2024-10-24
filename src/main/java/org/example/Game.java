package org.example;

public class Game {
    private final ScannerWrapper SCANNER;
    private final Board BOARD;
    private final TetrisPiece currentPiece = new TetrisPiece();

    public Game(Board board, ScannerWrapper scanner) {
        this.BOARD = board;
        this.SCANNER = scanner;
    }

    public void start() {
        System.out.println((createBoard(BOARD.getBoard())));
        while (true) {
            if (generalMenu().equalsIgnoreCase("exit")) {
                break;
            }
        }
    }

    private String generalMenu() {
        String option = SCANNER.getUserInput().toLowerCase();
        switch (option) {
            case "piece":
                option = menuPieces();
                break;
            case "break":
                BOARD.breakPieces();
                System.out.println((createBoard(BOARD.getBoard())));
                break;
        }
        return option;
    }

    public String menuMovePiece(TetrisPiece currentPiece) {
        char[][] matrix;
        String option = "";
        while (!option.equals("exit")) {
            option = SCANNER.getUserInput().toLowerCase();
            switch (option) {
                case "right":
                    if (currentPiece.isHitFloor()) {        //The piece can move one more time even if it seems
                        option = "exit";                    //to be in the floor for the first time
                    }                                       //that is why it is checked before the process instead of
                    currentPiece.moveRight();               //being checked at the end
                    matrix = modifyMatrix(currentPiece);
                    System.out.println((createBoard(matrix)));
                    break;
                case "left":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveLeft();
                    matrix = modifyMatrix(currentPiece);
                    System.out.println((createBoard(matrix)));
                    break;
                case "down":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveDown();
                    matrix = modifyMatrix(currentPiece);
                    System.out.println((createBoard(matrix)));
                    break;
                case "rotate":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    adjustRotation(currentPiece);
                    currentPiece.rotatePiece();
                    matrix = modifyMatrix(currentPiece);
                    System.out.println((createBoard(matrix)));
                    break;
                case "exit":
                    return "exit";
            }
        }
        if (BOARD.isOnTop()) {
            System.out.print("Game Over!\n");
            return "exit";
        } else {
            return "";
        }
    }

    private void adjustRotation(TetrisPiece currentPiece) {
        if (currentPiece.getxPosition() + currentPiece.getRows() > BOARD.getCols()) {
            currentPiece.setxPosition(BOARD.getCols() - currentPiece.getRows());
        }
        if (currentPiece.isHitLeftWall()) {
            currentPiece.setxPosition(0);
        }
        if (currentPiece.isHitFloor()) {
            currentPiece.setyPosition(BOARD.getRows() - currentPiece.getCols());
        }
        if (currentPiece.getyPosition() + currentPiece.getCols() >= BOARD.getRows()) {
            currentPiece.setyPosition(BOARD.getRows() - currentPiece.getCols());
        }
    }

    private String menuPieces() {
        char option = SCANNER.getUserInput().toUpperCase().charAt(0);
        switch (option) {
            case 'T', 'O', 'L', 'J', 'I', 'S', 'Z':
                currentPiece.choosePiece(option);
                break;
            default:
                System.out.print("That option is not valid\n");
                return "";
        }
        char[][] board = modifyMatrix(currentPiece);
        System.out.println((createBoard(board)));
        return menuMovePiece(currentPiece);
    }

    private char[][] modifyMatrix(TetrisPiece currentPiece) {
        char[][] tempBoard = BOARD.getBoard();
        char[][] currentPieceMatrix = currentPiece.getPiece();
        for (int currentRow = 0; currentRow < currentPieceMatrix.length; currentRow++) {
            for (int currentCol = 0; currentCol < currentPieceMatrix[0].length; currentCol++) {
                int tempRow = currentPiece.getyPosition() + currentRow;
                int tempCol = currentPiece.getxPosition() + currentCol;
                if (currentPiece.isOccupied(currentRow, currentCol)) {
                    tempBoard[tempRow][tempCol] = currentPieceMatrix[currentRow][currentCol];
                    checkPieceBelow(tempBoard, currentPiece, tempRow + 1, tempCol);
                }
            }
        }
        if (currentPiece.isHitFloor()) {
            BOARD.saveBoard(tempBoard);
        }
        checkWalls(currentPiece);
        return tempBoard;
    }

    private void checkPieceBelow(char[][] tempBoard, TetrisPiece currentPiece, int row, int col) {
        if (!(row >= BOARD.getRows())
                && tempBoard[row][col] == TetrisPiece.getOCCUPIED()) {
            currentPiece.hitFloor();
        }
    }

    private void checkWalls(TetrisPiece currentPiece) {
        currentPiece.setHitLeftWall(currentPiece.getxPosition() == 0);
        currentPiece.setHitRightWall(currentPiece.getxPosition() + currentPiece.getCols() == BOARD.getCols());
        if (currentPiece.getyPosition() + currentPiece.getRows() == BOARD.getRows()) {
            currentPiece.hitFloor();
        }
    }

    private String createBoard(char[][] matrix) {
        StringBuilder matrixToPrint = new StringBuilder();
        for (char[] currentRow : matrix) {
            for (int currentCol = 0; currentCol < matrix[0].length; currentCol++) {
                matrixToPrint.append(currentRow[currentCol]);
                matrixToPrint.append(" ");
            }
            matrixToPrint.append("\n");
        }
        matrixToPrint.append("\n");
        return matrixToPrint.toString();
    }
}
