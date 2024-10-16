package tetris;

import java.util.ArrayList;
import java.util.List;

public class ZPiece extends TetrisPiece{
    public ZPiece() {
        super();
        setxPosition(4);
    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{0, 1, 5, 6});
        tempPieceCoordinates.add(new Integer[]{2, 5, 6, 9});
        return new ArrayList<>(tempPieceCoordinates);
    }
}
