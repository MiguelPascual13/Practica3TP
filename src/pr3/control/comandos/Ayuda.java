package pr3.control.comandos;

import pr3.control.ParserComandos;
import pr3.logica.Mundo;

/**
 * Comando de ayuda.
 */
public class Ayuda extends Comando {
	/**
	 * Muestra por tantalla los textos de ayuda de todos los comandos
	 * existentes.
	 */
	public void ejecuta(Mundo mundo) {
		System.out.println(ParserComandos.ayudaComandos());
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equals("AYUDA"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "\nAYUDA:\tBreve descripcion del efecto de los comandos.\n";
	}
}
