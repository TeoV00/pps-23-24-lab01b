package e1;

import e1.logic.Logics;
import e1.logic.LogicsImpl;
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