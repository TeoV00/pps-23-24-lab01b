package domain.pieces;

import e1.Pair;
import e1.domain.PiecesFactory;
import e1.domain.PiecesFactoryImpl;
import e1.domain.pieces.pawn.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    private final Pair<Integer, Integer> POSITION = new Pair<>(2,2);
    private Pawn pawn;

    @BeforeEach
    void initTest() {
        PiecesFactory factory = new PiecesFactoryImpl();
        this.pawn = factory.createPawn(POSITION);
    }

    @Test
    void testPawnCannotMove() {
        var someNotAllowedMoves = Set.of(
                new Pair<>(1,4),
                new Pair<>(2,1),
                new Pair<>(3,5),
                new Pair<>(5,5),
                new Pair<>(3,2)
        );

        someNotAllowedMoves.forEach((position) -> {
            assertAll(
                    isNotAllowedMove(position),
                    moveThrowsException(position),
                    positionDoesNotChange()
            );
        });
    }

    private Executable isNotAllowedMove(Pair<Integer, Integer> position) {
        return () -> assertFalse(this.pawn.isAllowedMove(position.getX(), position.getY()));
    }

    private Executable moveThrowsException(Pair<Integer, Integer> position) {
        return () -> assertThrows(IllegalStateException.class, () -> this.pawn.move(position.getX(), position.getY()));
    }

    private Executable positionDoesNotChange() {
        return () -> assertEquals(POSITION, this.pawn.position());
    }

}
