import e1.Pair;
import e1.domain.Piece;
import e1.domain.PieceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

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
            return (Math.abs(row - currentPos.getX()) == 1 || Math.abs(row - currentPos.getX()) == 0) &&
                    (Math.abs(col - currentPos.getY()) == 1 || Math.abs(col - currentPos.getY()) == 0);
        });

        assertAll(
            () -> assertTrue(piece.isAllowedMove(INITIAL_POSITION.getX() + 1, INITIAL_POSITION.getY())),
            () -> assertTrue(piece.isAllowedMove(INITIAL_POSITION.getX() - 1, INITIAL_POSITION.getY())),
            () -> assertFalse(piece.isAllowedMove(INITIAL_POSITION.getX() + 2, INITIAL_POSITION.getY() + 2))
        );
    }
}
