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
	private static final int boardSize = 15;
	private Field[][] tilesArrangement = new Field[boardSize][boardSize]; 
	private List<Field> previosulyPlayedTiles = new LinkedList<Field>();
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
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				this.tilesArrangement[x][y] = new Field.Builder(x+1, y+1).build();
			}
		}
		Arrays.stream(doubleLetter).forEach(element -> {premiumFields.put(element, Premium.DOUBLE_LETTER);});
		Arrays.stream(tripleLetter).forEach(element -> {premiumFields.put(element, Premium.TRIPLE_LETTER);});
		Arrays.stream(doubleWord).forEach(element -> {premiumFields.put(element, Premium.DOUBLE_WORD);});
		Arrays.stream(tripleWord).forEach(element -> {premiumFields.put(element, Premium.TRIPLE_WORD);});
	}
	
	public Field[][] getTilesArrangement() {
		return tilesArrangement;
	}

	public void setTilesArrangement(Field[][] tilesArrangement) {
		Stream.of(tilesArrangement).flatMap(fields -> Arrays.stream(fields))
		.filter(field -> field != null)
		.forEach(field -> {
			int x = field.getCoordinates().getX();
			int y = field.getCoordinates().getY();
			
			this.tilesArrangement[x][y] = field;
		});
	}

	public List<Field> getPreviosulyPlayedTiles() {
		return previosulyPlayedTiles;
	}

	public void setPreviosulyPlayedTiles(List<Field> previosulyPlayedTiles) {
		this.previosulyPlayedTiles = previosulyPlayedTiles;
	}
	
	
	public void flushPreviouslyPlayedTiles() {
		this.previosulyPlayedTiles.clear();
	}
	
	public void updateTheBoardAndFlushPreviouslyPlayedTiles() {
		previosulyPlayedTiles.stream().forEach(field -> {
			int x = field.getCoordinates().getX();
			int y = field.getCoordinates().getY();
			tilesArrangement[x][y] = field;
		});
		flushPreviouslyPlayedTiles();
	}
	
	public List<Field> removePreviouslyPlayedTiles() {
		List<Field> playedTiles = new LinkedList(this.previosulyPlayedTiles);
		flushPreviouslyPlayedTiles();
		return playedTiles;
	}
	
	public void playTiles(List<Field> playedTiles) {
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
		return this.previosulyPlayedTiles.isEmpty() && isGameBoardEmpty();
	}
	
	private boolean isGameBoardEmpty() {
		return Stream.of(this.tilesArrangement)
			.flatMap(fieldRows -> Arrays.stream(fieldRows))
			.allMatch(field -> field.getTile() == null);
	}
	
	private boolean noneOfTilesPlacedOnCenterOfGameBoard(List<Field> playedTiles) {
		return !playedTiles.stream()
				.anyMatch(tile -> tile.getCoordinates().equals(CenterField));
	}

	private boolean tilesArrangedInLine(List<Field> playedTiles) {
		boolean xCoordinatesChangesCorrectly =
				playedTiles.stream()
				.map(Field::getCoordinates)
				.distinct()
				.limit(2)
				.count() == 1;
		boolean yCoordinatesChangesCorrectly =
				playedTiles.stream()
				.map(Field::getCoordinates)
				.distinct()
				.limit(2)
				.count() == 1;
		return xCoordinatesChangesCorrectly || yCoordinatesChangesCorrectly;
	}

	private boolean atLeastOneFieldIsAlreadyOccupied(List<Field> playedTiles) {
		return playedTiles.stream()
				.filter(
					field -> Stream.of(this.tilesArrangement)
					.flatMap(fieldRows -> Arrays.stream(fieldRows))
					.map(Field::getCoordinates).equals(field.getCoordinates()) 
				)
				.count() > 0;
	}
	
	private boolean atLeastOneTileShouldAdhereToAnotherOneAlreadyPlacedOnGameBoard(List<Field> playedTiles) {
		return playedTiles.stream()
				.filter(playedTile -> {
					int x = playedTile.getCoordinates().getX();
					int y = playedTile.getCoordinates().getY();
					try {
						if (Stream.of(this.tilesArrangement)
								.flatMap(fieldRows -> Arrays.stream(fieldRows)).map(Field::getCoordinates).equals(new Coordinates(x-1, y))
							|| Stream.of(this.tilesArrangement)
							.flatMap(fieldRows -> Arrays.stream(fieldRows)).map(Field::getCoordinates).equals(new Coordinates(x+1, y))
							|| Stream.of(this.tilesArrangement)
							.flatMap(fieldRows -> Arrays.stream(fieldRows)).map(Field::getCoordinates).equals(new Coordinates(x, y+1))
							|| Stream.of(this.tilesArrangement)
							.flatMap(fieldRows -> Arrays.stream(fieldRows)).map(Field::getCoordinates).equals(new Coordinates(x, y-1))) {
							return true;
						}
						else {
							return false;
						}							
					}
					catch (Exception ex) {
						return false;
					}
				})
				.limit(1)
				.count() > 0;
	}
	// TODO Implement word direction checker?
	private boolean areTilesSeparated(List<Field> playedTiles) {
		if (playedTiles.size() == 1)
		{
			return true;
		}
		else {
			boolean result = false;
			int minX = 
					playedTiles.stream()
					.map(Field::getCoordinates)
					.map(Coordinates::getX)
					.reduce(BinaryOperator.minBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find min X coordination"));
			int minY = 
					playedTiles.stream()
					.map(Field::getCoordinates)
					.map(Coordinates::getY)
					.reduce(BinaryOperator.minBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find min Y coordination"));
			int maxX = 
					playedTiles.stream()
					.map(Field::getCoordinates)
					.map(Coordinates::getX)
					.reduce(BinaryOperator.maxBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find max X coordination"));
			int maxY = 
					playedTiles.stream()
					.map(Field::getCoordinates)
					.map(Coordinates::getY)
					.reduce(BinaryOperator.maxBy(Integer::compareTo))
					.orElseThrow(() -> new IllegalStateException("Can't find max Y coordination"));

			List<Coordinates> fieldsSequence = new LinkedList<Coordinates>();
			if (minX == maxX) {
				result = !Stream.iterate(minY, n -> n+1).limit(maxY - minY)
						.allMatch(n -> Stream.of(this.tilesArrangement)
								.flatMap(fieldRows -> Arrays.stream(fieldRows)).map(Field::getCoordinates).equals(new Coordinates(minX, n))
									|| playedTiles.stream().map(Field::getCoordinates).equals(new Coordinates(minX, n)));
			}
			else {
				result = !Stream.iterate(minY, n -> n+1).limit(maxX - minX)
						.allMatch(n -> Stream.of(this.tilesArrangement)
								.flatMap(fieldRows -> Arrays.stream(fieldRows)).map(Field::getCoordinates).equals(new Coordinates(minY, n))
									|| playedTiles.stream().map(Field::getCoordinates).equals(new Coordinates(minY, n)));
			}
			return result;
		}
	}
	
	public Premium getPremiumIfExists(Coordinates coordinates) {
		return premiumFields.get(coordinates);
	}
}
