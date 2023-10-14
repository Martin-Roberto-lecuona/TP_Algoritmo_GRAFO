package entitis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Mapa {
	private Double[][] terreno;
	int x, y;

	public Mapa(Double[][] t) {
		terreno = t;
		x = terreno.length;
		y = terreno[0].length;
	}

	public boolean esObstaculo(Casilla c) {
		return terreno[c.getX()][c.getY()] == Double.POSITIVE_INFINITY;
	}

	public boolean esObstaculo(int x, int y) {
		return terreno[x][y] == Double.POSITIVE_INFINITY;
	}

	public Double[][] getTerreno() {
		return terreno;
	}

	public void printMapa() {
		System.out.print("   ");
		for (int i = 0; i < this.y; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
		for (int i = 0; i < this.x; i++) {
			System.out.print(" " + i + " ");
			for (int j = 0; j < this.y; j++) {
				if (!esObstaculo(i, j))
					System.out.print("|○|");
				else
					System.out.print("|X|");
			}
			System.out.println();
		}
	}

	public ArrayList<Casilla> camino(Casilla origen, Casilla destino) {
		if (!casillaValida(origen)) {
			System.out.println("la casilla de origen no es valida");
			return null;
		}
		if (!casillaValida(destino)) {
			System.out.println("la casilla de destino no es valida");
			return null;
		}
		ArrayList<Casilla> cerrado = new ArrayList<Casilla>();
		origen.setH(getDistanciaEntreCasilas(origen, destino));
		Casilla act = origen;
		cerrado.add(act);
		ArrayList<Casilla> sinSalida = new ArrayList<Casilla>();

		ArrayList<Casilla> vecinos = getVecinos(act, cerrado, destino, sinSalida);
		ArrayList<Casilla> abierto = getVecinos(act, cerrado, destino, sinSalida);

		calcularPesoVecinos(destino, vecinos, abierto, act);

		while (!cerrado.contains(destino)) {
			try {
				act = Collections.min(vecinos, (o1, o2) -> Double.compare(o1.getF(), o2.getF()));
				act = agregarMinimoCerrado(cerrado, act, abierto);
				vecinos = getVecinos(act, cerrado, destino, sinSalida);

				ponerSinRepetir(abierto, vecinos);
				calcularPesoVecinos(destino, vecinos, abierto, act);

			} catch (NoSuchElementException e) {
				if (cerrado.isEmpty())
					return cerrado;// se quedo sin salida y no hay solucion
				sinSalida.add(cerrado.remove(cerrado.size() - 1));
				vecinos = getVecinos(cerrado.get(cerrado.size() - 1), cerrado, destino, sinSalida);
				System.out.println("act: " + act);
			}
		}

		ArrayList<Casilla> camino = new ArrayList<Casilla>();
		crearCamino(cerrado, camino);
		return camino;
	}

	private void crearCamino(ArrayList<Casilla> cerrado, ArrayList<Casilla> camino) {
		Casilla aux = cerrado.get(cerrado.size() - 1);
		camino.add(aux);
		while (aux != null) {
			aux = cerrado.get(cerrado.indexOf(aux)).getpadre();
			camino.add(aux);
		}
	}

	private boolean casillaValida(Casilla c) {
		return c.getX() <= x && c.getX() >= 0 && c.getY() <= y && c.getY() >= 0;
	}

	private Casilla agregarMinimoCerrado(ArrayList<Casilla> cerrado, Casilla act, ArrayList<Casilla> abierto) {
		if (act.getF() > abierto.get(abierto.indexOf(act)).getF()) {
			act = abierto.get(abierto.indexOf(act));
		}

		cerrado.add(act);
		return act;
	}

	private void ponerSinRepetir(ArrayList<Casilla> abierto, ArrayList<Casilla> vecinos) {
		for (Casilla vec : vecinos) {
			if (!abierto.contains(vec))
				abierto.add(vec);
			else if (abierto.get(abierto.indexOf(vec)).getF() > vec.getF()) {
				int pos = abierto.indexOf(vec);
				abierto.remove(pos);
				abierto.add(pos, vec);
			}
		}

	}

	private void calcularPesoVecinos(Casilla destino, ArrayList<Casilla> vecinos, ArrayList<Casilla> abierto,
			Casilla act) {

		for (int i = 0; i < vecinos.size(); i++) {
			Casilla sucesor = vecinos.get(i);
			int indiceEnAbierto = abierto.indexOf(sucesor);
			if (abierto.get(indiceEnAbierto).getF() > sucesor.getF()) {
				abierto.remove(indiceEnAbierto);
				abierto.add(indiceEnAbierto, sucesor);
			}

		}
	}

	private Double getDistanciaEntreCasilas(Casilla a, Casilla b) {
		// ACA ES DONDE IMPORTA LA HEURISTICA
		int dx = Math.abs(b.getX() - a.getX());
		int dy = Math.abs(b.getY() - a.getY());
		Double dist = (dx + dy) + ((Math.sqrt(2) - 2) * getMin(dx, dy));
		return dist;
	}

	private double getMin(int a, int b) {
		return a < b ? a : b;
	}

	private ArrayList<Casilla> getVecinos(Casilla c, ArrayList<Casilla> cerrado, Casilla destino,
			ArrayList<Casilla> sinSalida) {
		int inicioX = c.getX() - 1;
		int inicioY = c.getY() - 1;
		ArrayList<Casilla> rta = new ArrayList<Casilla>();
		for (int i = inicioX; i < inicioX + 3 && i < this.x; i++) {
			for (int j = inicioY; j < inicioY + 3 && j < this.y; j++) {
				Casilla aux = new Casilla(i, j);
				if (i >= 0 && j >= 0 && !esObstaculo(aux) && !cerrado.contains(aux) && !sinSalida.contains(aux)) {
					Double distanciaActSucesor = getDistanciaEntreCasilas(aux, c);
					Double distanciaActDestino = getDistanciaEntreCasilas(aux, destino);
					aux.setG(c.getG() + distanciaActSucesor);
					aux.setH(distanciaActDestino);
					aux.setpadre(c);
					rta.add(aux);
				}
			}
		}

		return rta;
	}

	public void printCamino(ArrayList<Casilla> camino) {
		System.out.print("   ");
		for (int i = 0; i < this.y; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
		for (int i = 0; i < this.x; i++) {
			if (i > 9)
				System.out.print(i + " ");
			else
				System.out.print(" " + i + " ");
			for (int j = 0; j < this.y; j++) {
				if (!esObstaculo(i, j)) {
					Casilla aux = new Casilla(i, j);
					if (camino.contains(aux))
						System.out.print("|•|");
					else
						System.out.print("|○|");
				} else
					System.out.print("|X|");
			}
			System.out.println();
		}

	}

}
