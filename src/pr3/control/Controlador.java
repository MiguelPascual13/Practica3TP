package pr3.control;

import java.util.Scanner;

import pr3.control.comandos.Comando;
import pr3.logica.Mundo;

/**
 * Clase encarga de coordinar la simulacion del mundo con las ordenes del
 * usuario
 */
public class Controlador {
	private Mundo mundo;
	private Scanner in;

	/**
	 * Constructora con parametros.
	 * 
	 * @param mundo
	 *            mundo sobre el que se realizaran las simulaciones.
	 * @param in
	 *            flujo de entrada.
	 */
	public Controlador(Mundo mundo, Scanner in) {
		this.mundo = mundo;
		this.in = in;
	}

	/**
	 * Capta comandos del usuario y los ejecuta sobre el mundo hasta que la
	 * simulacion finaliza.
	 */
	public void realizaSilulacion() {
		System.out.println("Bienvenido al mundo celular...");
		System.out.println(mundo);
		String[] words = captarEntrada();
		while (!mundo.getSimulacionTerminada()) {
			Comando comando = ParserComandos.parseaComando(words);
			if (comando != null) {
				comando.ejecuta(mundo);
			} else {
				System.out.println("ERROR: Comando no existente.");
			}
			if (!mundo.getSimulacionTerminada()) {
				System.out.println(mundo);
				words = captarEntrada();
			}
		}
		System.out.println("Fin de la simulacion...");
	}

	private String[] captarEntrada() {
		String[] words = null;
		System.out.print("Comando > ");
		String entrada = in.nextLine();
		entrada = entrada.toUpperCase();
		words = entrada.split(" ");
		return words;
	}
}
