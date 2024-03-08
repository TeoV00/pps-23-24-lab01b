package e1;

import e1.domain.*;
import e1.domain.pieces.pawn.Pawn;

import java.util.*;

public class LogicsImpl implements Logics {

	private final Random random = new Random();
	private GameGrid gameGrid;

    public LogicsImpl(int size) {
    	this.initLogic(size, randomEmptyPosition(size));
    }

	public LogicsImpl(int size, Pair<Integer, Integer> initialKnightPosition){
		initLogic(size, initialKnightPosition);
	}

	private void initLogic(int size, Pair<Integer, Integer> initialKnightPosition) {
		PiecesFactory factory = new PiecesFactoryImpl();
		Pawn pawn = factory.createPawn(randomEmptyPosition(size));
		this.gameGrid = new GameGridImpl(size, size, factory.createKnight(initialKnightPosition), Set.of(pawn));
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(int size){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return /*this.pawn!=null && this.pawn.position().equals(pos) ? randomEmptyPosition() : */ pos;
    }

	@Override
	public boolean hit(int row, int col) {
		this.gameGrid.moveKnight(row, col);
		return this.gameGrid.hasPawn(row, col);
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.gameGrid.hasKnight(row, col);
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.gameGrid.hasPawn(row, col);
	}
}
