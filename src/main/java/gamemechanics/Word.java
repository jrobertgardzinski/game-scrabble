package gamemechanics;

import java.util.LinkedList;
import java.util.List;

public class Word {
	List<Field> word;
	
	public Word(List<Field> word) {
		this.word = word;
	}

	public List<Field> getWord() {
		return word;
	}

	public void setWord(List<Field> word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String letters = new String();
		for (int i=0; i<this.word.size(); i++) {
			letters += this.word.get(i).getTile().getLetter();
		}
		return "Word [word=" + letters.toUpperCase() + "]";
	}
	
	
}
