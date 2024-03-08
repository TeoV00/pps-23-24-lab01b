package e1.domain;

import e1.Pair;
import e1.domain.pieces.knight.KnightImpl;
import e1.domain.pieces.strategies.FixedPositionMoveStrategy;
import e1.domain.pieces.knight.Knight;

public class PiecesFactoryImpl implements PiecesFactory {
    @Override
    public Knight createKnight(Pair<Integer, Integer> position) {
        return new KnightImpl(position);
    }

    @Override
    public Piece createPawn(Pair<Integer, Integer> position) {
        return new PieceImpl(position, new FixedPositionMoveStrategy());
    }
}
