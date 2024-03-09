package e1.domain.pieces.pawn;

import e1.Pair;
import e1.domain.pieces.Piece;
import e1.domain.pieces.PieceImpl;

public class PawnImpl implements Pawn {

    private final Piece piece;

    public PawnImpl(Pair<Integer, Integer> position) {
        this.piece = new PieceImpl(position,  new FixedPositionMoveStrategy());
    }

    @Override
    public boolean isAllowedMove(int row, int col) {
        return this.piece.isAllowedMove(row, col);
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
