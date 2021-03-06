package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gamemechanics.enums.PolishAlphabet;

class WordTest {

	@Test
	void toStringMethodShouldContainsFormaWord() {
		List<Field> tiles = new LinkedList<>();
		tiles.add(new Field.Builder(new Coordinates(1,1)).setTile(new Tile(PolishAlphabet.F)).build());
		tiles.add(new Field.Builder(new Coordinates(1,2)).setTile(new Tile(PolishAlphabet.O)).build());
		tiles.add(new Field.Builder(new Coordinates(1,3)).setTile(new Tile(PolishAlphabet.R)).build());
		tiles.add(new Field.Builder(new Coordinates(1,4)).setTile(new Tile(PolishAlphabet.M)).build());
		tiles.add(new Field.Builder(new Coordinates(1,5)).setTile(new Tile(PolishAlphabet.A)).build());
		Word word = new Word(tiles);
		
		assertTrue(word.toString().contains("FORMA"));
	}

	@Test
	void equality() {
		List<Field> tiles = new LinkedList<>();
		tiles.add(new Field.Builder(new Coordinates(1,1)).setTile(new Tile(PolishAlphabet.F)).build());
		tiles.add(new Field.Builder(new Coordinates(1,2)).setTile(new Tile(PolishAlphabet.O)).build());
		tiles.add(new Field.Builder(new Coordinates(1,3)).setTile(new Tile(PolishAlphabet.R)).build());
		tiles.add(new Field.Builder(new Coordinates(1,4)).setTile(new Tile(PolishAlphabet.M)).build());
		tiles.add(new Field.Builder(new Coordinates(1,5)).setTile(new Tile(PolishAlphabet.A)).build());
		Word word = new Word(tiles);
		
		List<Field> tiles2 = new LinkedList<>();
		tiles2.add(new Field.Builder(new Coordinates(1,1)).setTile(new Tile(PolishAlphabet.F)).build());
		tiles2.add(new Field.Builder(new Coordinates(1,2)).setTile(new Tile(PolishAlphabet.O)).build());
		tiles2.add(new Field.Builder(new Coordinates(1,3)).setTile(new Tile(PolishAlphabet.R)).build());
		tiles2.add(new Field.Builder(new Coordinates(1,4)).setTile(new Tile(PolishAlphabet.M)).build());
		tiles2.add(new Field.Builder(new Coordinates(1,5)).setTile(new Tile(PolishAlphabet.A)).build());
		Word word2 = new Word(tiles2);
		
		assertTrue(word.equals(word2));
	}

	@Test
	void equality2() {
		List<Field> tiles = new LinkedList<>();
		tiles.add(new Field.Builder(new Coordinates(1,1)).setTile(new Tile(PolishAlphabet.F)).build());
		tiles.add(new Field.Builder(new Coordinates(1,2)).setTile(new Tile(PolishAlphabet.O)).build());
		tiles.add(new Field.Builder(new Coordinates(1,3)).setTile(new Tile(PolishAlphabet.R)).build());
		tiles.add(new Field.Builder(new Coordinates(1,4)).setTile(new Tile(PolishAlphabet.M)).build());
		tiles.add(new Field.Builder(new Coordinates(1,5)).setTile(new Tile(PolishAlphabet.A)).build());
		Word word = new Word(tiles);
		
		List<Field> tiles2 = new LinkedList<>();
		tiles2.add(new Field.Builder(new Coordinates(5,1)).setTile(new Tile(PolishAlphabet.F)).build());
		tiles2.add(new Field.Builder(new Coordinates(5,2)).setTile(new Tile(PolishAlphabet.O)).build());
		tiles2.add(new Field.Builder(new Coordinates(5,3)).setTile(new Tile(PolishAlphabet.R)).build());
		tiles2.add(new Field.Builder(new Coordinates(5,4)).setTile(new Tile(PolishAlphabet.M)).build());
		tiles2.add(new Field.Builder(new Coordinates(5,5)).setTile(new Tile(PolishAlphabet.A)).build());
		Word word2 = new Word(tiles2);

		assertFalse(word.equals(word2));
	}
}
