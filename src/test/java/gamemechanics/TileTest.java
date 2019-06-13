package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	// TODO blank implementation
	@Test
	void bothTilesShouldBeEqual() {
		Tile tile1 = new Tile('A', 1);
		Tile tile2 = new Tile(gamemechanics.enums.Tile.A);
		assertTrue(tile1.equals(tile2));
	}
}
