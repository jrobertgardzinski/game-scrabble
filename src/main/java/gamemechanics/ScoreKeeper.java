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
		List<Integer> scores = calculateScorePerWord(this.combinedWords);
		
		return new Score(this.combinedWords, scores);
	}
	
	private List<Integer> calculateScorePerWord(List<Word> combinedWords) {
		return combinedWords
				.stream()
				.map(Word::getWord)
				.collect(() -> new ArrayList<Integer>(),
						(result, fields) -> {
							int[] res = fields.stream().collect(
								() -> new int [] {0, 1}, 
								(points, field) -> {
									Premium fieldPremium = field.getPremium();
									int pointsToAdd = field.getTile().getPoints();
									try {
										if (fieldPremium.equals(Premium.DOUBLE_LETTER)) {
											pointsToAdd *= 2;
										}
										else if (fieldPremium.equals(Premium.TRIPLE_LETTER)) {
											pointsToAdd *= 3;
										}
										else if (fieldPremium.equals(Premium.DOUBLE_WORD)) {
											points[1] *= 2;
										}
										else if (fieldPremium.equals(Premium.TRIPLE_WORD)) {
											points[1] *= 3;
										}
									}
									catch (NullPointerException ex) {
										
									}
									points[0] += pointsToAdd;
								}, 
								(c,d) -> {}
							);

							result.add(res[0]*res[1]);
						},
						(a,b) -> {});
	}
}
