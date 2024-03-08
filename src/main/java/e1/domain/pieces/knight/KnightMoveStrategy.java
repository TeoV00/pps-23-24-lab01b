package e1.domain.pieces.knight;

import e1.Pair;
import e1.domain.pieces.strategies.MoveStrategy;

public class KnightMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Pair<Integer, Integer> currentPos, int row, int col) {
        int x = row - currentPos.getX();
        int y = col - currentPos.getY();
        return x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3;
    }
}
