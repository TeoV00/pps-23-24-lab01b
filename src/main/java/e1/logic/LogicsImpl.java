package e1.logic;

import e1.Pair;
import e1.domain.gamegrid.GameGrid;
import e1.domain.gamegrid.GridFactory;
import e1.domain.gamegrid.GridFactoryImpl;

public class LogicsImpl implements Logics {

	private final GameGrid gameGrid;

    public LogicsImpl(int size) {
		GridFactory gridFactory = new GridFactoryImpl();
		this.gameGrid = gridFactory.makeGameGridRandomKnightWithPawns(size, size, 1);
    }

	public LogicsImpl(int size, Pair<Integer, Integer> initialKnightPosition){
		GridFactory gridFactory = new GridFactoryImpl();
		this.gameGrid = gridFactory.makeGameGridInitialPositionKnightWithPawns(size, size, 1, initialKnightPosition);
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
