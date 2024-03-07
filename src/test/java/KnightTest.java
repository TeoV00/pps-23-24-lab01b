import e1.Knight;
import e1.KnightImpl;
import e1.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    private final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(4,4);
    private Knight knight;

    @BeforeEach
    void initTest() {
        this.knight = new KnightImpl(KNIGHT_POSITION);
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
