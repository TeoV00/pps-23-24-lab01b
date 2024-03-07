package e1.domain;

import e1.Pair;

public interface PiecesFactory {

    /**
     *
     * @param position
     * @return knight piece at given position
     */
    Piece makeKnight(Pair<Integer, Integer> position);

    /**
     *
     * @param position
     * @return pawn piece at given position
     */
    Piece makePawn(Pair<Integer, Integer> position);
}
