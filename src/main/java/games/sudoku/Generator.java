package games.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class Generator {
	@Inject private Reporting reporting;

	private Sudoku sudoku;
	private boolean generated;
	private Random random;
	
	public Sudoku generateSudoku() {
		sudoku = new Sudoku();
		generated = false;
		random = new Random();
		
		do {
			reporting.reportGenerationAttempt();
			clearAllNumbers();
			fillWithRandomNumbers();
		} while (!generated);
		
		return sudoku;
	}
	
	private void clearAllNumbers() {
		sudoku.getMatrix().clear();
	}

	private void fillWithRandomNumbers() {
	    RootMatrix matrix = sudoku.getMatrix();
	    
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				reporting.reportIteration();
				
				List<Integer> allowedNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

				removeExistingNumbers(allowedNumbers, matrix.getRowNumbers(i));
				removeExistingNumbers(allowedNumbers, matrix.getColumnNumbers(j));
				removeExistingNumbers(allowedNumbers, matrix.getBoxNumbers(i, j));
				
				if (allowedNumbers.isEmpty())
					return;
			    
			    int randomAllowedNumber = allowedNumbers.get(random.nextInt(allowedNumbers.size()));
				matrix.getCell(i, j).setNumber(randomAllowedNumber);
			}
		}
		this.generated = true;
	}
	
	private void removeExistingNumbers(List<Integer> allowed, List<Integer> existing) {
		if (allowed.isEmpty())
			return;
		
		allowed.removeAll(existing);
	}
}
