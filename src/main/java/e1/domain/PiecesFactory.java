package e1.domain;

import e1.Pair;
import e1.domain.pieces.knight.Knight;
import e1.domain.pieces.pawn.Pawn;

public interface PiecesFactory {

    /**
     * @param position
     * @return knight piece at given position
     */
    Knight createKnight(Pair<Integer, Integer> position);

    /**
     * @param position
     * @return pawn piece at given position
     */
    Pawn createPawn(Pair<Integer, Integer> position);
}
