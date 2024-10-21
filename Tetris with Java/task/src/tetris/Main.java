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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Game game = new Game(rows,cols);
        game.start();
    }
}
