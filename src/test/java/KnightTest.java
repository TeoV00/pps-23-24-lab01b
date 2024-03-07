import e1.Pair;
import e1.domain.Piece;
import e1.domain.PiecesFactory;
import e1.domain.PiecesFactoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    private final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(4,4);
    private Piece knight;

    @BeforeEach
    void initTest() {
        PiecesFactory factory = new PiecesFactoryImpl();
        this.knight = factory.makeKnight(KNIGHT_POSITION);
    }

    @Test
    void testKnightPosition() {
        Assertions.assertEquals(KNIGHT_POSITION, this.knight.position());
    }

    @Test
    void testKnightAllowedMove() {
        Pair<Integer, Integer> newPosition = KnightMoves.BOTTOM_LEFT.calculateNewPosition(this.knight.position());
        assertAll(
            () -> assertTrue(this.knight.isAllowedMove(newPosition.getX(), newPosition.getY())),
            () -> {
                this.knight.move(newPosition.getX(), newPosition.getY());
                assertEquals(newPosition, this.knight.position());
            }
        );
    }

}
