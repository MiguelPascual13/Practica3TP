package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.IndicesFueraDeRango;

public class Paso implements Comando {

	/**
	 * Realiza un paso de vida en el mundo que se esta ejecutando en el
	 * controlador.
	 */

	public void ejecuta(Controlador controlador) throws IndicesFueraDeRango {
		System.out.println("Evolucionando...");
		controlador.evoluciona();
	}

	/**
	 * Comprueba si la cadena coincide con el comando paso.
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("PASO"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "PASO:\t\t\tRealiza una evolucion en el mundo.\n";
	}

}
