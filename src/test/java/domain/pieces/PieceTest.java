package domain.pieces;

import e1.Pair;
import e1.domain.pieces.Piece;
import e1.domain.pieces.PieceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    private final int NO_STEP = 0;
    private final int ONE_STEP = 1;
    private final Pair<Integer, Integer> INITIAL_POSITION = new Pair<>(2,2);

    @Test
    void testPieceWithNoMoveLimitation() {
        Piece piece = new PieceImpl(INITIAL_POSITION, (pos, row, col) -> true);
        assertAll(
            () -> assertTrue(piece.isAllowedMove(3,5)),
            () -> assertTrue(piece.isAllowedMove(9,2))
        );
    }

    @Test
    void testPieceWithOneStepMove() {
        Piece kingPiece = new PieceImpl(INITIAL_POSITION, this::kingMoveStrategy);

        var oneStepDiagonalMove = new Pair<>(INITIAL_POSITION.getX() + ONE_STEP, INITIAL_POSITION.getY() + ONE_STEP);
        var oneStepLeftMove = new Pair<>(INITIAL_POSITION.getX() - ONE_STEP, INITIAL_POSITION.getY());
        var wrongLeftMove = new Pair<>(INITIAL_POSITION.getX() + 2, INITIAL_POSITION.getY());
        assertAll(
            () -> assertTrue(kingPiece.isAllowedMove(oneStepDiagonalMove.getX(), oneStepDiagonalMove.getY())),
            () -> assertTrue(kingPiece.isAllowedMove(oneStepLeftMove.getX(), oneStepLeftMove.getY())),
            () -> assertFalse(kingPiece.isAllowedMove(wrongLeftMove.getX(), wrongLeftMove.getY()))
        );
    }

    private boolean kingMoveStrategy(Pair<Integer, Integer> currentPos, int row, int col) {
        return (Math.abs(row - currentPos.getX()) == ONE_STEP || Math.abs(row - currentPos.getX()) == NO_STEP) &&
                (Math.abs(col - currentPos.getY()) == ONE_STEP || Math.abs(col - currentPos.getY()) == NO_STEP);
    }

    @Test
    void testNotAllowedMove() {
        Piece piece = new PieceImpl(INITIAL_POSITION, ((currentPos, row, col) -> 
                (currentPos.getX() + row) == (currentPos.getY() + col))
        );
        Pair<Integer, Integer> wrongPosition = new Pair<>(INITIAL_POSITION.getX() + 2, INITIAL_POSITION.getY());
        assertThrows(IllegalStateException.class, () -> piece.move(wrongPosition.getX(), wrongPosition.getY()));
    }
}
