package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import gamemechanics.enums.Premium;

class GameBoardTest {
	GameBoard gameBoard;
	// TODO consider adding testcases defining opposed, correct behaviour
	@BeforeEach
	void init() {
		gameBoard = new GameBoard();		
	}
	
	@Nested
	class PlacingFirstWordOnGameBoard{
		@Test
		void shouldNotAcceptPlacingFirstWordNotOnTheCenterOfTheGameBoard() {
			List<Field> playedTiles = new LinkedList<>();
			playedTiles.add(new Field(1,8,'A'));
			playedTiles.add(new Field(1,9,'W'));
			playedTiles.add(new Field(1,10,'I'));
			playedTiles.add(new Field(1,11,'Z'));
			playedTiles.add(new Field(1,12,'O'));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(playedTiles); });
		}
	
		@Test
		void centerFieldShouldHasDoubleWordPremium() {
			List<Field> playedTiles = new LinkedList<>();
			playedTiles.add(new Field(8,8,'A'));
			playedTiles.add(new Field(8,9,'W'));
			playedTiles.add(new Field(8,10,'I'));
			playedTiles.add(new Field(8,11,'Z'));
			playedTiles.add(new Field(8,12,'O'));
			
			assertTrue(gameBoard.getPremiumIfExists(new Coordinates(8, 8)).equals(Premium.DOUBLE_WORD));
		}
		
		@Test
		void firstPlayedWordConsistAtLeastOfTwoTiles() {
			List<Field> playedTiles = new LinkedList<Field>();
			playedTiles.add(new Field(8,8,'A'));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(playedTiles); });
		}
	}
	
	@Nested
	class HandlingLogicOfFurtherMoves {
		List<Field> tilesToPlay;
		List<Field> tilesOnBoard;
				
		@BeforeEach
		void reinitializePlayedTiles() {
			tilesToPlay = new LinkedList<Field>();
			
			tilesOnBoard = new LinkedList<Field>();
			// Given tiles will produce words:
			// AWIZO
			// ZDRÓW
			// BÓL
			tilesOnBoard.add(new Field(8,8,'A'));
			tilesOnBoard.add(new Field(8,9,'W'));
			tilesOnBoard.add(new Field(8,10,'I'));
			tilesOnBoard.add(new Field(8,11,'Z'));
			tilesOnBoard.add(new Field(8,12,'O'));
			tilesOnBoard.add(new Field(9,11,'D'));
			tilesOnBoard.add(new Field(10,11,'R'));
			tilesOnBoard.add(new Field(11,11,'Ó'));
			tilesOnBoard.add(new Field(12,11,'W'));
			tilesOnBoard.add(new Field(11,10,'B'));
			tilesOnBoard.add(new Field(11,12,'L'));
			
			gameBoard.setTilesArrangement(tilesOnBoard);
		}
		
		@Test
		void playedTilesShouldBePlacedInOneLine() {
			tilesToPlay.add(new Field(6,11,'P'));
			tilesToPlay.add(new Field(7,11,'O'));
			tilesToPlay.add(new Field(7,12,'D'));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}

		@Test
		void playedTilesShouldNotBeSeparated() {
			tilesToPlay.add(new Field(7,5,'T'));
			// take note about space between T and other tiles
			tilesToPlay.add(new Field(7,7,'A'));
			tilesToPlay.add(new Field(7,8,'M'));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });			
		}

		@Test
		void atLeastOneTileShouldAdhereToOneAlreadyPlacedOnGameBoard() {
			tilesToPlay.add(new Field(7,6,'J'));
			tilesToPlay.add(new Field(8,6,'A'));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}
		
		@Test
		void cannotPlaceTileOnOccupiedField() {
			tilesToPlay.add(new Field(8,8,'T'));
			tilesToPlay.add(new Field(8,9,'Y'));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}
	}

	//TODO consider creation of nested Premium tests if Word Combiner
}
