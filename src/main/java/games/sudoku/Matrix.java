package games.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Matrix {
	public static final List<Integer> ALLOWED_NUMBERS = Collections.unmodifiableList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	
	private SubMatrix[][] matrixes;
	
	public Matrix() {
		this.matrixes = new SubMatrix[3][3];
		for (int i = 0; i < 9; i++) {
			this.matrixes[toSubIndex(i)][toRelativeIndex(i)] = new SubMatrix();
		}
	}
	
	public void clear() {
		for (int i = 0; i < 9; i++) {
			SubMatrix sub = matrixes[toSubIndex(i)][toRelativeIndex(i)];
			sub.clear();
		}
	}
	
	public boolean isFilled() {
		for (int i = 0; i < 81; i++) {
			Cell cell = getCell(i);
			if (!cell.hasNumber())
				return false;
		}
		return true;
	}

	public List<Integer> getValidNumbers(int i, int j) {
		List<Integer> numbers = new ArrayList<>(ALLOWED_NUMBERS);
		removeNumbers(numbers, getRowNumbers(i));
		removeNumbers(numbers, getColumnNumbers(j));
		removeNumbers(numbers, getBoxNumbers(i, j));
		return numbers;
	}

	public List<Integer> getRowNumbers(int row) {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int j = 0; j < 3; j++) {
			SubMatrix sub = matrixes[toSubIndex(row)][j];
			numbers.addAll(sub.getRowNumbers(toRelativeIndex(row)));
		}
		return numbers;
	}

	public List<Integer> getColumnNumbers(int column) {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			SubMatrix sub = matrixes[i][toSubIndex(column)];
			numbers.addAll(sub.getColumnNumbers(toRelativeIndex(column)));
		}
		return numbers;
	}

	public List<Integer> getBoxNumbers(int row, int column) {
		SubMatrix sub = matrixes[toSubIndex(row)][toSubIndex(column)];
		return sub.getBoxNumbers();
	}
	
	public Cell getCell(int absoluteIndex) {
		int i = absoluteIndex / 9;
		int j = absoluteIndex % 9;
		return getCell(i, j);
	}
	
	public Cell getCell(int i, int j) {
		SubMatrix sub = matrixes[toSubIndex(i)][toSubIndex(j)];
		return sub.getCell(toRelativeIndex(i), toRelativeIndex(j));
	}

	public boolean isSolved() {
		for (int i = 0; i < 9; i++) {
			if (!getRowNumbers(i).containsAll(Matrix.ALLOWED_NUMBERS))
				return false;
			
			if (!getColumnNumbers(i).containsAll(Matrix.ALLOWED_NUMBERS))
				return false;
			
			SubMatrix sub = matrixes[toSubIndex(i)][toRelativeIndex(i)];
			if (!sub.isSolved())
				return false;
		}
		
		return true;
	}
	
	private void removeNumbers(List<Integer> allowed, List<Integer> numbers) {
		if (allowed.isEmpty())
			return;
		
		allowed.removeAll(numbers);
	}

	private int toSubIndex(int index) {
		return index / 3;
	}

	private int toRelativeIndex(int index) {
		return index % 3;
	}
}
