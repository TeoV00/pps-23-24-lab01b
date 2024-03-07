package e1;

public class KnightImpl implements Knight {

    private Pair<Integer, Integer> position;

    public KnightImpl(Pair<Integer, Integer> initialKnightPosition) {
        this.position = initialKnightPosition;
    }

    @Override
    public boolean isAllowedMove(int row, int col) {
        // Below a compact way to express allowed moves for the knight
        int x = row-this.position().getX();
        int y = col-this.position().getY();
        return x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3;
    }

    @Override
    public void move(int row, int col) {
        this.position = new Pair<>(row, col);
    }

    @Override
    public Pair<Integer, Integer> position() {
        return this.position;
    }
}
