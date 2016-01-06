package pr3.logica;

/**
 * Clase abstracta que representa a uno de los posibles tipos de entidades que
 * pueden habitar el mundo. Se encarga de consultar y modificar (ATENCION: Clase
 * Mutable) las caracteristicas fundamentales de una celula. También se encarga
 * de la impresion de una celula por pantalla asi como de su movimiento a traves
 * del tablero del mundo.
 */
public abstract class Celula {

	/*-----ATRIBUTOS-----*/
	/**
	 * Atributo protegido (para poder ser heredado) que indica si la celula a la
	 * que se refiere el objeto puede ser comida o no.
	 */
	protected boolean esComestible;

	/*-----METODOS EN GENERAL-----*/
	public abstract String toString();

	/**
	 * Mueve la celula por el tablero realizando en la tarea todo lo que ello
	 * conlleve, ya sea la muerte de la celula en cuestion, la muerte de otras
	 * celulas o bien el nacimiento de nuevas celulas.
	 * 
	 * @param casilla
	 *            casilla actual de la celula.
	 * @param superficie
	 *            tablero sobre el que las celulas se moveran.
	 * @return casilla a la que la celula se movio, en caso de no moverse
	 *         devolvera el puntero nulo.
	 */
	public abstract Casilla ejecutaMovimiento(Casilla casilla, Superficie superficie);

	/*-----METODOS GET-----*/
	/**
	 * @return devuelve la "comestibilidad" de la celula.
	 */
	public abstract boolean esComestible();
}
