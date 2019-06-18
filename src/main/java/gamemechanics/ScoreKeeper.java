package gamemechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gamemechanics.enums.Premium;

public class ScoreKeeper {
	private List<Word> combinedWords;
	
	public ScoreKeeper(List<Word> combinedWords) {
		this.combinedWords = combinedWords;
	}

	public Score calculate() {		
		int[] arr = calculateScorePerWord(this.combinedWords);
		
		return new Score(null, null);
	}
	
	private int[] calculateScorePerWord(List<Word> combinedWords) {
		return combinedWords
				.stream()
				.map(Word::getWord)
				.collect(
						() -> new int[1], 
						(result, fields) -> result[0] += fields.stream().map(field -> {
							int a = field.getTile().getPoints();
							Premium b = field.getPremium() == null ? null : field.getPremium();
							if (b != null) {
								if (b.equals(Premium.DOUBLE_LETTER)) {
									a *= 2;
								} else if (b.equals(Premium.TRIPLE_LETTER)) {
									a *= 3;
								}
							}
							return a;
						}).reduce(0, (a, b) -> a + b), 
						(c, d) -> c[0] += d[0]);
				
	}
}
