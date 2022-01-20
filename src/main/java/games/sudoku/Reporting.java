package games.sudoku;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Reporting {
	private int generationAttempts;
	private long iterations;
	
	public void reportGenerationAttempt() {
		this.generationAttempts++;
	}
	
	public void reportIteration() {
		this.iterations++;
	}
}
