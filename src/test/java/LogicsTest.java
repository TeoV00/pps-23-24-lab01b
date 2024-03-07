import e1.Logics;
import e1.LogicsImpl;
import e1.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest {
    private static final int KNIGHT_2_STEP = 2;
    private static final int KNIGHT_1_STEP = 1;
    final private int GAME_SIZE = 5;
    private Logics logics;

    private Pair<Integer, Integer> knightPosition;
    private Pair<Integer, Integer> pawnPosition;


    @BeforeEach
    void beforeEach() {
        Pair<Integer, Integer> initialKnightPosition = new Pair<>(GAME_SIZE/2, GAME_SIZE/2);
        this.logics = new LogicsImpl(GAME_SIZE, initialKnightPosition);
        findAndSaveKnightAndPawnPosition();
    }

    @Test
    void testKnightAndPawnInitiallyInDifferentPosition() {
        assertNotEquals(this.knightPosition, this.pawnPosition);
    }

    @Test
    void testNotPermittedOneStepMoveKnight() {
        int newRowPosition = calculateNewValidCoordinate(this.knightPosition.getX(), KNIGHT_1_STEP);
        int newColPosition = calculateNewValidCoordinate(this.knightPosition.getY(), KNIGHT_1_STEP);
        this.logics.hit(newRowPosition, newColPosition);
        assertFalse(this.logics.hasKnight(newRowPosition, newColPosition));
    }

    @Test
    void testMoveKnightVerticallyToAllowedPosition() {

        int newRowPosition = calculateNewValidCoordinate(this.knightPosition.getX(), KNIGHT_2_STEP);
        int newColPosition = calculateNewValidCoordinate(this.knightPosition.getY(), KNIGHT_1_STEP);
        this.logics.hit(newRowPosition, newColPosition);
        assertTrue(this.logics.hasKnight(newRowPosition, newColPosition));
    }

    @Test
    void testMoveKnightHorizontallyToAllowedPosition() {
        int newRowPosition = calculateNewValidCoordinate(this.knightPosition.getX(), KNIGHT_1_STEP);
        int newColPosition = calculateNewValidCoordinate(this.knightPosition.getY(), KNIGHT_2_STEP);
        this.logics.hit(newRowPosition, newColPosition);
        assertTrue(this.logics.hasKnight(newRowPosition, newColPosition));
    }

    private int calculateNewValidCoordinate(int current, int stepSize) {
        return current + stepSize < GAME_SIZE ? current + stepSize : current - stepSize;
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