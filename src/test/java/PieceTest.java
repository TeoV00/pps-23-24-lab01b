import e1.Pair;
import e1.domain.Piece;
import e1.domain.PieceImpl;
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
        Piece piece = new PieceImpl(INITIAL_POSITION, (currentPos , row, col) -> {
            return (Math.abs(row - currentPos.getX()) == ONE_STEP || Math.abs(row - currentPos.getX()) == NO_STEP) &&
                    (Math.abs(col - currentPos.getY()) == ONE_STEP || Math.abs(col - currentPos.getY()) == NO_STEP);
        });

        var oneStepDiagonalMove = new Pair<>(INITIAL_POSITION.getX() + ONE_STEP, INITIAL_POSITION.getY() + ONE_STEP);
        var oneStepLeftMove = new Pair<>(INITIAL_POSITION.getX() - ONE_STEP, INITIAL_POSITION.getY());
        var wrongLeftMove = new Pair<>(INITIAL_POSITION.getX() + 2, INITIAL_POSITION.getY());
        assertAll(
            () -> assertTrue(piece.isAllowedMove(oneStepDiagonalMove.getX(), oneStepDiagonalMove.getY())),
            () -> assertTrue(piece.isAllowedMove(oneStepLeftMove.getX(), oneStepLeftMove.getY())),
            () -> assertFalse(piece.isAllowedMove(wrongLeftMove.getX(), wrongLeftMove.getY()))
        );
    }

    @Test
    void testNotAllowedMove() {
        Piece piece = new PieceImpl(INITIAL_POSITION, ((currentPos, row, col) -> {
            return (currentPos.getX() + row) == (currentPos.getY() + col);
        }));
        Pair<Integer, Integer> wrongPosition = new Pair<>(INITIAL_POSITION.getX() + 2, INITIAL_POSITION.getY());
        assertThrows(IllegalStateException.class, () -> piece.move(wrongPosition.getX(), wrongPosition.getY()));

    }
}
