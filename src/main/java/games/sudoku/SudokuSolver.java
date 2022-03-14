package games.sudoku;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SudokuSolver {
	private int solutions;

	public boolean isUnique(Sudoku sudoku) {
		return solve(sudoku) == 1;
	}

	private int solve(Sudoku sudoku) {
		this.solutions = 0;
		countSolutions(sudoku);
		return solutions;
	}

	private void countSolutions(Sudoku sudoku) {
		Matrix matrix = sudoku.getMatrix();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = matrix.getCell(i, j);
				
				if (!cell.hasNumber()) {
					List<Integer> numbers = matrix.getValidNumbers(i, j);
					
					for (Integer number : numbers) {
						cell.setNumber(number);
						
						countSolutions(sudoku);
						
						if (matrix.isSolved()) {
							this.solutions++;
						}
						
						cell.clear();
					}
					
					return;
				}
			}
		}
	}
}
