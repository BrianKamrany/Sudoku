package games.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class SudokuGenerator {
	@Inject private Reporting reporting;
	@Inject private SudokuSolver solver;

	private Sudoku sudoku;
	
	public Sudoku generateSudoku() {
		reporting.clear();
		this.sudoku = new Sudoku();
		
		boolean uniqueSolution = false;
		while (!uniqueSolution) {
			sudoku.getMatrix().clear();
			while (!sudoku.getMatrix().isFilled()) {
				sudoku.getMatrix().clear();
				tryToFillWithRandomNumbers();
				reporting.reportGenerationAttempt();
			}
			hideNumbers(30);
			uniqueSolution = solver.isUnique(sudoku);
		}
		
		return sudoku;
	}
	
	/* Usually does not succeed. */
	private void tryToFillWithRandomNumbers() {
		Random random = new Random();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				reporting.reportIteration();
				
				List<Integer> numbers = sudoku.getMatrix().getValidNumbers(i, j);
				if (numbers.isEmpty())
					return;
			    
			    int number = numbers.get(random.nextInt(numbers.size()));
				Cell cell = sudoku.getMatrix().getCell(i, j);
				cell.setNumber(number);
			}
		}
	}

	private void hideNumbers(int count) {
		Set<Integer> indexes = new HashSet<>();
		Random random = new Random();
		while (indexes.size() < count) {
			indexes.add(random.nextInt(81));
		}
		
		for (Integer index : indexes) {
			Cell cell = sudoku.getMatrix().getCell(index);
			cell.clear();
		}
	}
}
