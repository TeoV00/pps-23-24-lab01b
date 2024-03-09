package e1.domain.pieces;

import e1.Pair;
import e1.domain.pieces.knight.KnightImpl;
import e1.domain.pieces.pawn.Pawn;
import e1.domain.pieces.pawn.PawnImpl;
import e1.domain.pieces.knight.Knight;

public class PiecesFactoryImpl implements PiecesFactory {
    @Override
    public Knight createKnight(Pair<Integer, Integer> position) {
        return new KnightImpl(position);
    }

    @Override
    public Pawn createPawn(Pair<Integer, Integer> position) {
        return new PawnImpl(position);
    }
}
