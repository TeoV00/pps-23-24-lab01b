package e1.domain;

public interface GridFactory {

    /**
     *
     * @param height vertical size of game grid
     * @param width horizontal size of game grid
     * @return game grid with no pawns and a knight in random position
     */
    GameGrid makeGameGridRandomKnightWithoutPawns(int height, int width);

    /**
     *
     * @param height vertical size of game grid
     * @param width horizontal size of game grid
     * @param pawnsAmount amount of pawns in a random position
     * @return game grid with <code>pawnsAmount</code> pawns and a knight in random position
     */
    GameGrid makeGameGridRandomKnightWithPawns(int height, int width, int pawnsAmount);
}
