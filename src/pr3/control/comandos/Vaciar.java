package pr3.control.comandos;

import pr3.control.Controlador;

public class Vaciar implements Comando {
	
	/**
	 * Vacia el mundo mediante el controlador. 
	 */

	public void ejecuta(Controlador controlador) {
		System.out.println("Vaciando mundo...");
		controlador.vaciar();
	}
	
	/**
	 * Comprueba si la cadena coincide con el comando vaciar. 
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("VACIAR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "VACIAR:\t\t\tVacia el mundo.\n";
	}

}
