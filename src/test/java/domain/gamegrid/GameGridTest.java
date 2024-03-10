package domain.gamegrid;

import domain.pieces.KnightMoves;
import e1.Pair;
import e1.domain.gamegrid.GameGrid;
import e1.domain.gamegrid.GameGridImpl;
import e1.domain.pieces.PiecesFactory;
import e1.domain.pieces.PiecesFactoryImpl;
import e1.domain.pieces.pawn.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameGridTest {

    private final int GRID_SIZE = 5;
    private final Pair<Integer, Integer> KNIGHT_POS = new Pair<>(GRID_SIZE/2, GRID_SIZE/2);
    private final Pair<Integer, Integer> PAWN_TO_EAT_POS = KnightMoves.BOTTOM_LEFT.calculateNewPosition(KNIGHT_POS);
    private GameGrid gameGrid;

    @BeforeEach
    void testGameGridCreation() {
        PiecesFactory factory = new PiecesFactoryImpl();
        Set<Pawn> pawns = new HashSet<>();
        pawns.add(uneatablePawnWithOneMove(factory));
        pawns.add(bottomLeftPawnToBeEaten(factory));
        this.gameGrid = new GameGridImpl(GRID_SIZE, GRID_SIZE, factory.createKnight(KNIGHT_POS), pawns);
    }

    private Pawn uneatablePawnWithOneMove(PiecesFactory factory) {
        return factory.createPawn(new Pair<>(KNIGHT_POS.getX() - 1, KNIGHT_POS.getY()));
    }

    private Pawn bottomLeftPawnToBeEaten(PiecesFactory factory) {
        return factory.createPawn(PAWN_TO_EAT_POS);
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

    @Test
    void testMoveKnight() {
        var anAllowedPosition = KnightMoves.TOP_LEFT.calculateNewPosition(KNIGHT_POS);
        this.gameGrid.moveKnight(anAllowedPosition.getX(), anAllowedPosition.getY());
        assertTrue(this.gameGrid.hasKnight(anAllowedPosition.getX(), anAllowedPosition.getY()));
    }

    @Test
    void testMoveKnightAndEatPawn() {
        assertAll(
                pawnInitiallyInPosition(PAWN_TO_EAT_POS),
                knightMovedToPawnPosition(PAWN_TO_EAT_POS),
                pawnEatenByKnight()
        );
    }

    private Executable pawnEatenByKnight() {
        return () -> assertTrue(gameGrid.hasPawn(PAWN_TO_EAT_POS.getX(), PAWN_TO_EAT_POS.getY()));
    }

    private Executable pawnInitiallyInPosition(Pair<Integer, Integer > position) {
        return () -> assertTrue(gameGrid.hasPawn(position.getX(), position.getY()));
    }

    private Executable knightMovedToPawnPosition(Pair<Integer, Integer> position) {
        return () -> {
            this.gameGrid.moveKnight(position.getX(), position.getY());
            assertTrue(this.gameGrid.hasKnight(position.getX(), position.getY()));
        };
    }
}
