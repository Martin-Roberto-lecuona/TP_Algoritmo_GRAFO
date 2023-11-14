package UserInterface;

import java.util.ArrayList;

import entitis.Casilla;
import entitis.FilesMapa;
import entitis.Mapa;

public class Main {

	public static void main(String[] args) {
		//Double[][] mapaMatrix = FilesMapa.lecturaArchivo("./casosDePrueba/in/mapaSinSalida.csv", ";");
		Double[][] mapaMatrix = FilesMapa.lecturaArchivo("./casosDePrueba/in/mapaEjemploClase.csv", ";");
		
		Mapa m = new Mapa(mapaMatrix);
		m.printMapa();

		Casilla orig = new Casilla(1, 0);
		Casilla dest = new Casilla(2,3);
		ArrayList<Casilla> camino = m.camino(orig, dest);
		System.out.println("Camino: ");
		m.printCamino(camino);
	}

}
