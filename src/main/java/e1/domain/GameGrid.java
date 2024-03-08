package e1.domain;

import e1.Pair;

public interface GameGrid {

    /**
     *
     * @return dimension (H, W) of game grid
     */
    Pair<Integer, Integer> dimensions();

    boolean isInsideGrid(Pair<Integer, Integer> position);

    void moveKnight(int row, int col);

    boolean hasKnight(int row, int col);

    boolean hasPawn(int row, int col);
}
