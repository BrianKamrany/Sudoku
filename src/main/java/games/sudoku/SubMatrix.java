package games.sudoku;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SubMatrix {
	private Cell[][] cells;
	
	public SubMatrix() {
		this.cells = new Cell[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.cells[i][j] = new Cell();
			}
		}
	}
	
	public Cell getCell(int i, int j) {
		return cells[i][j];
	}
	
	public List<Integer> getRowNumbers(int row) {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int j = 0; j < 3; j++) {
			Cell cell = cells[row][j];
			numbers.add(cell.getNumber());
		}
		return numbers;
	}
	
	public List<Integer> getColumnNumbers(int column) {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Cell cell = cells[i][column];
			numbers.add(cell.getNumber());
		}
		return numbers;
	}

	public List<Integer> getBoxNumbers() {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cell cell = cells[i][j];
				numbers.add(cell.getNumber());
			}
		}
		return numbers;
	}
	
	public void clear() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cell cell = cells[i][j];
				cell.clear();
			}
		}
	}

	public boolean isSolved() {
		return getBoxNumbers().containsAll(Matrix.ALLOWED_NUMBERS);
	}
}
