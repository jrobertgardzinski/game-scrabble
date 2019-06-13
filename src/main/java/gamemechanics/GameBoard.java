package gamemechanics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import gamemechanics.enums.Premium;

public class GameBoard {
	private Map<Coordinates, Tile> tilesArrangement = new HashMap<Coordinates, Tile>(); 
	private Map<Coordinates, Tile> previosulyPlayedTiles = new HashMap<Coordinates, Tile>();
	//Premium fields coordinates
	private final Coordinates CenterField = new Coordinates(8, 8);
	private final Coordinates[] doubleLetter = {
			new Coordinates(1, 4),
			new Coordinates(1, 12),
			
			new Coordinates(3, 7),
			new Coordinates(3, 9),

			new Coordinates(4, 8),
			new Coordinates(4, 1),
			new Coordinates(4, 15),
			
			new Coordinates(7, 3),
			new Coordinates(7, 7),
			new Coordinates(7, 9),
			new Coordinates(7, 13),
			
			new Coordinates(8, 4),
			new Coordinates(8, 12),
			
			new Coordinates(9, 3),
			new Coordinates(9, 7),
			new Coordinates(9, 9),
			new Coordinates(9, 13),

			new Coordinates(12, 8),
			new Coordinates(12, 1),
			new Coordinates(12, 15),
			
			new Coordinates(13, 7),
			new Coordinates(13, 9),

			new Coordinates(15, 4),
			new Coordinates(15, 12)
	};
	private final Coordinates[] tripleLetter = {
			new Coordinates(2, 6),
			new Coordinates(2, 10),
			
			new Coordinates(6, 2),
			new Coordinates(6, 6),
			new Coordinates(6, 10),
			new Coordinates(6, 14),
			
			new Coordinates(10, 2),
			new Coordinates(10, 6),
			new Coordinates(10, 10),
			new Coordinates(10, 14),
			
			new Coordinates(14, 6),
			new Coordinates(14, 10)
	};
	private final Coordinates[] doubleWord = {
			new Coordinates(2, 6),
			new Coordinates(2, 10),
			
			new Coordinates(6, 2),
			new Coordinates(6, 6),
			new Coordinates(6, 10),
			new Coordinates(6, 14),
			
			CenterField,
			
			new Coordinates(10, 2),
			new Coordinates(10, 6),
			new Coordinates(10, 10),
			new Coordinates(10, 14),
			
			new Coordinates(14, 6),
			new Coordinates(14, 10)
	};
	private final Coordinates[] tripleWord = {
			new Coordinates(1, 1),
			new Coordinates(1, 8),
			new Coordinates(1, 15),

			new Coordinates(8, 1),
			new Coordinates(8, 15),

			new Coordinates(15, 1),
			new Coordinates(15, 8),
			new Coordinates(15, 15)
	};	
	
	private Map<Coordinates, Premium> premiumFields = new HashMap<Coordinates, Premium>();
	
	public GameBoard() {
		Arrays.stream(doubleLetter).forEach(element -> {premiumFields.put(element, Premium.DOUBLE_LETTER);});
		Arrays.stream(tripleLetter).forEach(element -> {premiumFields.put(element, Premium.TRIPLE_LETTER);});
		Arrays.stream(doubleWord).forEach(element -> {premiumFields.put(element, Premium.DOUBLE_WORD);});
		Arrays.stream(tripleWord).forEach(element -> {premiumFields.put(element, Premium.TRIPLE_WORD);});
	}
	
	public Map<Coordinates, Tile> getTilesArrangement() {
		return tilesArrangement;
	}

	public void setTilesArrangement(Map<Coordinates, Tile> tilesArrangement) {
		this.tilesArrangement = tilesArrangement;
	}

	public Map<Coordinates, Tile> getPreviosulyPlayedTiles() {
		return previosulyPlayedTiles;
	}

	public void setPreviosulyPlayedTiles(Map<Coordinates, Tile> previosulyPlayedTiles) {
		this.previosulyPlayedTiles = previosulyPlayedTiles;
	}
	
	
	public void flushPreviouslyPlayedTiles() {
		this.previosulyPlayedTiles.clear();
	}
	
	public void updateTheBoardAndFlushPreviouslyPlayedTiles() {
		this.tilesArrangement.putAll(previosulyPlayedTiles);
		flushPreviouslyPlayedTiles();
	}
	
	public Map<Coordinates, Tile> removePreviouslyPlayedTiles() {
		Map<Coordinates, Tile> playedTiles = new HashMap(this.previosulyPlayedTiles);
		flushPreviouslyPlayedTiles();
		return playedTiles;
	}
	
