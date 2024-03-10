package e1.domain.gamegrid;

import e1.Pair;
import e1.domain.pieces.knight.Knight;
import e1.domain.pieces.pawn.Pawn;

import java.util.Optional;
import java.util.Set;

public class GameGridImpl implements GameGrid {

    private final Integer height;
    private final Integer width;
    private final Knight knight;
    private final Set<Pawn> pawns;

    public GameGridImpl(int height, int width, Knight knight, Set<Pawn> pawns) {
        this.height = height;
        this.width = width;
        this.knight = knight;
        this.pawns = pawns;
    }

    @Override
    public Pair<Integer, Integer> dimensions() {
        return new Pair<>(this.height, this.width);
    }

    @Override
    public boolean isInsideGrid(Pair<Integer, Integer> position) {
        return position.getX() >= 0 &&
                position.getY() >= 0 &&
                position.getX() < this.height &&
                position.getY() < this.width;
    }

    @Override
    public void moveKnight(int row, int col) {
        if (!isInsideGrid(new Pair<>(row, col))) {
            throw new IndexOutOfBoundsException();
        }
        if (this.knight.isAllowedMove(row, col)) {
            this.knight.move(row, col);
            /*Optional<Pawn> pawnToRemove = this.pawns.stream()
                    .filter(p -> p.position().equals(this.knight.position()))
                    .findFirst();
            pawnToRemove.ifPresent(this.pawns::remove);
            */
        }
    }

    @Override
    public boolean hasKnight(int row, int col) {
        return this.knight.position().equals(new Pair<>(row, col));
    }

    @Override
    public boolean hasPawn(int row, int col) {
        var position = new Pair<>(row,col);
        return this.pawns.stream().anyMatch(pawn -> pawn.position().equals(position));
    }

}
