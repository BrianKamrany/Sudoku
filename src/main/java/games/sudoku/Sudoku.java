package games.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import lombok.Data;

@Data
public class Sudoku {
	private Matrix matrix;

	public Sudoku() {
		this.matrix = new Matrix();
	}
	
	public boolean isGenerated() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = matrix.getCell(i, j);
				if (!cell.hasNumber())
					return false;
			}
		}
		return true;
	}

	public void clear() {
		matrix.clear();
	}

	public void hideNumbers(int count) {
	    ArrayList<Integer> removedIndexes = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int index = random.nextInt(81);
			while (removedIndexes.contains(Integer.valueOf(index))) {
				index = random.nextInt(81);
			}
			
			Cell cell = matrix.getCell(index / 9, index % 9);
			cell.clear();
			removedIndexes.add(index);
		}
	}

	public static Sudoku fromFile(String filepath) throws IOException {
		Sudoku sudoku = new Sudoku();
		
		try (Scanner scanner = new Scanner(new File(filepath))) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int nextInt = scanner.nextInt();
					sudoku.getMatrix().getCell(i, j).setNumber(nextInt);
				}
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
