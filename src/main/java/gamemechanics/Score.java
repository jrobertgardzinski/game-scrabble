package gamemechanics;

import java.util.List;

public class Score {
	private int total;
	private List<Word> combinedWords;
	private List<Integer> scorePerWord;
	
	public Score(List<Word> combinedWords, List<Integer> scorePerWord) {
		this.combinedWords = combinedWords;
		this.scorePerWord = scorePerWord;
		this.total = (int)this.scorePerWord.stream().count();
	}

	public List<Word> getCombinedWords() {
		return combinedWords;
	}

	public void setCombinedWords(List<Word> combinedWords) {
		this.combinedWords = combinedWords;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Integer> getScorePerWord() {
		return scorePerWord;
	}

	public void setScorePerWord(List<Integer> scorePerWord) {
		this.scorePerWord = scorePerWord;
	}
	
	
}
