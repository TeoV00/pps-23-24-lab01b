package e1.domain;

import e1.Pair;
import e1.domain.pieces.strategies.MoveStrategy;

import java.util.Objects;

public class PieceImpl implements Piece {

    private Pair<Integer, Integer> position;
    private final MoveStrategy moveStrategy;

    public PieceImpl(Pair<Integer, Integer> initialPosition, MoveStrategy moveStrategy) {
        this.position = initialPosition;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean isAllowedMove(int row, int col) {
        return this.moveStrategy.canMove(this.position, row, col);
    }

    @Override
    public void move(int row, int col) {
        if (!isAllowedMove(row, col)) {
            throw new IllegalStateException("Not Allowed move: " + new Pair<>(row, col));
        }
        this.position = new Pair<>(row, col);
    }

    @Override
    public Pair<Integer, Integer> position() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceImpl piece)) return false;
        return Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
