package gamemechanics;

import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import gamemechanics.enums.Premium;

public class Score {
	private int total;
	private List<Word> combinedWords;
	private List<Integer> scorePerWord;
	
	public Score() {
	}

	public List<Word> getCombinedWords() {
		return combinedWords;
	}

	public int getTotal() {
		return total;
	}

	public List<Integer> getScorePerWord() {
		return scorePerWord;
	}

	public void calculate(List<Word> combinedWords) {	
		this.combinedWords = combinedWords;
		this.scorePerWord = calculateScorePerWord(this.combinedWords);
		this.total = this.scorePerWord.stream().reduce(0,  (result, scorePerWord) -> result += scorePerWord);
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
