import domain.pieces.KnightMoves;
import e1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest {
    private static final int KNIGHT_1_STEP = 1;
    final private int GAME_SIZE = 5;
    private Logics logics;
    private Pair<Integer, Integer> knightPosition;
    private Pair<Integer, Integer> pawnPosition;

    @BeforeEach
    void beforeEach() {
        int centerPosition = GAME_SIZE/2;
        Pair<Integer, Integer> initialKnightPosition = new Pair<>(centerPosition, centerPosition);
        this.logics = new LogicsImpl(GAME_SIZE, initialKnightPosition);
        findAndSaveKnightAndPawnPosition();
    }

    @Test
    void testKnightAndPawnInitiallyInDifferentPosition() {
        assertNotEquals(this.knightPosition, this.pawnPosition);
    }

    @Test
    void testNotPermittedOneStepMoveKnight() {
        var wrongMove = calculateNewKnightPosition(KNIGHT_1_STEP, KNIGHT_1_STEP);
        this.logics.hit(wrongMove.getX(), wrongMove.getY());
        assertFalse(this.logics.hasKnight(wrongMove.getX(), wrongMove.getY()));
    }

    @Test
    void testKnightMoveOutOfGameSize() {
        assertAll(
            () -> {
                assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(GAME_SIZE + KNIGHT_1_STEP, GAME_SIZE + KNIGHT_1_STEP));
            },
            () -> {
                assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(-KNIGHT_1_STEP, -KNIGHT_1_STEP));
            }
        );
    }

    @Test
    void testMoveKnightTopLeft() {
        var newPosition = KnightMoves.TOP_LEFT.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightTopRight() {
        var newPosition = KnightMoves.TOP_RIGHT.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightBottomLeft() {
        var newPosition = KnightMoves.BOTTOM_LEFT.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightBottomRight() {
        var newPosition = KnightMoves.BOTTOM_RIGHT.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightRightTop() {
        var newPosition = KnightMoves.RIGHT_TOP.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightRightBottom() {
        var newPosition = KnightMoves.RIGHT_BOTTOM.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightLeftTop() {
        var newPosition = KnightMoves.LEFT_TOP.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testMoveKnightLeftBottom() {
        var newPosition = KnightMoves.LEFT_BOTTOM.calculateNewPosition(this.knightPosition);
        this.logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(this.logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    private Pair<Integer, Integer> calculateNewKnightPosition(Integer rowOffset, Integer colOffset) {
        return new Pair<>(this.knightPosition.getX() + rowOffset, this.knightPosition.getY() + colOffset);
    }

    private void findAndSaveKnightAndPawnPosition() {
        for(int row = 0; row < GAME_SIZE; row ++) {
            for(int col = 0; col < GAME_SIZE; col++) {
                if (this.logics.hasKnight(row, col)) {
                    this.knightPosition = new Pair<>(row,col);
                }
                if (this.logics.hasPawn(row, col)) {
                    this.pawnPosition = new Pair<>(row,col);
                }
            }
        }
    }
}