package pr3.control;

import pr3.control.comandos.*;

/**
 * Clase encargada de parsear los comandos introducidos por el usuario.
 */
public class ParserComandos {
	private static Comando[] comandos = { new Ayuda(), new CrearCompleja(), new CrearSimple(), new EliminarCelula(),
			new Iniciar(), new Paso(), new Salir(), new Vaciar() };

	/**
	 * @return Devuelve un string con el texto que mostrará el comando ayuda
	 *         aprovechando la existencia de un array estatico de comandos del
	 *         cual podremos recopilar los textos de ayuda de cada comando.
	 */
	static public String ayudaComandos() {
		String ayuda = "";
		for (int i = 0; i < comandos.length; i++) {
			ayuda += comandos[i].textoAyuda();
		}
		return ayuda;
	}

	/**
	 * Dado un array de strings en el que cada posicion es una palabra
	 * introducida por el usuario, dedice de que comando se trata, si es que se
	 * trata de alguno y lo devuelev.
	 * 
	 * @param cadenas
	 *            array de strings en cuestion
	 * @return comando al que se refiere al array de strings, si no se refiere
	 *         al ninguno, devolvera null
	 */
	static public Comando parseaComando(String[] cadenas) {
		Comando comando = null;
		boolean encontrado = false;
		int i = 0;
		while (i < comandos.length && !encontrado) {
			if (comandos[i].parsea(cadenas) != null) {
				encontrado = true;
			} else {
				i++;
			}
		}

		if (encontrado)
			comando = comandos[i];

		return comando;
	}
}
