package gamemechanics;

import java.util.Map;

import gamemechanics.enums.PolishAlphabet;
import gamemechanics.enums.Premium;

public class Field {
	private Coordinates coordinates;
	private Tile tile;
	private Premium premium;

	public Field(Builder builder) {
		this.coordinates = builder.coordinates;
		this.tile = builder.tile;
		this.premium = builder.premium;
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

	public Premium getPremium() {
		return premium;
	}

	public static class Builder {
		private Coordinates coordinates;
		private Tile tile;
		private Premium premium;

		public Builder(Coordinates coordinates) {
			this.coordinates = coordinates;
		}

		public Builder(int x, int y) {
			this.coordinates = new Coordinates(x, y);
		}

		public Builder setTile(Tile tile) {
			this.tile = tile;
			return this;
		}
		
		public Builder setTile(char letter) {
			this.tile = new Tile(letter);
			return this;
		}

		public Builder setPremium(Premium premium) {
			this.premium = premium;
			return this;
		}

		public Field build() {
			return new Field(this);
		}
	}
}
