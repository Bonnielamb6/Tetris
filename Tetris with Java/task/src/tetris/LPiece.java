package tetris;

import java.util.ArrayList;
import java.util.List;

public class LPiece extends TetrisPiece{

    public LPiece() {
        super();
        setxPosition(3);
    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{1, 5, 9, 10});
        tempPieceCoordinates.add(new Integer[]{2, 4, 5, 6});
        tempPieceCoordinates.add(new Integer[]{1, 2, 6, 10});
        tempPieceCoordinates.add(new Integer[]{4, 5, 6, 8});
        return new ArrayList<>(tempPieceCoordinates);
    }
}
