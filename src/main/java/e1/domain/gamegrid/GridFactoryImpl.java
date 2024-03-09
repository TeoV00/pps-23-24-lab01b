package e1.domain.gamegrid;

import e1.Pair;
import e1.domain.pieces.PiecesFactory;
import e1.domain.pieces.PiecesFactoryImpl;
import e1.domain.pieces.pawn.Pawn;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GridFactoryImpl implements GridFactory {

    private final PiecesFactory piecesFactory = new PiecesFactoryImpl();
    private final Random random = new Random();

    @Override
    public GameGrid makeGameGridRandomKnightWithoutPawns(int height, int width) {
        return createGameGrid(height, width, 0, randomPosition(height, width));
    }

    @Override
    public GameGrid makeGameGridRandomKnightWithPawns(int height, int width, int pawnsAmount) {
        return createGameGrid(height, width, pawnsAmount, randomPosition(height, width));
    }

    @Override
    public GameGrid makeGameGridInitialPositionKnightWithPawns(int height, int width, int pawnsAmount, Pair<Integer, Integer> initialKnightPosition) {
        return createGameGrid(height, width, pawnsAmount, initialKnightPosition);
    }

    private GameGrid createGameGrid(int height, int width, int pawnsAmount, Pair<Integer, Integer> knightPosition) {
        return new GameGridImpl(height,
                                width,
                                piecesFactory.createKnight(knightPosition),
                                generateRandomPawns(pawnsAmount, knightPosition, height, width));
    }

    private Set<Pawn> generateRandomPawns(int amount, Pair<Integer, Integer> knightPosition, int height, int width) {
        return Stream.generate(() -> new Pair<>(random.nextInt(height), random.nextInt(width)))
                .filter(pawnPos -> !pawnPos.equals(knightPosition))
                .distinct()
                .limit(amount)
                .map(piecesFactory::createPawn)
                .collect(Collectors.toSet());
    }

    private final Pair<Integer,Integer> randomPosition(int height, int width){
        return new Pair<>(this.random.nextInt(height),this.random.nextInt(width));
    }
}
