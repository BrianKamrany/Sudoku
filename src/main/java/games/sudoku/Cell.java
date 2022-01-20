package games.sudoku;

import lombok.Data;

@Data
public class Cell {
	public static final int UNSET = Integer.valueOf(0);
	
	private Integer number = UNSET;
	
	public boolean hasNumber() {
		return number != UNSET;
	}

	@Override
	public String toString() {
		return number != UNSET ? String.valueOf(number) : " ";
	}
}
