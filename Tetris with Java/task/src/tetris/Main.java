package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char option = scanner.nextLine().toUpperCase().charAt(0);
        TetrisPiece currentPiece;
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        Character[][] matrix;

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
                return;

        }
        matrix = initializeMatrix(rows, cols);
        printMatrix(matrix, rows, cols);
        matrix = createMatrix(currentPiece, rows, cols);
        printMatrix(matrix, rows, cols);
        menu(scanner, currentPiece, rows, cols);
    }

    private static Character[][] initializeMatrix(int rows, int cols) {
        Character[][] matrixTemp = new Character[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                matrixTemp[i][j] = '-';
            }

        }
        return matrixTemp;
    }

    public static void menu(Scanner scanner, TetrisPiece currentPiece, int rows, int cols) {
        Character[][] matrix;
        String option = "";
        while (!option.equals("exit")) {
            option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "right":
                    currentPiece.moveRight();
                    matrix = createMatrix(currentPiece, rows, cols);
                    printMatrix(matrix, rows, cols);
                    break;
                case "left":
                    currentPiece.moveLeft();
                    matrix = createMatrix(currentPiece, rows, cols);
                    printMatrix(matrix, rows, cols);
                    break;
                case "down":
                    currentPiece.moveDown();
                    matrix = createMatrix(currentPiece, rows, cols);
                    printMatrix(matrix, rows, cols);
                    break;
                case "rotate":
                    currentPiece.rotatePiece();
                    matrix = createMatrix(currentPiece, rows, cols);
                    printMatrix(matrix, rows, cols);
                    break;
            }
        }
    }

    public static Character[][] createMatrix(TetrisPiece currentPiece, int rows, int cols) {
        Character[][] matrixTemp = initializeMatrix(rows, cols);

        for (int i = 0; i < currentPiece.getCurrentPieceState().length; i++) {
            for (int j = 0; j < currentPiece.getCurrentPieceState().length; j++) {
                int tempi = currentPiece.getyPosition() + i;
                int tempj = currentPiece.getxPosition() + j;
                if (currentPiece.getCurrentPieceState()[i][j] == '0') {

                    currentPiece.setHitRightWall((currentPiece.getxPosition() + j) == matrixTemp[0].length-1);

                    currentPiece.setHitLeftWall((currentPiece.getxPosition() + j) == 1);

                    if (currentPiece.getyPosition() + i >= matrixTemp.length-1) {
                        currentPiece.hitFloor();

                    }
                    matrixTemp[tempi][tempj] = currentPiece.getCurrentPieceState()[i][j];
                }

            }
        }
        return matrixTemp;
    }

    public static void printMatrix(Character[][] matrix, int rows, int cols) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(matrix[i][j] + "\s");
            }
            System.out.println();
        }
        System.out.println();
    }

}
