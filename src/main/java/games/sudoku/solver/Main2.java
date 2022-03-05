package games.sudoku.solver;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import games.sudoku.Sudoku;

@SpringBootApplication
public class Main2 {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Main2.class, args);

		Sudoku sudoku = Sudoku.fromFile("one_solution.txt");
		//Sudoku sudoku = Sudoku.fromFile("two_solutions.txt");
		System.out.println(sudoku);

		Solver solver = context.getBean(Solver.class);
		System.out.println(solver.countSolutions(sudoku));
	}
}
