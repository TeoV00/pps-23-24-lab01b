package e1;

import org.checkerframework.checker.units.qual.K;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private Pair<Integer,Integer> pawn;
	private Knight knight;
	private final Random random = new Random();
	private int size;

    public LogicsImpl(int size){
    	this.initLogic(size, randomEmptyPosition());
    }

	public LogicsImpl(int size, Pair<Integer, Integer> initialKnightPosition){
		initLogic(size, initialKnightPosition);
	}

	private void initLogic(int size, Pair<Integer, Integer> initialKnightPosition) {
		this.size = size;
		this.knight = new KnightImpl(initialKnightPosition);
		this.pawn = this.randomEmptyPosition();
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		if (this.knight.isAllowedMove(row, col)) {
			this.knight.move(row, col);
			return this.pawn.equals(this.knight.position());
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.position().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}
