package gamemechanics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordCombiner {
	private GameBoard gameBoard;

	public WordCombiner(GameBoard gameBoard) {
		super();
		this.gameBoard = gameBoard;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public List<Word> combineWords() {
		Set<Word> result = new HashSet<Word>();
		List<Field> previouslyPlayedTiles = gameBoard.getPreviosulyPlayedTiles();
		
		// combine words
		
		return new ArrayList<Word>(result);
	}
}
