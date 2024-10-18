package tetris;

import java.util.Scanner;

/*      TO DO LIST
 *
 *       -SAVE THE GRID WHEN A PIECE HITS THE FLOOR         ALREADY DONE
 *       -BE ABLE TO REQUEST MORE THAN ONE PIECE            ALREADY DONE
 *       -PIECE BECOMES STATIC WHEN HITS ANOTHER PIECE      ALREADY DONE
 *       -WHEN A ROW IS FILLED, THE ROW DISAPPEARS AND      ALREADY DONE
 *        DROPS THE PIECES THAT WERE ABOVE
 *       -THE GAME ENDS WHEN THE PIECE THAT BECOMES STATIC HITS THE ROOF    ALREADY DONE
 *
 */
public class Main {
    static Grid grid;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        grid = new Grid(rows,cols);
        grid.saveMatrix(initializeMatrix(rows, cols));
        printMatrix(grid.getMatrix());
        while (!generalMenu(scanner).equalsIgnoreCase("exit")) {

        }
    }

    private static String generalMenu(Scanner scanner) {
        String option = scanner.nextLine().toLowerCase();
        switch (option) {
            case "piece":
                option = menuPieces(scanner);
                break;
            case "break":
                grid.breakPieces();
                printMatrix(grid.getMatrix());
                break;
        }
        return option;
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

    public static String menuMovePiece(Scanner scanner, TetrisPiece currentPiece) {
        Character[][] matrix;
        String option ="";
        while (!option.equals("exit")) {
            option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "right":
                    if(currentPiece.isHitFloor()){option = "exit";}
                    currentPiece.moveRight();
                    matrix = createMatrix(currentPiece);
                    printMatrix(matrix);
                    break;
                case "left":
                    if(currentPiece.isHitFloor()){option = "exit";}
                    currentPiece.moveLeft();
                    matrix = createMatrix(currentPiece);
                    printMatrix(matrix);
                    break;
                case "down":
                    if(currentPiece.isHitFloor()){option = "exit";}
                    currentPiece.moveDown();
                    matrix = createMatrix(currentPiece);
                    printMatrix(matrix);
                    break;
                case "rotate":
                    if(currentPiece.isHitFloor()){option = "exit";}
                    currentPiece.rotatePiece();
                    matrix = createMatrix(currentPiece);
                    printMatrix(matrix);
                    break;
                case "exit":
                    return "exit";
            }
        }
        if(grid.isOnTop()){
            System.out.println("Game Over!");
            return "exit";
        }else{
            return "";
        }
    }

    public static Character[][] createMatrix(TetrisPiece currentPiece) {
        Character[][] matrixTemp = grid.getMatrix();

        for (int i = 0; i < currentPiece.getCurrentPieceState().length; i++) {
            for (int j = 0; j < currentPiece.getCurrentPieceState().length; j++) {
                int tempi = currentPiece.getyPosition() + i;
                int tempj = currentPiece.getxPosition() + j;
                if (currentPiece.getCurrentPieceState()[i][j] == '0') {
                    currentPiece.setHitRightWall((currentPiece.getxPosition() + j) == matrixTemp[0].length - 1);
                    currentPiece.setHitLeftWall((currentPiece.getxPosition() + j) == 1);
                    if (currentPiece.getyPosition() + i >= matrixTemp.length - 1 || matrixTemp[tempi+1][tempj]=='0' ) {
                        currentPiece.hitFloor();
                    }
                    matrixTemp[tempi][tempj] = currentPiece.getCurrentPieceState()[i][j];
                }
            }
        }
        if(currentPiece.isHitFloor()){
            grid.saveMatrix(matrixTemp);
        }
        return matrixTemp;
    }

    public static void printMatrix(Character[][] matrix) {
        for (Character[] characters : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(characters[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String menuPieces(Scanner scanner) {
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
        Character[][] matrix = createMatrix(currentPiece);
        printMatrix(matrix);
        return menuMovePiece(scanner, currentPiece);
    }

}
