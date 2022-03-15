package games.sudoku;

import lombok.Data;

@Data
public class Cell {
	private static final int UNSET_VALUE = 0;
	
	private Integer number;

	public Cell() {
		this.number = UNSET_VALUE;
	}
	
	public boolean hasNumber() {
		return !number.equals(UNSET_VALUE);
	}

	public void clear() {
		this.number = UNSET_VALUE;
	}

	@Override
	public String toString() {
		return hasNumber() ? String.valueOf(number) : "0";
	}
}
