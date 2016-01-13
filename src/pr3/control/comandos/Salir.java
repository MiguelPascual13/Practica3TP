package pr3.control.comandos;

import pr3.control.Controlador;

public class Salir implements Comando {
	
	/**
	 * Modifica el booleano de simulacion terminada a true y de esta manera terminar con la simulacion. 
	 */
	
	public void ejecuta(Controlador controlador) {
		controlador.setSimulacionTerminada(true);
	}
	
	/**
	 * Comprueba si la cadena coincide con el comando salir. 
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("SALIR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "SALIR:\t\t\tFinaliza la Simulacion.\n";
	}
}
