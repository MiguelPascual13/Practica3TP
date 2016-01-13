package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.control.ParserComandos;

/**
 * Comando de ayuda.
 */

public class Ayuda implements Comando {
	
	/**
	 * Muestra por tantalla los textos de ayuda de todos los comandos
	 * existentes.
	 */
	
	public void ejecuta(Controlador controlador) {
		System.out.println(ParserComandos.ayudaComandos());
	}

	/**
	 * Comprueba si la cadena coincide con el comando ayuda. 
	 */
	
	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("AYUDA"))
			comando = this;
		return comando;
	}
	

	public String textoAyuda() {
		return "\nAYUDA:\tBreve descripcion del efecto de los comandos.\n";
	}
}
