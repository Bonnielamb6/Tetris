package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisPieceTest {

    private final TetrisPiece pieceToTest;
    private final int ROWS = 3;
    private final int COLS = 2;

    public TetrisPieceTest() {
        pieceToTest = new TetrisPiece();
        pieceToTest.choosePiece('L');
    }

    @Test
    void initLPiece() {
        char[][] expected = new char[][]{
                {'0', '-'},
                {'0', '-'},
                {'0', '0'}
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void initSPiece() {
        pieceToTest.choosePiece('S');
        char[][] expected = new char[][]{
                {'-', '0', '0'},
                {'0', '0', '-'},
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void initIPiece() {
        pieceToTest.choosePiece('I');
        char[][] expected = new char[][]{
                {'0'},
                {'0'},
                {'0'},
                {'0'}
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void initJPiece() {
        pieceToTest.choosePiece('J');
        char[][] expected = new char[][]{
                {'-', '0'},
                {'-', '0'},
                {'0', '0'},
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void initOPiece() {
        pieceToTest.choosePiece('O');
        char[][] expected = new char[][]{
                {'0', '0'},
                {'0', '0'}
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void initZPiece() {
        pieceToTest.choosePiece('Z');
        char[][] expected = new char[][]{
                {'0', '0', '-'},
                {'-', '0', '0'}
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void initTPiece() {
        pieceToTest.choosePiece('T');
        char[][] expected = new char[][]{
                {'-', '0', '-'},
                {'0', '0', '0'},
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void getxPosition() {
        int expected = 4;
        assertEquals(expected, pieceToTest.getxPosition());
    }

    @Test
    void setxPosition() {
        int expectedBefore = 4;
        assertEquals(expectedBefore, pieceToTest.getxPosition());
        pieceToTest.setxPosition(7);
        int expectedAfter = 7;
        assertEquals(expectedAfter, pieceToTest.getxPosition());
    }

    @Test
    void getyPosition() {
        int expected = 0;
        assertEquals(expected, pieceToTest.getyPosition());
    }

    @Test
    void setyPosition() {
        int expectedBefore = 0;
        assertEquals(expectedBefore, pieceToTest.getyPosition());
        pieceToTest.setyPosition(7);
        int expectedAfter = 7;
        assertEquals(expectedAfter, pieceToTest.getyPosition());
    }

    @Test
    void rotatePiece() {
        int expectYBefore = 0;
        char[][] expectedBefore = new char[][]{
                {'0', '-'},
                {'0', '-'},
                {'0', '0'}
        };
        assertEquals(expectYBefore, pieceToTest.getyPosition());
        assertArrayEquals(expectedBefore, pieceToTest.getPiece());
        int expectedYAfter = 1;
        char[][] expectedAfter = new char[][]{
                {'-', '-', '0'},
                {'0', '0', '0'}
        };
        pieceToTest.rotatePiece();
        assertEquals(expectedYAfter, pieceToTest.getyPosition());
        assertArrayEquals(expectedAfter, pieceToTest.getPiece());
    }

    @Test
    void moveDown() {
        int expectedBefore = 0;
        assertEquals(expectedBefore, pieceToTest.getyPosition());
        pieceToTest.moveDown();
        int expectedAfter = 1;
        assertEquals(expectedAfter, pieceToTest.getyPosition());
    }

    @Test
    void notMoveDown() {
        int expectedBefore = 0;
        assertEquals(expectedBefore, pieceToTest.getyPosition());
        pieceToTest.hitFloor();
        pieceToTest.moveDown();
        int expectedAfter = 0;
        assertEquals(expectedAfter, pieceToTest.getyPosition());
    }

    @Test
    void moveLeft() {
        int expectedXBefore = 4;
        int expectedYBefore = 0;
        assertEquals(expectedXBefore, pieceToTest.getxPosition());
        assertEquals(expectedYBefore, pieceToTest.getyPosition());
        pieceToTest.moveLeft();
        int expectedXAfter = 3;
        int expectedYAfter = 1;
        assertEquals(expectedXAfter, pieceToTest.getxPosition());
        assertEquals(expectedYAfter, pieceToTest.getyPosition());
    }

    @Test
    void notMoveLeft() {
        int expectedXBefore = 4;
        int expectedYBefore = 0;
        assertEquals(expectedXBefore, pieceToTest.getxPosition());
        assertEquals(expectedYBefore, pieceToTest.getyPosition());
        pieceToTest.setHitLeftWall(true);
        pieceToTest.moveLeft();
        int expectedXAfter = 4;
        int expectedYAfter = 1;
        assertEquals(expectedXAfter, pieceToTest.getxPosition());
        assertEquals(expectedYAfter, pieceToTest.getyPosition());
    }

    @Test
    void moveRight() {
        int expectedXBefore = 4;
        int expectedYBefore = 0;
        assertEquals(expectedXBefore, pieceToTest.getxPosition());
        assertEquals(expectedYBefore, pieceToTest.getyPosition());
        pieceToTest.moveRight();
        int expectedXAfter = 5;
        int expectedYAfter = 1;
        assertEquals(expectedXAfter, pieceToTest.getxPosition());
        assertEquals(expectedYAfter, pieceToTest.getyPosition());
    }

    @Test
    void notMoveRight() {
        int expectedXBefore = 4;
        int expectedYBefore = 0;
        assertEquals(expectedXBefore, pieceToTest.getxPosition());
        assertEquals(expectedYBefore, pieceToTest.getyPosition());
        pieceToTest.setHitRightWall(true);
        pieceToTest.moveRight();
        int expectedXAfter = 4;
        int expectedYAfter = 1;
        assertEquals(expectedXAfter, pieceToTest.getxPosition());
        assertEquals(expectedYAfter, pieceToTest.getyPosition());
    }

    @Test
    void isOccupied() {
        int rowToCheck = 0;
        int colToCheck = 0;
        assertTrue(pieceToTest.isOccupied(rowToCheck, colToCheck));
    }

    @Test
    void isNotOccupied() {
        int rowToCheck = 1;
        int colToCheck = 1;
        assertFalse(pieceToTest.isOccupied(rowToCheck, colToCheck));
    }

    @Test
    void isHitFloor() {
        boolean expected = false;
        assertEquals(expected, pieceToTest.isHitFloor());
    }

    @Test
    void hitFloor() {
        boolean expectedBefore = false;
        assertEquals(expectedBefore, pieceToTest.isHitFloor());
        boolean expectedAfter = true;
        pieceToTest.hitFloor();
        assertEquals(expectedAfter, pieceToTest.isHitFloor());
    }

    @Test
    void isHitLeftWall() {
        boolean expected = false;
        assertEquals(expected, pieceToTest.isHitLeftWall());
    }

    @Test
    void setHitLeftWall() {
        boolean expectedBefore = false;
        assertEquals(expectedBefore, pieceToTest.isHitLeftWall());
        boolean expectedAfter = true;
        pieceToTest.setHitLeftWall(true);
        assertEquals(expectedAfter, pieceToTest.isHitLeftWall());
    }

    @Test
    void isHitRightWall() {
        boolean expected = false;
        assertEquals(expected, pieceToTest.isHitRightWall());
    }

    @Test
    void setHitRightWall() {
        boolean expectedBefore = false;
        assertEquals(expectedBefore, pieceToTest.isHitRightWall());
        boolean expectedAfter = true;
        pieceToTest.setHitRightWall(true);
        assertEquals(expectedAfter, pieceToTest.isHitRightWall());
    }

    @Test
    void getPiece() {
        char[][] expected = new char[][]{
                {'0', '-'},
                {'0', '-'},
                {'0', '0'}
        };
        assertArrayEquals(expected, pieceToTest.getPiece());
    }

    @Test
    void getRows() {
        assertEquals(ROWS, pieceToTest.getRows());
    }

    @Test
    void getCols() {
        assertEquals(COLS, pieceToTest.getCols());
    }

    @Test
    void getOCCUPIED() {
        char expected = '0';
        assertEquals(expected, TetrisPiece.getOCCUPIED());
    }
}