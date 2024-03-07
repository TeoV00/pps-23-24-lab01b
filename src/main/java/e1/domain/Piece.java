package e1.domain;

import e1.Pair;

public interface Piece {
    /**
     *
     * @param row
     * @param col
     * @return true if position is allowed, false otherwise
     */
    boolean isAllowedMove(int row, int col);

    /**
     * Move knight to (row, col) position. In case of not allowed position, knight position is not updated.
     * @param row
     * @param col
     */
    void move(int row, int col);

    /**
     *
     * @return current knight position
     */
    Pair<Integer, Integer> position();

}
