package e1.domain.pieces.strategies;

import e1.Pair;

public interface MoveStrategy {
    boolean canMove(Pair<Integer, Integer> currentPos, int row, int col);
}
