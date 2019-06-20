package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import gamemechanics.enums.Premium;

class ScoreKeeperTest {
	GameBoard gameBoard;
	
	@BeforeEach
	void init() {
		gameBoard = new GameBoard();		
	}
	
	@Nested
	class PlacingFirstWordOnGameBoard{
		@Test
		void placingFirstWordTest() {
			List<Field> playedTiles = new LinkedList<>();
			playedTiles.add(new Field.Builder(8,8).setTile('A').build());
			playedTiles.add(new Field.Builder(8,9).setTile('W').build());
			playedTiles.add(new Field.Builder(8,10).setTile('I').build());
			playedTiles.add(new Field.Builder(8,11).setTile('Z').build());
			playedTiles.add(new Field.Builder(8,12).setTile('O').build());
			
			gameBoard.playTiles(playedTiles);
			WordCombiner wordCombiner = new WordCombiner(gameBoard);
			
			//ScoreKeeper scoreKeeper = new ScoreKeeper(wordCombiner.combineWords());
			
			List<Word> words = new ArrayList<Word>();
			List<Field> list1 = new ArrayList<Field>();				// 6
			list1.add(new Field.Builder(8,8).setTile('T').setPremium(Premium.DOUBLE_WORD).build());
			list1.add(new Field.Builder(8,9).setTile('A').build()); 
			List<Field> list2 = new ArrayList<Field>();				// 10
			list2.add(new Field.Builder(7,9).setTile('P').setPremium(Premium.DOUBLE_LETTER).build());
			list2.add(new Field.Builder(8,9).setTile('A').build());
			list2.add(new Field.Builder(9,9).setTile('N').setPremium(Premium.DOUBLE_LETTER).build());
			list2.add(new Field.Builder(10,9).setTile('N').build());
			list2.add(new Field.Builder(11,9).setTile('Y').build());
			Word word1 = new Word(list1); // 6 pts
			Word word2 = new Word(list2); // 13 pts
			List<Word> combined = new ArrayList<Word>();
			combined.add(word1);
			combined.add(word2);
			
			ScoreKeeper scoreKeeper = new ScoreKeeper(combined);
			
			assertEquals(16, scoreKeeper.calculate().getTotal());
		}
	}
}