	public void playTiles(Map<Coordinates, Tile> playedTiles) {
		if (isFirstPlayedWord() && noneOfTilesPlacedOnCenterOfGameBoard(playedTiles)) {
			throw new IllegalArgumentException("First played word must be placed on the center of game board!");
		}
		else if (!tilesArrangedInLine(playedTiles)) {
			throw new IllegalArgumentException("Tiles must be placed in one line!");
		}
		else if (atLeastOneFieldIsAlreadyOccupied(playedTiles)) {
			throw new IllegalArgumentException("At least one field is already occupied by another tile!");
		}
		else if (!atLeastOneTileShouldAdhereToAnotherOneAlreadyPlacedOnGameBoard(playedTiles)) {
			throw new IllegalArgumentException("At least one tile should adhere tile placed on game board!");
		}
		else if (areTilesSeparated(playedTiles)) {
			throw new IllegalArgumentException("Played tiles should not be separated!");
		}
		this.previosulyPlayedTiles = playedTiles;
	}
	
	private boolean isFirstPlayedWord() {
		return this.previosulyPlayedTiles.isEmpty() && this.tilesArrangement.isEmpty();
	}
	
	private boolean noneOfTilesPlacedOnCenterOfGameBoard(Map<Coordinates, Tile> playedTiles) {
		return !playedTiles.containsKey(CenterField);
	}

	// TODO use groupBy rather than two streams
	private boolean tilesArrangedInLine(Map<Coordinates, Tile> playedTiles) {
		boolean xCoordinatesChangesCorrectly =
				playedTiles.keySet().stream()
				.map(Coordinates::getX)
				.distinct()
				.limit(2)
				.count() == 1;
		boolean yCoordinatesChangesCorrectly =
				playedTiles.keySet().stream()
				.map(Coordinates::getY)
				.distinct()
				.limit(2)
				.count() == 1;
		return xCoordinatesChangesCorrectly || yCoordinatesChangesCorrectly;
	}

	private boolean atLeastOneFieldIsAlreadyOccupied(Map<Coordinates, Tile> playedTiles) {
		return playedTiles.keySet().stream()
				.filter(coordinates -> this.tilesArrangement.containsKey(coordinates))
				.count() > 0;
	}
	
	private boolean atLeastOneTileShouldAdhereToAnotherOneAlreadyPlacedOnGameBoard(Map<Coordinates, Tile> playedTiles) {
		return playedTiles.keySet().stream()
				.filter(playedTile -> {
					int x = playedTile.getX();
					int y = playedTile.getY();
					try {
						if (this.tilesArrangement.containsKey(new Coordinates(x-1, y))
							|| this.tilesArrangement.containsKey(new Coordinates(x+1, y))
							|| this.tilesArrangement.containsKey(new Coordinates(x, y+1))
							|| this.tilesArrangement.containsKey(new Coordinates(x, y-1))
								)
							return true;
						else
							return false;
					}
					catch (Exception ex) {
						return false;
					}
				})
				.limit(1)
				.count() > 0;
	}
	// TODO Implement word direction checker?
	private boolean areTilesSeparated(Map<Coordinates, Tile> playedTiles) {
		// find longest distance between tiles
		// iterate through the first one to the last one and
		// check if 
		// playedTiles contains key 
		// or
		// tilesArrangement contains key
		// else
		// false					
		if (playedTiles.size() == 1)
		{
			return true;
		}
		else {
			boolean result = false;
			// TODO separate this fragment to method findWord or something
			int minX = 
					playedTiles.keySet().stream()
					.map(key -> key.getX())
					.reduce(BinaryOperator.minBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find min X coordination"));
			int minY = 
					playedTiles.keySet().stream()
					.map(key -> key.getY())
					.reduce(BinaryOperator.minBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find min Y coordination"));
			int maxX = 
					playedTiles.keySet().stream()
					.map(key -> key.getX())
					.reduce(BinaryOperator.maxBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find max X coordination"));
			int maxY = 
					playedTiles.keySet().stream()
					.map(key -> key.getY())
					.reduce(BinaryOperator.maxBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find max Y coordination"));

			List<Coordinates> fieldsSequence = new LinkedList<Coordinates>();
			if (minX == maxX) {
				result = !Stream.iterate(minY, n -> n+1).limit(maxY - minY)
						.allMatch(n -> this.tilesArrangement.containsKey(new Coordinates(minX, n))
									|| playedTiles.containsKey(new Coordinates(minX, n)));
			}
			else {
				result = !Stream.iterate(minY, n -> n+1).limit(maxX - minX)
						.allMatch(n -> this.tilesArrangement.containsKey(new Coordinates(minY, n))
									|| playedTiles.containsKey(new Coordinates(minX, n)));
			}
			return result;
		}
	}
	
	public Premium getPremiumIfExists(Coordinates coordinates) {
		return premiumFields.get(coordinates);
	}
}
