package pr3.control.comandos;

import pr3.control.Controlador;

public class Guardar implements Comando {

	private String nombreFichero;

	/**
	 * Guarda el mundo en juego, en un fichero.
	 */

	public void ejecuta(Controlador controlador) {
		System.out.println("Guardando mundo...");
		controlador.guardar(this.nombreFichero);
	}

	/**
	 * Comprueba si la cadena coincide con el comando guardar.
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 2) {
			if (cadenaComando[0].equalsIgnoreCase("GUARDAR")) {
				this.nombreFichero = cadenaComando[1];
				comando = this;
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "GUARDAR nombFich:\t\tGuarda la Partida en un Fichero de Texto.\n";
	}

}
