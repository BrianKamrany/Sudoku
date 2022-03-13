package games.sudoku.solver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import games.sudoku.Sudoku;
import games.sudoku.SudokuSolver;

@SpringBootTest
@RunWith(SpringRunner.class)
class SudokuSolverTest {
	@Inject private SudokuSolver solver;
	
	@Test
	public void isUnique_OneSolution() throws Exception {
		Sudoku sudoku = Sudoku.fromFile(toTestPath("one_solution.txt"));
		assertTrue(solver.isUnique(sudoku));
	}

	@Test
	public void isUnique_TwoSolutions() throws Exception {
		Sudoku sudoku = Sudoku.fromFile(toTestPath("two_solutions.txt"));
		assertFalse(solver.isUnique(sudoku));
	}
	
	protected static String toTestPath(String filepath) {
		return "src/test/resources/" + filepath;
	}
}
