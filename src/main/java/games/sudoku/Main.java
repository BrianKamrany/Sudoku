package games.sudoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

		SudokuGenerator generator = context.getBean(SudokuGenerator.class);
		Sudoku sudoku = generator.generateSudoku();
		sudoku.hideNumbers(10);

		printReporting(context);
		System.out.print(sudoku);
	}

	@SuppressWarnings("unused")
	private static void printReporting(ConfigurableApplicationContext context) {
		Reporting reporting = context.getBean(Reporting.class);
		System.out.println("Attempts: " + reporting.getGenerationAttempts());
		System.out.println("Iterations: " + reporting.getIterations());
		System.out.println();
	}
}
