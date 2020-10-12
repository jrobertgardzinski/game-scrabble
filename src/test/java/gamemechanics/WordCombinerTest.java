package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WordCombinerTest {
	GameBoard gameBoard;
	List<Field> tilesToPlay;
	Field[][] tilesOnBoard;
			
	@BeforeEach
	void initializePlayedTiles() {
		gameBoard = new GameBoard();
		
		tilesToPlay = new LinkedList<Field>();
		
		tilesOnBoard = new Field [15][15];
		
		// Given tiles will produce words:
		// AWIZO
		// ZDRÓW
		// BÓL
		tilesOnBoard[7][7] = new Field.Builder(8,8).setTile('A').build();
		tilesOnBoard[7][8] = new Field.Builder(8,9).setTile('W').build();
		tilesOnBoard[7][9] = new Field.Builder(8,10).setTile('I').build();
		tilesOnBoard[7][10] = new Field.Builder(8,11).setTile('Z').build();
		tilesOnBoard[7][11] = new Field.Builder(8,12).setTile('O').build();
		tilesOnBoard[8][10] = new Field.Builder(9,11).setTile('D').build();
		tilesOnBoard[9][10] = new Field.Builder(10,11).setTile('R').build();
		tilesOnBoard[10][10] = new Field.Builder(11,11).setTile('Ó').build();
		tilesOnBoard[11][10] = new Field.Builder(12,11).setTile('W').build();
		tilesOnBoard[10][9] = new Field.Builder(11,10).setTile('B').build();
		tilesOnBoard[10][11] = new Field.Builder(11,12).setTile('L').build();
		
		gameBoard.setTilesArrangement(tilesOnBoard);
	}

	@Test
	void shouldCombineFourWords() {
		tilesToPlay.add(new Field.Builder(7,10).setTile('N').build());
		tilesToPlay.add(new Field.Builder(7,11).setTile('U').build());
		tilesToPlay.add(new Field.Builder(7,12).setTile('T').build());
		tilesToPlay.add(new Field.Builder(7,13).setTile('A').build());
		
		gameBoard.playTiles(tilesToPlay);
		
		WordCombiner wordCombiner = new WordCombiner(gameBoard);
		List<Word> combinedWords = wordCombiner.combineWords();
		Word dd = new Word(tilesToPlay);
		
		assertAll(() -> {
			assertTrue(combinedWords.stream().map(Word::toString).anyMatch(word -> word.contains("NI")));
			assertTrue(combinedWords.stream().map(Word::toString).anyMatch(word -> word.contains("UZDRÓW")));
			assertTrue(combinedWords.stream().map(Word::toString).anyMatch(word -> word.contains("TO")));
			assertTrue(combinedWords.stream().map(Word::toString).anyMatch(word -> word.contains("NUTA")));
		});
	}

	@Test
	void shouldCombinePrefixAndSuffix() {
		tilesToPlay.add(new Field.Builder(6,11).setTile('P').build());
		tilesToPlay.add(new Field.Builder(7,11).setTile('O').build());
		tilesToPlay.add(new Field.Builder(13,11).setTile('M').build());
		tilesToPlay.add(new Field.Builder(14,11).setTile('Y').build());
		
		gameBoard.playTiles(tilesToPlay);
		
		WordCombiner wordCombiner = new WordCombiner(gameBoard);
		List<Word> combinedWords = wordCombiner.combineWords();
		Word dd = new Word(tilesToPlay);
		
		assertTrue(combinedWords.stream().map(Word::toString).anyMatch(word -> word.contains("POZDRÓWMY")));		
	}

}
