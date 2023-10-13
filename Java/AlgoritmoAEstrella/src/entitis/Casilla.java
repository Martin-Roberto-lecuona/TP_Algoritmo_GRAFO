package entitis;

import java.util.Objects;

public class Casilla {
	private final int x, y;
	private double  g,h;
	private Casilla padre; 

	public Casilla(int x, int y ) {
		this.x = x;
		this.y = y;
		this.g = 0;
		this.h = Double.POSITIVE_INFINITY;
	}
	public Casilla(Casilla c) {
		this.x = c.getX();
		this.y = c.getY();
		this.g = c.getG();
		this.h = c.getH();
		this.padre = c.getpadre();
	}
	public void setpadre(Casilla p) {
		this.padre = p;
	}
	public Casilla getpadre() {
		return this.padre;
	}
	
	public double getG() {
		return g;
	}

	public void setG(double d) {
		this.g = d;
	}

	public double getH() {
		return h;
	}

	public void setH(Double h) {
		this.h = h;
	}

	public double getF() {
		return g+h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ";" + y + ")" + "["+ this.getF()+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casilla other = (Casilla) obj;
		return x == other.x && y == other.y;
	}
	public boolean mayor(Casilla b) {
		if (this.getF() == Double.POSITIVE_INFINITY )
			return true;
		return Double.compare(this.getF(), b.getF()) >= 0 ? true : false;
	}
}
