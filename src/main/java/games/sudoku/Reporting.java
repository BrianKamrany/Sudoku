package games.sudoku;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Reporting {
	private int generationAttempts;
	private int iterations;
	
	public void reportGenerationAttempt() {
		this.generationAttempts++;
	}
	
	public void reportIteration() {
		this.iterations++;
	}
	
	public void clear() {
		this.generationAttempts = 0;
		this.iterations = 0;
	}
}
