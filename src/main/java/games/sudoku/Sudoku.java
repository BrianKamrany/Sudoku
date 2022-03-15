package games.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import lombok.Data;

@Data
public class Sudoku {
	private Matrix matrix;

	public Sudoku() {
		this.matrix = new Matrix();
	}

	public static Sudoku fromFile(String filepath) throws IOException {
		Sudoku sudoku = new Sudoku();
		
		String unprocessedSudoku = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		String zeroStringSudoku = unprocessedSudoku.replaceAll("-", "").replaceAll(" \\| ", " ").replaceAll("\\|", "").replaceAll("  ", " 0");
		
		try (Scanner scanner = new Scanner(zeroStringSudoku)) {
			for (int i = 0; i < 81; i++) {
				int number = scanner.nextInt();
				sudoku.getMatrix().getCell(i).setNumber(number);
			}
		}
		
		return sudoku;
	}

	public void writeToFile() throws IOException {
		File file = new File("sudoku.txt");
		FileUtils.writeStringToFile(file, this.toString(), StandardCharsets.UTF_8);
	}

	@Override
	public String toString() {
		SudokuPrinter printer = new SudokuPrinter();
		return printer.toString(this);
	}
}
