package tetris;

import java.util.ArrayList;
import java.util.List;

public abstract class TetrisPiece {
    protected int xPosition;
    protected int yPosition;
    protected List<Character[][]> pieceStates;
    protected int indexPieceState;

    public TetrisPiece() {
        indexPieceState = 0;
        xPosition = 0;
        yPosition = 0;
        List<Integer[]> pieces = initPieceStates();
        pieceStates = initList(pieces);
    }

    public int getxPosition(){
        return xPosition;
    }

    public void setxPosition(int xPosition){
        this.xPosition = xPosition;
    }

    public int getyPosition(){
        return yPosition;
    }

    public void setyPosition(int yPosition){
        this.yPosition = yPosition;
    }

    public List<Character[][]> getPieceStates(){
        List<Character[][]> copy = new ArrayList<>();
        for (Character[][] state : pieceStates) {
            Character[][] stateCopy = new Character[state.length][];
            for (int i = 0; i < state.length; i++) {
                stateCopy[i] = state[i].clone();
            }
            copy.add(stateCopy);
        }
        return copy;
    }

    public Character[][] getCurrentPieceState(){
        return pieceStates.get(indexPieceState).clone();
    }

    public void rotatePiece(){
        moveDown();
        indexPieceState++;
        if (indexPieceState >= pieceStates.size()) {
            indexPieceState = 0;
        }
    }

    public void moveDown(){
        setyPosition(yPosition + 1);
    }

    public void moveLeft(){
        moveDown();
        setxPosition(xPosition - 1);
    }

    public void moveRight() {
        moveDown();
        setxPosition(xPosition + 1);
    }

    protected abstract List<Integer[]> initPieceStates();

    protected List<Character[][]> initList(List<Integer[]> pieces){
        List<Character[][]> tempPieceStates = new ArrayList<>();
        for (Integer[] piece : pieces) {
            short counter = 0;
            Character[][] currentPiece = new Character[4][4];
            for (int cols = 0; cols < 4; cols++) {
                for (int rows = 0; rows < 4; rows++) {
                    if (counter < 4 && piece[counter] == ((cols * 4) + (rows))) {
                        counter++;
                        currentPiece[cols][rows] = '0';
                    } else {
                        currentPiece[cols][rows] = '-';
                    }
                }
            }
            tempPieceStates.add(currentPiece);
        }
        return new ArrayList<>(tempPieceStates);
    }

}
