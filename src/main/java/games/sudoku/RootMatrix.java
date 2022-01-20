package games.sudoku;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RootMatrix {
	private NestedMatrix[][] nestedMatrixes;
	
	public RootMatrix() {
		this.nestedMatrixes = new NestedMatrix[3][3];
		clear();
	}
	
	public void clear() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.nestedMatrixes[i][j] = new NestedMatrix();
			}
		}
	}
	
	public Cell getCell(int i, int j) {
		int iNested = toNestedIndex(i);
		int jNested = toNestedIndex(j);
		NestedMatrix nestedMatrix = nestedMatrixes[iNested][jNested];
		
		int iRelative = toRelativeIndex(i);
		int jRelative = toRelativeIndex(j);
		Cell cell = nestedMatrix.getCell(iRelative, jRelative);
		return cell;
	}

	private int toNestedIndex(int i) {
		return i / 3;
	}

	private int toRelativeIndex(int i) {
		return i % 3;
	}

	public List<Integer> getRowNumbers(int i) {
		int iNested = toNestedIndex(i);
		int iRelative = toRelativeIndex(i);
		
		ArrayList<Integer> rowNumbers = new ArrayList<>();
		for (int j = 0; j < 3; j++) {
			NestedMatrix nestedMatrix = nestedMatrixes[iNested][j];
			rowNumbers.addAll(nestedMatrix.getRowNumbers(iRelative));
		}
		return rowNumbers;
	}

	public List<Integer> getColumnNumbers(int j) {
		int jNested = toNestedIndex(j);
		int jRelative = toRelativeIndex(j);
		
		ArrayList<Integer> columnNumbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			NestedMatrix nestedMatrix = nestedMatrixes[i][jNested];
			columnNumbers.addAll(nestedMatrix.getColumnNumbers(jRelative));
		}
		return columnNumbers;
	}

	public List<Integer> getBoxNumbers(int i, int j) {
		int iNested = toNestedIndex(i);
		int jNested = toNestedIndex(j);
		NestedMatrix nestedMatrix = nestedMatrixes[iNested][jNested];
		List<Integer> boxNumbers = nestedMatrix.getBoxNumbers();
		return boxNumbers;
	}

	public NestedMatrix getNestedMatrix(int iNested, int jNested) {
		return nestedMatrixes[iNested][jNested];
	}
}
