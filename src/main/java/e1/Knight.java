package e1;

public interface Knight {

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
