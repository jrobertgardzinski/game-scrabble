package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {

	@Test
	void bothTilesShouldBeEqual() {
		Tile tile1 = new Tile("A", 1);
		Tile tile2 = new Tile("A", 1);
		assertTrue(tile1.equals(tile2));
	}

}
