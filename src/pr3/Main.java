package pr3;

import java.util.Scanner;

import pr3.control.Controlador;
import pr3.logica.Mundo;

/**
 * Clase principal.
 */
public class Main {
	/**
	 * Funcion principal
	 * 
	 * @param args
	 *            argumentos en desuso
	 */
	public static void main(String[] args) {
		Mundo mundo = new Mundo(3, 3);
		Scanner in = new Scanner(System.in);
		Controlador control = new Controlador(mundo, in);
		control.realizaSilulacion();
	}
}
