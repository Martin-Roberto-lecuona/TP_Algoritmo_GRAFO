package entitis;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class FilesMapa {
	private static final String EOL = "\n";

	public static Double[][] lecturaArchivo(String archivo, String separatedValue) {
		Scanner scanner = null;
		String linea;
		try {
			File file = new File(archivo);
			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);
			linea = scanner.next();
			String[] datos = linea.split(separatedValue);
			Double[][] matriz = new Double[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])];
			int j = 0;
			while (scanner.hasNext()) {
				linea = scanner.next();
				datos = linea.split(";");
				for (int i = 0; i < datos.length; i++) {
					try {
						if (Integer.parseInt(datos[i]) == 0)
							matriz[j][i] = Double.POSITIVE_INFINITY;
						else
							matriz[j][i] = Double.parseDouble(datos[i]);
					} catch (NumberFormatException e) {
						matriz[j][i] = Double.POSITIVE_INFINITY;
					}
				}
				j++;
			}

			return matriz;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return null;
	}
}
