package games.sudoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

		Generator generator = context.getBean(Generator.class);
		Sudoku sudoku = generator.generateSudoku();
		sudoku.hideNumbersRandomly(30);

		//printReporting(context);

		System.out.print(sudoku);
	}

	@SuppressWarnings("unused")
	private static void printReporting(ConfigurableApplicationContext context) {
		Reporting reporting = context.getBean(Reporting.class);
		System.out.println(reporting.getGenerationAttempts());
		System.out.println(reporting.getIterations());
		System.out.println();
	}
}
