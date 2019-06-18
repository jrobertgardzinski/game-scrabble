package gamemechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreKeeper {
	private WordCombiner wordCombiner;

	public ScoreKeeper(WordCombiner wordCombiner) {
		super();
		this.wordCombiner = wordCombiner;
	}
	
	public Score calculate() {
		List<Word> combinedWords; 
		wordCombiner.combineWords();
		
		List<Integer> scorePerWord = calculateScorePerWord(combinedWords);
		
		return ;
	}
	
	private List<Integer> calculateScorePerWord(List<Word> combinedWords) {
		return combinedWords
				.stream()
				.map(Word::getWord)
				.flatMap(List::stream)
				.collect(
						0,
						Field::getTile,
						Integer::sum
				);
	}
}
