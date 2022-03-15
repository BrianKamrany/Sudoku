package games.sudoku;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

		SudokuGenerator generator = context.getBean(SudokuGenerator.class);
		Sudoku sudoku = generator.generateSudoku();

		sudoku.writeToFile();
		System.out.print(sudoku);
		//printReporting(context);
	}

	@SuppressWarnings("unused")
	private static void printReporting(ConfigurableApplicationContext context) {
		Reporting reporting = context.getBean(Reporting.class);
		System.out.println();
		System.out.println();
		System.out.println("Attempts: " + reporting.getGenerationAttempts());
		System.out.println("Iterations: " + reporting.getIterations());
	}
}
