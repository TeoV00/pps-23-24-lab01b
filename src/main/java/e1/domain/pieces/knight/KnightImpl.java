package e1.domain.pieces.knight;

import e1.Pair;
import e1.domain.Piece;
import e1.domain.PieceImpl;

public class KnightImpl implements Knight {
    private final Piece piece;

    public KnightImpl(Pair<Integer, Integer> position) {
        this.piece = new PieceImpl(position, new KnightMoveStrategy());
    }

    @Override
    public boolean isAllowedMove(int row, int col) {
        return piece.isAllowedMove(row, col);
    }

    @Override
    public void move(int row, int col) {
        this.piece.move(row, col);
    }

    @Override
    public Pair<Integer, Integer> position() {
        return this.piece.position();
    }
}
