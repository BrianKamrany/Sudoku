package games.sudoku;

import java.util.ArrayList;
import java.util.Random;

import lombok.Data;

@Data
public class Sudoku {
	private RootMatrix matrix;
	private Random random;

	public Sudoku() {
		this.matrix = new RootMatrix();
		this.random = new Random();
	}

	public void hideNumbersRandomly(int count) {
	    ArrayList<Integer> removedIndexes = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int index = random.nextInt(81);
			while (removedIndexes.contains(Integer.valueOf(index))) {
				index = random.nextInt(81);
			}
			removedIndexes.add(index);
			
			Cell cell = matrix.getCell(index / 9, index % 9);
			cell.setNumber(Cell.UNSET);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i <= 8; i++) {
			addDashes(builder, i);
			
			for (int j = 0; j <= 8; j++) {
				addPips(builder, j);
				
				if (j != 0) {
					builder.append(" ");
				}
				builder.append(matrix.getCell(i, j));
			}
			
			if (i < 8) {
				builder.append("\n");
			}
		}
		
		return builder.toString();
	}

	private void addDashes(StringBuilder builder, int i) {
		if (i % 3 == 0 && i != 0) {
			for (int k = 0; k < 9 * 2 + 4; k++) {
				builder.append("-");
			}
			builder.append("\n");
		}
	}

	private void addPips(StringBuilder builder, int j) {
		if (j % 3 == 0 && j != 0) {
			builder.append(" ");
			builder.append("|");
		}
	}
}
