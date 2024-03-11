package e1.domain.pieces;

import e1.Pair;
import e1.domain.pieces.knight.Knight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest extends PieceTest {
    private final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(4,4);
    PiecesFactory factory = new PiecesFactoryImpl();

    @Test
    void testKnightPosition() {
        var knight = this.makeNewKnight();
        Assertions.assertEquals(KNIGHT_POSITION, knight.position());
    }

    @Test
    void testAllowedKnightMoves() {
        assertAll(
                knightAllowedToMove(KnightMoves.TOP_LEFT),
                knightAllowedToMove(KnightMoves.TOP_RIGHT),
                knightAllowedToMove(KnightMoves.BOTTOM_LEFT),
                knightAllowedToMove(KnightMoves.BOTTOM_RIGHT),
                knightAllowedToMove(KnightMoves.LEFT_BOTTOM),
                knightAllowedToMove(KnightMoves.LEFT_TOP),
                knightAllowedToMove(KnightMoves.RIGHT_BOTTOM),
                knightAllowedToMove(KnightMoves.RIGHT_TOP)
        );
    }

    @Test
    void testMoveKnightToAllowedPosition() {
        assertAll(
                allowedMoveNotThrowsException(KnightMoves.TOP_LEFT),
                allowedMoveNotThrowsException(KnightMoves.TOP_RIGHT),
                allowedMoveNotThrowsException(KnightMoves.BOTTOM_LEFT),
                allowedMoveNotThrowsException(KnightMoves.BOTTOM_RIGHT),
                allowedMoveNotThrowsException(KnightMoves.LEFT_BOTTOM),
                allowedMoveNotThrowsException(KnightMoves.LEFT_TOP),
                allowedMoveNotThrowsException(KnightMoves.RIGHT_BOTTOM),
                allowedMoveNotThrowsException(KnightMoves.RIGHT_TOP)
        );
    }

    private Executable allowedMoveNotThrowsException(KnightMoves knightMove) {
        var knight = this.makeNewKnight();
        var newPosition = calculateNewKnigthPosition(knight.position(),knightMove);
        return () -> assertDoesNotThrow(() -> knight.move(newPosition.getX(), newPosition.getY()));
    }

    private Executable knightAllowedToMove(KnightMoves knightMove) {
        var knight = this.makeNewKnight();
        var newPosition = calculateNewKnigthPosition(knight.position(), knightMove);
        return () -> assertTrue(knight.isAllowedMove(newPosition.getX(), newPosition.getY()));
    }

    private Pair<Integer, Integer> calculateNewKnigthPosition(Pair<Integer, Integer> currentPosition,KnightMoves knightMoves) {
        return knightMoves.calculateNewPosition(currentPosition);
    }

    private Knight makeNewKnight() {
        return factory.createKnight(KNIGHT_POSITION);
    }

}
