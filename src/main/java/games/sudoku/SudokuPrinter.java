package games.sudoku;

public class SudokuPrinter {
	public String toString(Sudoku sudoku) {
		return toZeroString(sudoku).replaceAll("0", " ");
	}
	
	public String toZeroString(Sudoku sudoku) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				addHorizontalLine(builder);
				builder.append("\n");
			}
			
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					addVerticalLine(builder);
					builder.append(" ");
				}
				
				builder.append(sudoku.getMatrix().getCell(i, j));
				builder.append(" ");
			}
			
			addVerticalLine(builder);
			builder.append("\n");
		}
		addHorizontalLine(builder);
		
		return builder.toString();
	}

	private void addHorizontalLine(StringBuilder builder) {
		for (int k = 0; k < 9 * 2 + 7; k++) {
			builder.append("-");
		}
	}

	private void addVerticalLine(StringBuilder builder) {
		builder.append("|");
	}
}
