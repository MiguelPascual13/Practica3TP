package pr3.control.comandos;

import pr3.logica.Mundo;

/**
 * Clase abstracta de la que derivarán tantas clases hijas como comandos haya.
 */
public abstract class Comando {
	/**
	 * Ejecuta el comando sobre el mundo.
	 * 
	 * @param mundo
	 *            mundo sobre el que se ejecutara el comando.
	 */
	public abstract void ejecuta(Mundo mundo);

	/**
	 * Comprueba si un array de strings se refiere al comando concreto.
	 * 
	 * @param cadenaComando
	 *            array de strings en cuestion
	 * @return se decuelve a si mismo en caso de darse la conincidencia.
	 *         Devolverá null en caso contrario.
	 */
	public abstract Comando parsea(String[] cadenaComando);

	/**
	 * Muestra un texto de ayuda del comando concreto.
	 * 
	 * @return
	 */
	public abstract String textoAyuda();
}
