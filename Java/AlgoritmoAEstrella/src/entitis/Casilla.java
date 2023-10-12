package entitis;

public class Casilla {
	private final int x, y;

	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ";" + y + ")";
	}
}
