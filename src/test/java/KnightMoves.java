import e1.Pair;

public enum KnightMoves {
    TOP_LEFT(-2, -1),
    TOP_RIGHT(-2, 1),
    BOTTOM_LEFT(2, -1),
    BOTTOM_RIGHT(2, 1),
    LEFT_TOP(-1, -2),
    LEFT_BOTTOM(1, -2),
    RIGHT_TOP(-1, 2),
    RIGHT_BOTTOM(1, 2);

    private int x;
    private int y;

    KnightMoves(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pair<Integer, Integer> calculateNewPosition(Pair<Integer, Integer> knightPosition) {
        return new Pair<>(knightPosition.getX() + this.getX(), knightPosition.getY() + this.getY());
    }
}
