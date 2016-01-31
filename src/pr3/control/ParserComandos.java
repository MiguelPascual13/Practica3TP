package pr3.control;

import pr3.control.comandos.Ayuda;
import pr3.control.comandos.Cargar;
import pr3.control.comandos.Comando;
import pr3.control.comandos.CrearCelula;
import pr3.control.comandos.EliminarCelula;
import pr3.control.comandos.Guardar;
import pr3.control.comandos.Iniciar;
import pr3.control.comandos.Jugar;
import pr3.control.comandos.Paso;
import pr3.control.comandos.Salir;
import pr3.control.comandos.Vaciar;
import pr3.excepciones.ComandoNoExistente;
import pr3.excepciones.ErrorComando;

/**
 * 
 * Clase ParserComandos encargada de comprobar los comandos validos.
 *
 */

public class ParserComandos {
	private static Comando[] comandos = { new Ayuda(), new Cargar(), new CrearCelula(), new EliminarCelula(),
			new Guardar(), new Iniciar(), new Jugar(), new Paso(), new Salir(), new Vaciar() };

	/**
	 * Encargada de crear un string con todos los textos de ayuda de cada uno de
	 * los comandos.
	 * 
	 * @return Devuelve un string con los textos de ayuda de cada uno de los
	 *         comandos.
	 */

	static public String ayudaComandos() {
		String ayuda = "";
		for (int i = 0; i < comandos.length; i++) {
			ayuda += comandos[i].textoAyuda();
		}
		return ayuda;
	}

	/**
	 * Comprueba que el string cadenas contiene un comando valido.
	 * 
	 * @param cadenas
	 *            Parametro que contiene la cadena introducida por el usuario.
	 * @return Devuelve el comando correspondiente que coincida con la cadena de
	 *         caracteres pasada por parametro.
	 * @throws ErrorComando
	 *             Lanza una excepcion de error comando si uno de los comandos
	 *             que se desea comprobar presenta un error de ecritura o de
	 *             formato.
	 * @throws ComandoNoExistente
	 *             Lanza una excepcion de comando no existente si el comando que
	 *             desea parsear no existe.
	 */

	static public Comando parseaComando(String[] cadenas) throws ErrorComando, ComandoNoExistente {
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
		else
			throw new ComandoNoExistente();

		return comando;
	}
}
