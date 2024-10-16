package tetris;

import java.util.ArrayList;
import java.util.List;

public class OPiece extends TetrisPiece{

    public OPiece() {
        super();
        setxPosition(3);
    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{1,2,5,6});
        return new ArrayList<>(tempPieceCoordinates);
    }
}
