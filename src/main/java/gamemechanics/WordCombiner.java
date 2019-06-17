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
		
		// vertical words
		previouslyPlayedTiles.stream().forEach(playedTile -> {
			int x = playedTile.getCoordinates().getX();
			int y = playedTile.getCoordinates().getY();
			
			List<Field> fieldList = new ArrayList<>();
			fieldList.add(playedTile);
			int index = x-1;
			try {
				while (gameBoard.getTilesArrangement()[index][y].getTile() != null) {
					Field fieldToAdd = gameBoard.getTilesArrangement()[index][y];
					fieldList.add(0, fieldToAdd);
					index -= 1;
				}
			}
			catch (Exception ex) {
				
			}
			index = x+1;
			try {
				while (gameBoard.getTilesArrangement()[index][y].getTile() != null) {
					Field fieldToAdd = gameBoard.getTilesArrangement()[index][y];
					fieldList.add(fieldToAdd);
					index += 1;
				}
			}
			catch (Exception ex) {
				
			}
			Word combinedWord = new Word(fieldList);
			if (combinedWord.getWord().size() > 1) {
				result.add(combinedWord);				
			}
		});
		
		// horizontal words
		previouslyPlayedTiles.stream().forEach(playedTile -> {
			int x = playedTile.getCoordinates().getX();
			int y = playedTile.getCoordinates().getY();
			
			List<Field> fieldList = new ArrayList<>();
			fieldList.add(playedTile);
			int index = y-1;
			while (gameBoard.getTilesArrangement()[x][index].getTile() != null) {
				Field fieldToAdd = gameBoard.getTilesArrangement()[x][index];
				fieldList.add(0, fieldToAdd);
				index -= 1;
			}
			index = y+1;
			while (gameBoard.getTilesArrangement()[x][index].getTile() != null) {
				Field fieldToAdd = gameBoard.getTilesArrangement()[x][index];
				fieldList.add(fieldToAdd);
				index += 1;
			}
			Word combinedWord = new Word(fieldList);
			if (combinedWord.getWord().size() > 1) {
				result.add(combinedWord);				
			}
		});
		
		return new ArrayList<Word>(result);
	}
}
