package tetris;

import java.util.ArrayList;
import java.util.List;

public class JPiece extends TetrisPiece{

    public JPiece() {
        super();
        setxPosition(3);
    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{2, 6, 9, 10});
        tempPieceCoordinates.add(new Integer[]{0, 1, 2, 6});
        tempPieceCoordinates.add(new Integer[]{1, 2, 5, 9});
        tempPieceCoordinates.add(new Integer[]{1, 5, 6, 7});
        return new ArrayList<>(tempPieceCoordinates);
    }
}
