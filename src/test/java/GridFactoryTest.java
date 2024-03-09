import e1.Pair;
import e1.domain.GameGrid;
import e1.domain.GridFactory;
import e1.domain.GridFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GridFactoryTest {

    public static final int GRID_WIDTH = 2;
    public static final int GRID_HEIGHT = 2;
    private GridFactory gridFactory;

    @BeforeEach
    void initTest() {
        this.gridFactory = new GridFactoryImpl();
    }

    @Test
    void testMakeGameGridWithoutPawnsAndRandomKnightPosition() {
        GameGrid gameGrid = this.gridFactory.makeGameGridRandomKnightWithoutPawns(GRID_HEIGHT, GRID_WIDTH);
        assertAll(
                () -> assertEquals(0, amountOfPawnsInGameGrid(gameGrid)),
                () -> assertNotEquals(Optional.empty(), findKnightPosition(gameGrid))
        );
    }

    @Test
    void testMakeGameGridWithAMountOfRandomPawnsAndOneKnight() {
        int pawnsAmount = 3;
        GameGrid gameGrid = this.gridFactory.makeGameGridRandomKnightWithPawns(GRID_HEIGHT, GRID_WIDTH, pawnsAmount);
        assertEquals(pawnsAmount, amountOfPawnsInGameGrid(gameGrid));
    }

    private Optional<Pair<Integer, Integer>> findKnightPosition(GameGrid gameGrid) {
        return generateGameGridCoordinates()
                .filter(c -> gameGrid.hasKnight(c.getX(), c.getY()))
                .findFirst();
    }

    private int amountOfPawnsInGameGrid(GameGrid gameGrid) {
        return (int) generateGameGridCoordinates()
                .filter(position -> gameGrid.hasPawn(position.getX(), position.getY()))
                .count();
    }

    private Stream<Pair<Integer, Integer>> generateGameGridCoordinates() {
        return IntStream.range(0, GRID_WIDTH)
                .boxed()
                .flatMap(col -> IntStream.range(0, GRID_HEIGHT).mapToObj(row -> new Pair<>(row,col)));
    }
}
