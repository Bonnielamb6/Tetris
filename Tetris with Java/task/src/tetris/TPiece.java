package tetris;

import java.util.ArrayList;
import java.util.List;

public class TPiece extends TetrisPiece{

    public TPiece() {
        super();
        setxPosition(3);
    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{1,5,6,9}); //right
        tempPieceCoordinates.add(new Integer[]{1,4,5,6}); //top
        tempPieceCoordinates.add(new Integer[]{2,5,6,10}); //left
        tempPieceCoordinates.add(new Integer[]{1,2,3,6}); //bottom
        return new ArrayList<>(tempPieceCoordinates);
    }
}
