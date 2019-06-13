package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
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
			Map<Coordinates, Tile> playedTiles = new HashMap<Coordinates, Tile>();
			playedTiles.put(new Coordinates(1, 8), new Tile('A', 1));
			playedTiles.put(new Coordinates(1, 9), new Tile('W', 1));
			playedTiles.put(new Coordinates(1, 10), new Tile('I', 1));
			playedTiles.put(new Coordinates(1, 11), new Tile('Z', 1));
			playedTiles.put(new Coordinates(1, 12), new Tile('O', 1));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(playedTiles); });
		}
	
		@Test
		void centerFieldShouldHasDoubleWordPremium() {
			Map<Coordinates, Tile> playedTiles = new HashMap<Coordinates, Tile>();
			playedTiles.put(new Coordinates(8, 8), new Tile('A', 1));
			playedTiles.put(new Coordinates(8, 9), new Tile('W', 1));
			playedTiles.put(new Coordinates(8, 10), new Tile('I', 1));
			playedTiles.put(new Coordinates(8, 11), new Tile('Z', 1));
			playedTiles.put(new Coordinates(8, 12), new Tile('O', 1));
			
			assertTrue(gameBoard.getPremiumIfExists(new Coordinates(8, 8)).equals(Premium.DOUBLE_WORD));
		}
		
		@Test
		void firstPlayedWordConsistAtLeastOfTwoTiles() {
			Map<Coordinates, Tile> playedTiles = new HashMap<Coordinates, Tile>();
			playedTiles.put(new Coordinates(8, 8), new Tile('A', 1));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(playedTiles); });
		}
	}
	
	@Nested
	class HandlingLogicOfFurtherMoves {
		Map<Coordinates, Tile> tilesToPlay;
		Map<Coordinates, Tile> tilesOnBoard;
				
		@BeforeEach
		void reinitializePlayedTiles() {
			tilesToPlay = new HashMap<Coordinates, Tile>();
			
			tilesOnBoard = new HashMap<Coordinates, Tile>();
			// Given tiles will produce words:
			// AWIZO
			// ZDRÓW
			// BÓL
			tilesOnBoard.put(new Coordinates(8, 8), new Tile(gamemechanics.enums.Tile.A));
			tilesOnBoard.put(new Coordinates(8, 9), new Tile(gamemechanics.enums.Tile.W));
			tilesOnBoard.put(new Coordinates(8, 10), new Tile(gamemechanics.enums.Tile.I));
			tilesOnBoard.put(new Coordinates(8, 11), new Tile(gamemechanics.enums.Tile.Z));
			tilesOnBoard.put(new Coordinates(8, 12), new Tile(gamemechanics.enums.Tile.O));
			tilesOnBoard.put(new Coordinates(9, 11), new Tile(gamemechanics.enums.Tile.D));
			tilesOnBoard.put(new Coordinates(9, 11), new Tile(gamemechanics.enums.Tile.R));
			tilesOnBoard.put(new Coordinates(10, 11), new Tile(gamemechanics.enums.Tile.O_special));
			tilesOnBoard.put(new Coordinates(11, 11), new Tile(gamemechanics.enums.Tile.W));
			tilesOnBoard.put(new Coordinates(10, 10), new Tile(gamemechanics.enums.Tile.B));
			tilesOnBoard.put(new Coordinates(10, 12), new Tile(gamemechanics.enums.Tile.L));
			
			gameBoard.setTilesArrangement(tilesOnBoard);
		}
		
		@Test
		void playedTilesShouldBePlacedInOneLine() {
			tilesToPlay.put(new Coordinates(6, 11), new Tile(gamemechanics.enums.Tile.P));
			tilesToPlay.put(new Coordinates(7, 11), new Tile(gamemechanics.enums.Tile.O));
			tilesToPlay.put(new Coordinates(7, 12), new Tile(gamemechanics.enums.Tile.T));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}

		@Test
		void playedTilesShouldNotBeSeparated() {
			tilesToPlay.put(new Coordinates(7, 5), new Tile(gamemechanics.enums.Tile.T));
			// take note about space between T and other tiles
			tilesToPlay.put(new Coordinates(7, 7), new Tile(gamemechanics.enums.Tile.A));
			tilesToPlay.put(new Coordinates(7, 8), new Tile(gamemechanics.enums.Tile.M));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });			
		}

		@Test
		void atLeastOneTileShouldAdhereToOneAlreadyPlacedOnGameBoard() {
			tilesToPlay.put(new Coordinates(7, 6), new Tile(gamemechanics.enums.Tile.J));
			tilesToPlay.put(new Coordinates(8, 6), new Tile(gamemechanics.enums.Tile.A));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}
		
		@Test
		void cannotPlaceTileOnOccupiedField() {
			tilesToPlay.put(new Coordinates(8, 8), new Tile(gamemechanics.enums.Tile.T));
			tilesToPlay.put(new Coordinates(8, 9), new Tile(gamemechanics.enums.Tile.Y));
			
			assertThrows(IllegalArgumentException.class, () -> { gameBoard.playTiles(tilesToPlay); });
		}
	}

	//TODO consider creation of nested Premium tests
}
