package e1.domain;

import e1.Pair;
import e1.domain.pieces.strategies.FixedPositionMoveStrategy;
import e1.domain.pieces.strategies.KnightMoveStrategy;

public class PiecesFactoryImpl implements PiecesFactory {
    @Override
    public Piece makeKnight(Pair<Integer, Integer> position) {
        return new PieceImpl(position, new KnightMoveStrategy());
    }

    @Override
    public Piece makePawn(Pair<Integer, Integer> position) {
        return new PieceImpl(position, new FixedPositionMoveStrategy());
    }
}
