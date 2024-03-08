package e1.domain;

import e1.Pair;

public class GameGridImpl implements GameGrid {

    private final Integer height;
    private final Integer width;

    public GameGridImpl(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public Pair<Integer, Integer> dimensions() {
        return new Pair<>(this.height, this.width);
    }

    @Override
    public boolean isInsideGrid(Pair<Integer, Integer> position) {
        return position.getX() > 0 &&
                position.getY() > 0 &&
                position.getX() < this.height &&
                position.getY() < this.width;
    }

}
