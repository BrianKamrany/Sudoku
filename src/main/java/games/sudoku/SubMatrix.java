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
	
	/*public Cell getCellAbsolute(int i, int j) {
		return cells[i][j];
	}*/
	
	public List<Integer> getRowNumbers(int i) {
		ArrayList<Integer> rowNumbers = new ArrayList<>();
		for (int j = 0; j < 3; j++) {
			Cell cell = cells[i][j];
			rowNumbers.add(cell.getNumber());
		}
		return rowNumbers;
	}
	
	public List<Integer> getColumnNumbers(int j) {
		ArrayList<Integer> columnNumbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Cell cell = cells[i][j];
			columnNumbers.add(cell.getNumber());
		}
		return columnNumbers;
	}

	public List<Integer> getBoxNumbers() {
		ArrayList<Integer> boxNumbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cell cell = cells[i][j];
				boxNumbers.add(cell.getNumber());
			}
		}
		return boxNumbers;
	}
	
	public void clear() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cell cell = cells[i][j];
				cell.clear();
			}
		}
	}
}
