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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result + ((premium == null) ? 0 : premium.hashCode());
		result = prime * result + ((tile == null) ? 0 : tile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		if (premium != other.premium)
			return false;
		if (tile == null) {
			if (other.tile != null)
				return false;
		} else if (!tile.equals(other.tile))
			return false;
		return true;
	}
	
	
}
