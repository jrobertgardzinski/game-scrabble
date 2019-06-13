package gamemechanics;

import java.util.Map;

public class Field {
	Coordinates coordinates;
	Tile tile;

	public Field(int x, int y, char letter) {
		super();
		this.coordinates = new Coordinates(x, y);
		
		this.tile = new Tile(gamemechanics.enums.Tile.A);
	}
	
	public Field(Coordinates coordinates, Tile tile) {
		super();
		this.coordinates = coordinates;
		this.tile = tile;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	
}
