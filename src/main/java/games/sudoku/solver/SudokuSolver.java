package games.sudoku.solver;

import java.util.List;

import org.springframework.stereotype.Component;

import games.sudoku.Cell;
import games.sudoku.Matrix;
import games.sudoku.SubMatrix;
import games.sudoku.Sudoku;

@Component
public class SudokuSolver {
	private int count = 0;

	public int countSolutions(Sudoku sudoku) {
		countSolutionsRecursively(sudoku);
		return count;
	}

	public void countSolutionsRecursively(Sudoku sudoku) {
		/*if (count == 2)
			return;*/
		
        outerloop:
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = sudoku.getMatrix().getCell(i, j);
				if (!cell.hasNumber()) {
					for (int k = 1; k <= 9; k++) {
						if (!isLegal(sudoku, k, i, j))
							continue;
						
						cell.setNumber(k);
						countSolutionsRecursively(sudoku);
						//System.out.println(sudoku);
						
						if (isBoardSolved(sudoku)) {
							System.out.println(sudoku);
							count++;
						}
						cell.clear();
					}
					break outerloop;
				}
			}
		}
	}

	private boolean isLegal(Sudoku sudoku, int k, int i, int j) {
		List<Integer> allowedNumbers = sudoku.getMatrix().getAllowedNumbers(i, j);
		return allowedNumbers.contains(k);
	}

	private boolean isBoardSolved(Sudoku sudoku) {
		for (int i = 0; i < 9; i++) {
			if (!sudoku.getMatrix().getRowNumbers(i).containsAll(Matrix.ALLOWED_NUMBERS))
				return false;
		}
		
		for (int j = 0; j < 9; j++) {
			if (!sudoku.getMatrix().getColumnNumbers(j).containsAll(Matrix.ALLOWED_NUMBERS))
				return false;
		}
		
		SubMatrix[][] matrixes = sudoku.getMatrix().getMatrixes();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				SubMatrix sub = matrixes[i][j];
				if (!sub.isSolved())
					return false;
			}
		}
		return true;
	}
}
