package games.sudoku.solver;

import java.util.List;

import org.springframework.stereotype.Component;

import games.sudoku.Cell;
import games.sudoku.Sudoku;

@Component
public class Solver {
	public int countSolutions(Sudoku sudoku) {
		Integer count = 0;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = sudoku.getMatrix().getCell(i, j);
				if (!cell.hasNumber()) {
					List<Integer> allowedNumbers = sudoku.getMatrix().getAllowedNumbers(i, j);
					for (int k = 1; k <= 9; k++) {
						if (!allowedNumbers.contains(k)) {
							continue;
						}
						
						cell.setNumber(k);
						
						if (isBoardSolved()) {
							count++;
						}
						cell.setNumber(Cell.UNSET);
					}
				}
			}
		}
		
		return count;
	}

	private boolean isBoardSolved() {
		// TODO Auto-generated method stub
		return false;
	}
}
