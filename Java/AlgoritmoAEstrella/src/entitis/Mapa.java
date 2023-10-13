package entitis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Mapa {
	private Double[][] terreno;

	public Mapa(Double[][] t) {
		terreno = t;
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
		for (int i = 0; i < terreno[0].length; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
		for (int i = 0; i < terreno.length; i++) {
			System.out.print(" " + i + " ");
			for (int j = 0; j < terreno[0].length; j++) {
				if (!esObstaculo(i, j))
					System.out.print("|○|");
				else
					System.out.print("|X|");
			}
			System.out.println();
		}
	}

	public ArrayList<Casilla> camino(Casilla origen, Casilla destino) {
		//
		// check si existe o es obstaculo si destino u origen
		//
		ArrayList<Casilla> cerrado = new ArrayList<Casilla>();
		origen.setH(getDistanciaEntreCasilas(origen, destino));
		Casilla act = origen;
		cerrado.add(act);
		ArrayList<Casilla> sinSalida = new ArrayList<Casilla>();
		ArrayList<Casilla> todosVecinos = new ArrayList<Casilla>();
		ArrayList<Casilla> vecinos = getVecinos(act, cerrado, destino,sinSalida);
		ArrayList<Casilla> abierto = getVecinos(act, cerrado, destino,sinSalida);

		calcularPesoVecinos(destino, vecinos, abierto, act);
		
	
		while (!cerrado.contains(destino)) {
			try {
				act = Collections.min(vecinos, (o1, o2) -> Double.compare(o1.getF(), o2.getF()));
//				if(todosVecinos.contains(act)) {
//					System.out.println("Pasa otra vez");
//					act=todosVecinos.get(todosVecinos.indexOf(act));
//					borrarDeCerradoPorVuelta(act,cerrado);
//				}
				System.out.println("ACT: " + act);
				agregarMinimoCerrado(cerrado,act,abierto);
				vecinos = getVecinos(act, cerrado, destino, sinSalida);
				
				ponerSinRepetir(abierto, vecinos);
				calcularPesoVecinos(destino, vecinos, abierto, act);
					
			} catch (NoSuchElementException e) {
				if(cerrado.isEmpty())
					return cerrado;
				sinSalida.add(cerrado.remove(cerrado.size()-1));
				vecinos = getVecinos(cerrado.get(cerrado.size()-1), cerrado, destino, sinSalida);
				System.out.println("act: " + act);
			}		
			todosVecinos.addAll(vecinos);
			
		}

		System.out.println();
		ArrayList<Casilla> camino = new ArrayList<Casilla>();
		int i = cerrado.size() - 1;
		camino.add(cerrado.get(i));
		while (!camino.contains(origen)) {
			camino.add(cerrado.get(i).getpadre());
			i--;
		}
		return camino;
	}


	private void borrarDeCerradoPorVuelta(Casilla act, ArrayList<Casilla> cerrado) {
		for (int i = cerrado.indexOf(act); i < cerrado.size(); i++) {
			cerrado.remove(i);
		}
	}

	private void agregarMinimoCerrado(ArrayList<Casilla> cerrado, Casilla act, ArrayList<Casilla> abierto) {	
		if(act.getF() > abierto.get(abierto.indexOf(act)).getF()) {
			act = abierto.get(abierto.indexOf(act));
		}
		
		cerrado.add(act);
	}

	private void ponerSinRepetir(ArrayList<Casilla> abierto, ArrayList<Casilla> vecinos) {
		for (Casilla vec : vecinos) {
			if (!abierto.contains(vec))
				abierto.add(vec);
			else if (abierto.get(abierto.indexOf(vec)).getF() > vec.getF()) {
				int pos = abierto.indexOf(vec);
				System.out.println("REMPLAZOOO");
				abierto.remove(pos);
				abierto.add(pos, vec);
			}
		}

	}

	private void calcularPesoVecinos(Casilla destino, ArrayList<Casilla> vecinos, ArrayList<Casilla> abierto,
			Casilla act) {

		for (int i = 0; i < vecinos.size(); i++) {
			Casilla sucesor = vecinos.get(i);
//			Casilla sucesor = new Casilla(vecinos.get(i).getX(),vecinos.get(i).getY());
//			Double distanciaActSucesor = getDistanciaEntreCasilas(sucesor, act);
//			Double distanciaActDestino = getDistanciaEntreCasilas(sucesor, destino);
//
//			sucesor.setG(act.getG() + distanciaActSucesor);
//			sucesor.setH(distanciaActDestino);
//			sucesor.setpadre(act);

			int indiceEnAbierto = abierto.indexOf(sucesor);
			if (abierto.get(indiceEnAbierto).getF() > sucesor.getF()) {
//				System.out.println("-------------------------------------");
//				System.out.println("de: " + abierto.get(indiceEnAbierto) + " a: " + sucesor);
//				System.out.println(" padre Viejo : " + abierto.get(indiceEnAbierto).getpadre() + " padre NUEVO : "
//						+ sucesor.getpadre());
//				System.out.println("-------------------------------------");
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

	private ArrayList<Casilla> getVecinos(Casilla c, ArrayList<Casilla> cerrado, Casilla destino, ArrayList<Casilla> sinSalida) {
		int inicioX = c.getX() - 1;
		int inicioY = c.getY() - 1;
		ArrayList<Casilla> rta = new ArrayList<Casilla>();
		for (int i = inicioX; i < inicioX + 3 && i < terreno.length; i++) {
			for (int j = inicioY; j < inicioY + 3 && j < terreno[0].length; j++) {
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
		for (int i = 0; i < terreno[0].length; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
		for (int i = 0; i < terreno.length; i++) {
			if (i>9)
				System.out.print( i + " ");
			else
				System.out.print(" " + i + " ");
			for (int j = 0; j < terreno[0].length; j++) {
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
