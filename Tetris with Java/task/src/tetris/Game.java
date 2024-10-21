package tetris;

import java.util.Scanner;

public class Game {
    Scanner scanner;
    static Grid grid;

    public Game(int rows, int cols) {
        scanner = new Scanner(System.in);
        grid = new Grid(rows, cols);
        printBoard(grid.getBoard());
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
                grid.breakPieces();
                printBoard(grid.getBoard());
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
        if (grid.isOnTop()) {
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
            case 'T':
                currentPiece = new TPiece();
                break;
            case 'O':
                currentPiece = new OPiece();
                break;
            case 'L':
                currentPiece = new LPiece();
                break;
            case 'J':
                currentPiece = new JPiece();
                break;
            case 'I':
                currentPiece = new IPiece();
                break;
            case 'S':
                currentPiece = new SPiece();
                break;
            case 'Z':
                currentPiece = new ZPiece();
                break;
            default:
                System.out.println("That option is not valid");
                return "";
        }
        char[][] matrix = createMatrix(currentPiece);
        printBoard(matrix);
        return menuMovePiece(scanner, currentPiece);
    }

    private char[][] createMatrix(TetrisPiece currentPiece) {
        char[][] matrixTemp = grid.getBoard();

        for (int i = 0; i < currentPiece.getCurrentPieceState().length; i++) {
            for (int j = 0; j < currentPiece.getCurrentPieceState().length; j++) {
                int tempi = currentPiece.getyPosition() + i;
                int tempj = currentPiece.getxPosition() + j;
                if (currentPiece.getCurrentPieceState()[i][j] == '0') {
                    currentPiece.setHitRightWall((currentPiece.getxPosition() + j) == matrixTemp[0].length - 1);
                    currentPiece.setHitLeftWall((currentPiece.getxPosition() + j) == 1);
                    if (currentPiece.getyPosition() + i >= matrixTemp.length - 1 || matrixTemp[tempi + 1][tempj] == '0') {
                        currentPiece.hitFloor();
                    }
                    matrixTemp[tempi][tempj] = currentPiece.getCurrentPieceState()[i][j];
                }
            }
        }
        if (currentPiece.isHitFloor()) {
            grid.saveMatrix(matrixTemp);
        }
        return matrixTemp;
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
