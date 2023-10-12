package entitis;

import java.util.ArrayList;

public class Mapa {
	private Double[][] terreno;

	public Mapa(Double[][] t) {
		terreno = t;
	}

	public boolean esObstaculo(Casilla c) {
		return terreno[c.getX()][c.getY()] == Double.POSITIVE_INFINITY;
	}

	public boolean esObstaculo(int x, int y) {
		return terreno[x][y]==Double.POSITIVE_INFINITY;
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
				if (!esObstaculo(i,j))
					System.out.print("|○|");
				else
					System.out.print("|X|");
			}
			System.out.println();
		}
	}
	
	public ArrayList<Casilla> camino (Casilla origen, Casilla destino){
		//
		// check si existe o es obstaculo si destino u origen 
		//
		ArrayList<Casilla> abierto = getVecinos(origen);
		ArrayList<Casilla> cerrado = new ArrayList<Casilla>();
		boolean [][] mcerrado = new boolean[terreno.length][terreno[0].length];
		
		while (!abierto.isEmpty()) {
			abierto.get(abierto.size()-1);
			
		}
		
		return abierto;
	}

	private ArrayList<Casilla> getVecinos(Casilla c) {
		Casilla cder = new Casilla(c.getX(),c.getY()+1);
		Casilla cizq = new Casilla(c.getX()+1,c.getY());
		Casilla cdiag = new Casilla(c.getX()+1,c.getY()+1);
		
		ArrayList<Casilla> rta = new ArrayList<Casilla>();
		
		if(!esObstaculo(cder))
			rta.add(cder);
		if(!esObstaculo(cizq))
			rta.add(cizq);
		if(!esObstaculo(cdiag))
			rta.add(cdiag);
		
		return rta;
	}
	
}
