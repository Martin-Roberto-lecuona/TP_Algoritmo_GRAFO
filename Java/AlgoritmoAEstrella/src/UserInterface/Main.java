package UserInterface;

import java.util.ArrayList;

import entitis.Casilla;
import entitis.FilesMapa;
import entitis.Mapa;

public class Main {

	public static void main(String[] args) {
		Double[][] mapaMatrix = FilesMapa.lecturaArchivo("./casosDePrueba/in/mapa1.csv", ";");
		Mapa m = new Mapa(mapaMatrix);
		m.printMapa();
		
		Casilla orig = new Casilla(0,0);
		Casilla dest = new Casilla(4,2);
		ArrayList<Casilla> camino = m.camino(orig, dest);
		System.out.println("Camino: ");
		for (Casilla x : camino) {
			System.out.print(x + " -> ");
		}
	}

}