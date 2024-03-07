package e1.domain.pieces.strategies;

import e1.Pair;

public class FixedPositionMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Pair<Integer, Integer> currentPos, int row, int col) {
        return false;
    }
}
