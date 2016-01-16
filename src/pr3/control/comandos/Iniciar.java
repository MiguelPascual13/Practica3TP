package pr3.control.comandos;

import pr3.control.Controlador;

public class Iniciar implements Comando {

	/**
	 * Inicia el mundo en el controlador para comenzar a jugar en un nuevo
	 * tablero.
	 */

	public void ejecuta(Controlador controlador) {
		System.out.println("Inicializando mundo...");
		controlador.inicializar();
	}

	/**
	 * Comprueba si la cadena coincide con el comando iniciar.
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("INICIAR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "INICIAR:\t\tInicializa el mundo.\n";
	}

}
