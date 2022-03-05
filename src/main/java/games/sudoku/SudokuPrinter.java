package games.sudoku;

public class SudokuPrinter {
	public String toString(Sudoku sudoku) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i <= 8; i++) {
			addHorizontalLine(builder, i);
			
			for (int j = 0; j <= 8; j++) {
				addVerticalLine(builder, j);
				
				if (j != 0) {
					builder.append(" ");
				}
				builder.append(sudoku.getMatrix().getCell(i, j));
			}
			addVerticalLine(builder, 9);
			
			if (i < 8) {
				builder.append("\n");
			}
		}
		builder.append("\n");
		addHorizontalLine(builder, 0);
		
		return builder.toString();
	}

	private void addHorizontalLine(StringBuilder builder, int i) {
		if (i % 3 == 0) {
			for (int k = 0; k < 9 * 2 + 7; k++) {
				builder.append("-");
			}
			builder.append("\n");
		}
	}

	private void addVerticalLine(StringBuilder builder, int j) {
		if (j % 3 == 0) {
			if (j != 0) {
				builder.append(" ");
			}
			builder.append("|");
			if (j == 0) {
				builder.append(" ");
			}
		}
	}
}
