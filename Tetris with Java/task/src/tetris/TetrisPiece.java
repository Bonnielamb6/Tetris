package tetris;

import java.util.ArrayList;
import java.util.List;

public abstract class TetrisPiece {
    protected int xPosition;
    protected int yPosition;
    protected List<Character[][]> pieceStates;
    protected int indexPieceState;
    protected boolean hitFloor;
    protected boolean hitLeftWall;
    protected boolean hitRightWall;

    public TetrisPiece() {
        indexPieceState = 0;
        xPosition = 0;
        yPosition = 0;
        hitFloor = false;
        hitLeftWall = false;
        hitRightWall = false;
        List<Integer[]> pieces = initPieceStates();
        pieceStates = initList(pieces);
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }


    public Character[][] getCurrentPieceState() {
        return pieceStates.get(indexPieceState).clone();
    }

    public void rotatePiece() {
        if (!isHitFloor()) {
            moveDown();
            indexPieceState++;
            if (indexPieceState >= pieceStates.size()) {
                indexPieceState = 0;
            }
        }
    }

    public void moveDown() {
        if (!isHitFloor()) {
            setyPosition(yPosition + 1);
        }
    }

    public void moveLeft() {
        if (!isHitFloor()) {
            moveDown();
            if (!isHitLeftWall()) {
                setxPosition(xPosition - 1);
            }
        }
    }

    public void moveRight() {
        if (!isHitFloor()) {
            moveDown();
            if (!isHitRightWall()) {
                setxPosition(xPosition + 1);
            }
        }
    }

    protected abstract List<Integer[]> initPieceStates();

    protected List<Character[][]> initList(List<Integer[]> pieces) {
        List<Character[][]> tempPieceStates = new ArrayList<>();
        for (Integer[] piece : pieces) {
            short counter = 0;
            Character[][] currentPiece = new Character[4][4];
            for (int rows = 0; rows < 4; rows++) {
                for (int cols = 0; cols < 4; cols++) {
                    if (counter < 4 && piece[counter] == ((rows * 4) + (cols))) {
                        counter++;
                        currentPiece[rows][cols] = '0';
                    } else {
                        currentPiece[rows][cols] = '-';
                    }
                }
            }
            tempPieceStates.add(currentPiece);
        }
        return new ArrayList<>(tempPieceStates);
    }

    public boolean isHitFloor() {
        return hitFloor;
    }

    public void hitFloor() {
        hitFloor = true;
    }

    public boolean isHitLeftWall() {
        return hitLeftWall;
    }

    public void setHitLeftWall(boolean hitLeftWall) {
        this.hitLeftWall = hitLeftWall;
    }

    public boolean isHitRightWall() {
        return hitRightWall;
    }

    public void setHitRightWall(boolean hitRightWall) {
        this.hitRightWall = hitRightWall;
    }
}
