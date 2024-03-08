import e1.Pair;
import e1.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameGridTest {

    private final int GRID_SIZE = 5;
    private GameGrid gameGrid;

    @BeforeEach
    void testGameGridCreation() {
        this.gameGrid = new GameGridImpl(GRID_SIZE, GRID_SIZE);
    }

    @Test
    void testGetGameGridDimensions() {
        assertEquals(new Pair<>(GRID_SIZE, GRID_SIZE), gameGrid.dimensions());
    }

    @Test
    void testIfPositionInsideGameGrid() {
        var outsidePosition = new Pair<>(GRID_SIZE, GRID_SIZE);
        assertFalse(this.gameGrid.isInsideGrid(outsidePosition));
    }
}
