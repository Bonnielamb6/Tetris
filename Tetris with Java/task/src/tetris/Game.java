package tetris;

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private final Board BOARD;

    public Game(int rows, int cols) {
        scanner = new Scanner(System.in);
        BOARD = new Board(rows, cols);
        printBoard(BOARD.getBoard());
    }

    public void start() {
        while (!generalMenu(scanner).equalsIgnoreCase("exit")) {

        }
    }

    private String generalMenu(Scanner scanner) {
        String option = scanner.nextLine().toLowerCase();
        switch (option) {
            case "piece":
                option = menuPieces(scanner);
                break;
            case "break":
                BOARD.breakPieces();
                printBoard(BOARD.getBoard());
                break;
        }
        return option;
    }

    public String menuMovePiece(Scanner scanner, TetrisPiece currentPiece) {
        char[][] matrix;
        String option = "";
        while (!option.equals("exit")) {
            option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "right":
                    if (currentPiece.isHitFloor()) {        //The piece can move one more time even if it seems
                        option = "exit";                    //to be in the floor for the first time
                    }                                       //that is why it is checked before the process instead of
                    currentPiece.moveRight();               //being checked at the end
                    matrix = modifyMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "left":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveLeft();
                    matrix = modifyMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "down":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveDown();
                    matrix = modifyMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "rotate":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    adjustRotation(currentPiece);
                    currentPiece.rotatePiece();
                    matrix = modifyMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "exit":
                    return "exit";
            }
        }
        if (BOARD.isOnTop()) {
            System.out.println("Game Over!");
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
    }

    private String menuPieces(Scanner scanner) {
        char option = scanner.nextLine().toUpperCase().charAt(0);
        TetrisPiece currentPiece;
        switch (option) {
            case 'T', 'O', 'L', 'J', 'I', 'S', 'Z':
                currentPiece = new TetrisPiece(option);
                break;
            default:
                System.out.println("That option is not valid");
                return "";
        }
        char[][] board = modifyMatrix(currentPiece);
        printBoard(board);
        return menuMovePiece(scanner, currentPiece);
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
        if (!(row >= BOARD.getRows())) {
            if (tempBoard[row][col] == TetrisPiece.getOCCUPIED()) {
                currentPiece.hitFloor();
            }
        }
    }

    private void checkWalls(TetrisPiece currentPiece) {
        currentPiece.setHitLeftWall(currentPiece.getxPosition() == 0);
        currentPiece.setHitRightWall(currentPiece.getxPosition() + currentPiece.getCols() == BOARD.getCols());
        if (currentPiece.getyPosition() + currentPiece.getRows() == BOARD.getRows()) {
            currentPiece.hitFloor();
        }
    }

    private void printBoard(char[][] matrix) {
        for (char[] currentRow : matrix) {
            for (int currentCol = 0; currentCol < matrix[0].length; currentCol++) {
                System.out.print(currentRow[currentCol] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
