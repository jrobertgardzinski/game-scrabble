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
			playedTiles.add(new Field.Builder(1,8).setTile('A').build());
			playedTiles.add(new Field.Builder(1,9).setTile('W').build());
			playedTiles.add(new Field.Builder(1,10).setTile('I').build());
			playedTiles.add(new Field.Builder(1,11).setTile('Z').build());
			playedTiles.add(new Field.Builder(1,12).setTile('O').build());
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(playedTiles); });
		}
	
		@Test
		void centerFieldShouldHasDoubleWordPremium() {
			List<Field> playedTiles = new LinkedList<>();
			playedTiles.add(new Field.Builder(8,8).setTile('A').build());
			playedTiles.add(new Field.Builder(8,9).setTile('W').build());
			playedTiles.add(new Field.Builder(8,10).setTile('I').build());
			playedTiles.add(new Field.Builder(8,11).setTile('Z').build());
			playedTiles.add(new Field.Builder(8,12).setTile('O').build());
			
			assertTrue(gameBoard.getPremiumIfExists(new Coordinates(8, 8)).equals(Premium.DOUBLE_WORD));
		}
		
		@Test
		void firstPlayedWordConsistAtLeastOfTwoTiles() {
			List<Field> playedTiles = new LinkedList<Field>();
			playedTiles.add(new Field.Builder(8,8).setTile('A').build());
			
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
			tilesOnBoard.add(new Field.Builder(8,8).setTile('A').build());
			tilesOnBoard.add(new Field.Builder(8,9).setTile('W').build());
			tilesOnBoard.add(new Field.Builder(8,10).setTile('I').build());
			tilesOnBoard.add(new Field.Builder(8,11).setTile('Z').build());
			tilesOnBoard.add(new Field.Builder(8,12).setTile('O').build());
			tilesOnBoard.add(new Field.Builder(9,11).setTile('D').build());
			tilesOnBoard.add(new Field.Builder(10,11).setTile('R').build());
			tilesOnBoard.add(new Field.Builder(11,11).setTile('Ó').build());
			tilesOnBoard.add(new Field.Builder(12,11).setTile('W').build());
			tilesOnBoard.add(new Field.Builder(11,10).setTile('B').build());
			tilesOnBoard.add(new Field.Builder(11,12).setTile('L').build());
			
			gameBoard.setTilesArrangement(tilesOnBoard);
		}
		
		@Test
		void playedTilesShouldBePlacedInOneLine() {
			tilesToPlay.add(new Field.Builder(6,11).setTile('P').build());
			tilesToPlay.add(new Field.Builder(7,11).setTile('O').build());
			tilesToPlay.add(new Field.Builder(7,12).setTile('D').build());
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}

		@Test
		void playedTilesShouldNotBeSeparated() {
			tilesToPlay.add(new Field.Builder(7,5).setTile('T').build());
			// take note about space between T and other tiles
			tilesToPlay.add(new Field.Builder(7,7).setTile('A').build());
			tilesToPlay.add(new Field.Builder(7,8).setTile('M').build());
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });			
		}

		@Test
		void atLeastOneTileShouldAdhereToOneAlreadyPlacedOnGameBoard() {
			tilesToPlay.add(new Field.Builder(7,6).setTile('J').build());
			tilesToPlay.add(new Field.Builder(8,6).setTile('A').build());
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}
		
		@Test
		void cannotPlaceTileOnOccupiedField() {
			tilesToPlay.add(new Field.Builder(8,8).setTile('T').build());
			tilesToPlay.add(new Field.Builder(8,9).setTile('Y').build());
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}
	}

	//TODO consider creation of nested Premium tests if Word Combiner
}
