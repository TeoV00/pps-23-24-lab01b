package e1;

import e1.domain.*;
import java.util.*;

public class LogicsImpl implements Logics {
	
	private Piece pawn;
	private Piece knight;
	private final Random random = new Random();
	private GameGrid gameGrid;

    public LogicsImpl(int size) {
    	this.initLogic(size, randomEmptyPosition());
    }

	public LogicsImpl(int size, Pair<Integer, Integer> initialKnightPosition){
		initLogic(size, initialKnightPosition);
	}

	private void initLogic(int size, Pair<Integer, Integer> initialKnightPosition) {
		PiecesFactory factory = new PiecesFactoryImpl();
		this.gameGrid = new GameGridImpl(size, size);
		this.knight = factory.createKnight(initialKnightPosition);
		this.pawn = factory.createPawn(randomEmptyPosition());

	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
		Integer height = this.gameGrid.dimensions().getX();
		Integer width = this.gameGrid.dimensions().getY();
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(height),this.random.nextInt(width));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.position().equals(pos) ? randomEmptyPosition() : pos;
    }

	@Override
	public boolean hit(int row, int col) {
		if (!this.gameGrid.isInsideGrid(new Pair<>(row, col))) {
			throw new IndexOutOfBoundsException();
		}
		if (this.knight.isAllowedMove(row, col)) {
			this.knight.move(row, col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.position().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.position().equals(new Pair<>(row,col));
	}
}
