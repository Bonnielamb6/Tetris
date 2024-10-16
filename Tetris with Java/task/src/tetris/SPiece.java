package tetris;

import java.util.ArrayList;
import java.util.List;

public class SPiece extends TetrisPiece {

    public SPiece() {
        super();
        setxPosition(3);

    }

    @Override
    protected List<Integer[]> initPieceStates() {
        List<Integer[]> tempPieceCoordinates = new ArrayList<>();
        tempPieceCoordinates.add(new Integer[]{1, 2, 4, 5});
        tempPieceCoordinates.add(new Integer[]{1, 5, 6, 10});
        return new ArrayList<>(tempPieceCoordinates);
    }

}
