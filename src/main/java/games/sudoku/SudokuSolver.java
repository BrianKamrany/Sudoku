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
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = sudoku.getMatrix().getCell(i, j);
				
				if (!cell.hasNumber()) {
					List<Integer> numbers = sudoku.getMatrix().getValidNumbers(i, j);
					
					for (Integer number : numbers) {
						cell.setNumber(number);
						
						countSolutions(sudoku);
						
						if (sudoku.getMatrix().isSolved()) {
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
