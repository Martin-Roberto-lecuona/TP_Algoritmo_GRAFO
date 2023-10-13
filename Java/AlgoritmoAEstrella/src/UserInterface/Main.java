package UserInterface;

import java.util.ArrayList;

import entitis.Casilla;
import entitis.FilesMapa;
import entitis.Mapa;

public class Main {

	public static void main(String[] args) {
		Double[][] mapaMatrix = FilesMapa.lecturaArchivo("./casosDePrueba/in/mapaBIG2.csv", ";");
		Mapa m = new Mapa(mapaMatrix);
		m.printMapa();

		Casilla orig = new Casilla(0, 0);
		Casilla dest = new Casilla(11,11);
		ArrayList<Casilla> camino = m.camino(orig, dest);
		System.out.println("Camino: ");
		m.printCamino(camino);
		for (Casilla x : camino) {
			System.out.println(x + " padre " + x.getpadre() + " -> ");
		}
	}

}
