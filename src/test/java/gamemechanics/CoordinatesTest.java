package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CoordinatesTest {

	@Test
	void bothCoordinatesShouldBeEqual() {
		Coordinates first = new Coordinates(1, 1);
		Coordinates second = new Coordinates(1, 1);
		
		assertTrue(first.equals(second));
	}

	@Test
	void bothCoordinatesShouldNotBeEqual() {
		Coordinates first = new Coordinates(1, 1);
		Coordinates second = new Coordinates(2, 2);
		
		assertFalse(first.equals(second));
	}
	
	@Test
	void throwsException() {
		Coordinates temp = new Coordinates(5, 5);
		assertAll(
				() -> assertThrows(IllegalArgumentException.class, () -> {new Coordinates(-1, 2);}),
				() -> assertThrows(IllegalArgumentException.class, () -> {temp.setX(16);}),
				() -> assertThrows(IllegalArgumentException.class, () -> {temp.setX(0);}),
				() -> assertThrows(IllegalArgumentException.class, () -> {temp.setY(16);}),
				() -> assertThrows(IllegalArgumentException.class, () -> {temp.setY(0);})
			);
	}
}
