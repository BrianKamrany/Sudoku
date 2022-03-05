package games.sudoku;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class SudokuGenerator {
	@Inject private Reporting reporting;

	private Sudoku sudoku;
	
	public Sudoku generateSudoku() {
		reporting.clear();
		sudoku = new Sudoku();
		
		while (!sudoku.isGenerated()) {
			sudoku.clear();
			tryToFillWithRandomNumbers();
			reporting.reportGenerationAttempt();
		}
		
		return sudoku;
	}

	private void tryToFillWithRandomNumbers() {
		Random random = new Random();
	    Matrix matrix = sudoku.getMatrix();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				reporting.reportIteration();
				
				List<Integer> allowedNumbers = matrix.getAllowedNumbers(i, j);
				if (allowedNumbers.isEmpty())
					return;
			    
			    int randomAllowedNumber = allowedNumbers.get(random.nextInt(allowedNumbers.size()));
				Cell cell = matrix.getCell(i, j);
				cell.setNumber(randomAllowedNumber);
			}
		}
	}
}
