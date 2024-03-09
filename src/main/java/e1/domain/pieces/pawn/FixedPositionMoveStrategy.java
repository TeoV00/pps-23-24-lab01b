package e1.domain.pieces.pawn;

import e1.Pair;
import e1.domain.pieces.MoveStrategy;

public class FixedPositionMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Pair<Integer, Integer> currentPos, int row, int col) {
        return false;
    }
}
