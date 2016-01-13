package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorComando;

/**
 * Clase abstracta de la que derivarán tantas clases hijas como comandos haya.
 */

public interface Comando {
	
	/**
	 * Ejecuta el comando sobre controlador. 
	 * @param controlador
	 * Controlador sobre el que se ejecutara el comando. 
	 */
	
	public abstract void ejecuta(Controlador controlador);
	
	/**
	 * Comprueba si un array de strings se refiere al comando concreto.
	 * 
	 * @param cadenaComando
	 * Array de strings en cuestion
	 * @return 
	 * Se decuelve a si mismo en caso de darse la conincidencia.
	 * Devolverá null en caso contrario.
	 * @throws ErrorComando
	 * Lanza una excepcion de error comando en caso de tener un fallo de formato. 
	 */

	public abstract Comando parsea(String[] cadenaComando) throws ErrorComando;
	
	/**
	 * Muestra un texto de ayuda del comando concreto.
	 * 
	 * @return
	 */

	public abstract String textoAyuda();
}
