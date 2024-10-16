package tetris;

import java.util.ArrayList;
import java.util.List;

public class IPiece extends TetrisPiece{

    public IPiece() {
        super();
        xPosition = 3;
    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{1, 5, 9, 13});
        tempPieceCoordinates.add(new Integer[]{0, 1, 2, 3});
        tempPieceCoordinates.add(new Integer[]{1, 5, 9, 13});
        tempPieceCoordinates.add(new Integer[]{0, 1, 2, 3});
        return new ArrayList<>(tempPieceCoordinates);
    }
}
