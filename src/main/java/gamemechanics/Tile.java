package gamemechanics;

import java.util.EnumSet;

import gamemechanics.enums.PolishAlphabet;


public class Tile {
	private char letter;
	private int points;

	public Tile(char letter) {
		for (PolishAlphabet tile : PolishAlphabet.values()) {
			if (tile.getLetter() == letter) {
				setLetter(tile.getLetter());
				this.points = tile.getPoints();
			}
		}
	}
	public Tile(PolishAlphabet tile) {
		setLetter(tile.getLetter());
		this.points = tile.getPoints();
	}
	public Tile(char letter, int points) {		
		setLetter(letter);
		this.points = points;
	}
	public char getLetter() {
		return letter;
	}	
	public int getPoints() {
		return points;
	}
	private void setLetter(char letter) {
		this.letter = Character.toUpperCase(letter);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + letter;
		result = prime * result + points;
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
		Tile other = (Tile) obj;
		if (letter != other.letter)
			return false;
		if (points != other.points)
			return false;
		return true;
	}
	
	
}
