package tetris;

import java.util.Scanner;

public class Game {
    Scanner scanner;
    static Board board;

    public Game(int rows, int cols) {
        scanner = new Scanner(System.in);
        board = new Board(rows, cols);
        printBoard(board.getBoard());
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
                board.breakPieces();
                printBoard(board.getBoard());
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
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveRight();
                    matrix = createMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "left":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveLeft();
                    matrix = createMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "down":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.moveDown();
                    matrix = createMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "rotate":
                    if (currentPiece.isHitFloor()) {
                        option = "exit";
                    }
                    currentPiece.rotatePiece();
                    matrix = createMatrix(currentPiece);
                    printBoard(matrix);
                    break;
                case "exit":
                    return "exit";
            }
        }
        if (board.isOnTop()) {
            System.out.println("Game Over!");
            return "exit";
        } else {
            return "";
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
        char[][] board = createMatrix(currentPiece);
        printBoard(board);
        return menuMovePiece(scanner, currentPiece);
    }

    private char[][] createMatrix(TetrisPiece currentPiece) {
        char[][] tempBoard = board.getBoard();
        char[][] currentPieceMatrix = currentPiece.getPiece();
        for (int currentRows = 0; currentRows < currentPieceMatrix.length; currentRows++) {
            for (int currentCols = 0; currentCols < currentPieceMatrix[0].length; currentCols++) {
                int tempRow = currentPiece.getyPosition() + currentRows;
                int tempCol = currentPiece.getxPosition() + currentCols;
                if (currentPieceMatrix[currentRows][currentCols] == '0') {
                    tempBoard[tempRow][tempCol] = currentPieceMatrix[currentRows][currentCols];
                }
            }
        }
        currentPiece.setHitLeftWall(currentPiece.getxPosition() == 0);
        currentPiece.setHitRightWall(currentPiece.getxPosition()+currentPiece.getCols() == board.getCols());
        if (currentPiece.isHitFloor()) {
            board.saveBoard(tempBoard);
        }
        if(currentPiece.getyPosition()+currentPiece.getRows() == board.getRows()){
            currentPiece.hitFloor();
        }
        return tempBoard;
    }

    private void printBoard(char[][] matrix) {
        for (char[] characters : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(characters[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
