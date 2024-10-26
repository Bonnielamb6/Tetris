package org.example;

public class Main {
    public static void main(String[] args) {
        ScannerWrapper scanner = new ScannerWrapper();
        int rows = scanner.getIntInput();
        int cols = scanner.getIntInput();
        Board board = new Board(rows, cols);
        Game game = new Game(board,scanner);
        game.start();
    }
}
