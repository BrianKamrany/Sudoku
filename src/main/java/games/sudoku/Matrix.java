package games.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class Matrix {
	public static final List<Integer> ALLOWED_NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	
	private SubMatrix[][] matrixes;
	
	public Matrix() {
		this.matrixes = new SubMatrix[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.matrixes[i][j] = new SubMatrix();
			}
		}
	}

	public List<Integer> getAllowedNumbers(int i, int j) {
		List<Integer> allowedNumbers = new ArrayList<>(ALLOWED_NUMBERS);
		removeNumbers(allowedNumbers, getRowNumbers(i));
		removeNumbers(allowedNumbers, getColumnNumbers(j));
		removeNumbers(allowedNumbers, getBoxNumbers(i, j));
		return allowedNumbers;
	}
	
	public Cell getCell(int i, int j) {
		SubMatrix sub = matrixes[toSubIndex(i)][toSubIndex(j)];
		Cell cell = sub.getCell(toRelativeIndex(i), toRelativeIndex(j));
		return cell;
	}

	public List<Integer> getRowNumbers(int i) {
		ArrayList<Integer> rowNumbers = new ArrayList<>();
		for (int j = 0; j < 3; j++) {
			SubMatrix sub = matrixes[toSubIndex(i)][j];
			rowNumbers.addAll(sub.getRowNumbers(toRelativeIndex(i)));
		}
		return rowNumbers;
	}

	public List<Integer> getColumnNumbers(int j) {
		ArrayList<Integer> columnNumbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			SubMatrix sub = matrixes[i][toSubIndex(j)];
			columnNumbers.addAll(sub.getColumnNumbers(toRelativeIndex(j)));
		}
		return columnNumbers;
	}

	public List<Integer> getBoxNumbers(int i, int j) {
		SubMatrix sub = matrixes[toSubIndex(i)][toSubIndex(j)];
		return sub.getBoxNumbers();
	}

	/*public SubMatrix getSubMatrix(int iSub, int jSub) {
		return matrixes[iSub][jSub];
	}*/
	
	public void clear() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				SubMatrix sub = this.matrixes[i][j];
				sub.clear();
			}
		}
	}

	private int toSubIndex(int index) {
		return index / 3;
	}

	private int toRelativeIndex(int index) {
		return index % 3;
	}
	
	private void removeNumbers(List<Integer> allowed, List<Integer> numbers) {
		if (allowed.isEmpty())
			return;
		
		allowed.removeAll(numbers);
	}
}
