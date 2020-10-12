package gamemechanics;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x >= 1 && x <= 15) {
			this.x = x;
		}
		else
		{
			throw new IllegalArgumentException("X coordinate value must be between 1 and 15.");
		}
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(y >= 1 && y <= 15) {
			this.y = y;
		}
		else
		{
			throw new IllegalArgumentException("Y coordinate value must be between 1 and 15.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
