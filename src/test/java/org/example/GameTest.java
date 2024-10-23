package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameTest {

    private final ScannerWrapper scanner;
    private final Board boardSpy;
    private final Game gameToTest;
    private final char[][] emptyBoard;
    private final PrintStream out;
    private final int ROWS = 20;
    private final int COLUMNS = 10;

    public GameTest() {
        scanner = Mockito.mock(ScannerWrapper.class);
        Board realBoard = new Board(ROWS, COLUMNS);
        boardSpy = Mockito.spy(realBoard);
        out = Mockito.mock(PrintStream.class);
        gameToTest = new Game(boardSpy, scanner);
        emptyBoard = new char[][]{
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
        };
    }

    @Test
    void startExit() {
        doReturn(emptyBoard).when(boardSpy).getBoard();
        when(scanner.getUserInput()).thenReturn("exit");
        gameToTest.start();
        verify(scanner).getUserInput();
    }

    @Test
    void startPiece() {
        doReturn(emptyBoard).when(boardSpy).getBoard();
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("t").thenReturn("exit");
        gameToTest.start();
        verify(scanner, times(3)).getUserInput();
    }

    @Test
    void startBreakPieces() {
        doReturn(emptyBoard).when(boardSpy).getBoard();
        when(scanner.getUserInput()).thenReturn("break").thenReturn("exit");
        gameToTest.start();
        verify(boardSpy, times(1)).breakPieces();
    }

    @Test
    void menuMovePieceRight() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("t").thenReturn("right").thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void menuMovePieceLeft() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("t").thenReturn("left").thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void menuMovePieceDown() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("t").thenReturn("down").thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void menuRotatePiece() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("t").thenReturn("rotate").thenReturn("exit");
        gameToTest.start();

    }

    @Test
    void adjustRotationRight() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("i").thenReturn("right").thenReturn("right").thenReturn("right")
                .thenReturn("right").thenReturn("right").thenReturn("rotate").thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void adjustRotationLeft() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("i").thenReturn("left").thenReturn("left").thenReturn("left")
                .thenReturn("left").thenReturn("left").thenReturn("rotate").thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void adjustRotationDown() {
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("s").thenReturn("rotate").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("rotate").thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void looseGame() {
        ArgumentCaptor<String>captor = ArgumentCaptor.forClass(String.class);
        System.setOut(out);
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("i").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("piece").thenReturn("i").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("piece").thenReturn("i").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("piece").thenReturn("i").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("piece")
                .thenReturn("i").thenReturn("down").thenReturn("exit");
        gameToTest.start();
        verify(out,atLeastOnce()).print(captor.capture());
        assertEquals("Game Over!\n", captor.getValue());
    }

    @Test
    void tryRightAfterFloor(){
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("i").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("right")
                .thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void tryLeftAfterFloor(){
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("i").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down")
                .thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("down").thenReturn("left")
                .thenReturn("exit");
        gameToTest.start();
    }

    @Test
    void notValidPiece(){
        ArgumentCaptor<String>captor = ArgumentCaptor.forClass(String.class);
        System.setOut(out);
        when(scanner.getUserInput()).thenReturn("piece").thenReturn("h").thenReturn("exit");
        gameToTest.start();
        verify(out,atLeastOnce()).print(captor.capture());
        assertEquals("That option is not valid\n", captor.getValue());
    }
}