package games.sudoku;

import lombok.Data;

@Data
public class Cell {
	public static final int UNSET = Integer.valueOf(0);
	
	private Integer number;

	public Cell() {
		this.number = UNSET;
	}
	
	public boolean hasNumber() {
		return number != UNSET;
	}

	public void clear() {
		this.number = UNSET;
	}

	@Override
	public String toString() {
		return number != UNSET ? String.valueOf(number) : " ";
	}
}
