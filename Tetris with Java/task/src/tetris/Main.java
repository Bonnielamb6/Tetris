package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char option = scanner.nextLine().toUpperCase().charAt(0);
        List<Character[][]> piecesList = new ArrayList<>();
        TetrisPiece currentPiece = null;
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
        menu(scanner, currentPiece, matrix, rows, cols);
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

    public static void menu(Scanner scanner, TetrisPiece currentPiece, Character[][] matrix, int rows, int cols) {
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

        for (int i = 0; i < currentPiece.getCurrentPieceState().length; i++) {//3
            for (int j = 0; j < currentPiece.getCurrentPieceState().length; j++) {//1
                int tempi = currentPiece.getyPosition() + i;
                int tempj = currentPiece.getxPosition() + j;
                if ((currentPiece.getxPosition() + j) >= matrixTemp[0].length) {
                    tempj = currentPiece.getxPosition() + j - rows;

                }
                if ((currentPiece.getxPosition() + j) < 0) {
                    tempj = currentPiece.getxPosition() + j + rows;

                }
                if (currentPiece.getyPosition() + i >= matrixTemp.length) {
                    tempi = currentPiece.getyPosition() + i - cols;

                }
                matrixTemp[tempi][tempj] = currentPiece.getCurrentPieceState()[i][j];


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
