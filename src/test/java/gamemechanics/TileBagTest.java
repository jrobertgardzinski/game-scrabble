package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class TileBagTest {

	@Nested
	class usage {
		TileBag fiveLettersBag;

		@BeforeEach
		void setup() {
			Tile a = new Tile('A', 1);
			Tile b = new Tile('B', 3);
			Tile c = new Tile('C', 2);
			Tile d = new Tile('D', 2);
			Tile e = new Tile('E', 1);
			Tile[] letterSet = { a, b, c, d, e };
			LinkedList<Tile> lettersForInitialization = new LinkedList<Tile>(Arrays.asList(letterSet));
			fiveLettersBag = new TileBag(lettersForInitialization);
		}

		@Test
		void shouldLeftTwoTilesInBagAfterDrawingThreeTiles() {
			int tilesToDraw = 3;
			int tilesInBagBeforeDrawing = fiveLettersBag.getLetterList().size();
			assertAll(() -> assertEquals(tilesToDraw, fiveLettersBag.drawTiles(tilesToDraw).size()),
					() -> assertEquals(tilesInBagBeforeDrawing - tilesToDraw, fiveLettersBag.getLetterList().size()));
		}

		@Test
		void shouldLeftNoTilesAfterDrawingMoreThanFiveTiles() {
			int sixTiles = 6;
			int tilesInBagBeforeDrawing = fiveLettersBag.getLetterList().size();
			assertAll(() -> assertEquals(tilesInBagBeforeDrawing, fiveLettersBag.drawTiles(sixTiles).size()),
					() -> assertEquals(0, fiveLettersBag.getLetterList().size()));
		}

		// TODO Consider refactor of this test
		@Test
		void shouldDrawDifferentTileAtLeastOnceAfterTenAttempts() {
			Tile tileI = new Tile('I', 1);
			List<Tile> tileRack = Collections.nCopies(7, tileI);
			List<Tile> tileToReplace = (List<Tile>) tileRack.subList(0, 1);
			boolean drawnDifferentTile = false;
			for (int i = 0; i < 10; i++){
				List<Tile> drawnTiles = fiveLettersBag.replaceTiles(tileToReplace);
				Tile drawnTile = drawnTiles.get(0);
				if (!tileI.equals(drawnTile))
					drawnDifferentTile = true;
			}
			assertTrue(drawnDifferentTile);
		}

	}

}
