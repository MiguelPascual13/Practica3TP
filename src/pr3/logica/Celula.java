package pr3.logica;

/**
 * Clase Abstracta usada para representar los diferentes tipos de celulas que
 * pueden vivir en el mundo.
 * 
 * Es importante notar que una celula no sabe por si misma donde se encutra ni
 * si se ha movido o no.
 * 
 * Cada celula tiene ecapsuladas sus reglas de movimiento por el mundo.
 * Incluyendose dentro de esto las reglas de reproduccion y muerte si las
 * hubiera.
 */
public abstract class Celula {
	/*-----ATRIBUTOS-----*/

	/*
	 * A dia de hoy este atributo determina totalmente el tipo de celula. Si se
	 * quieren añadir mas tipos de celula en el futuro se debe tener en cuenta
	 * que este atributo dejara de ser determinante.
	 */
	protected boolean esComestible;

	/*-----METODOS-----*/

	/*---GENERALES---*/
	/**
	 * Metodo en el que van encapsuladas las reglas de movimiento (asi como de
	 * muerte y reproduccion en su caso).
	 * 
	 * @return Devuelve la casilla del mundo a la que se movio la celula.
	 * 
	 *         NOTA: No se exactamente si tiene utilidad pasar una casilla aqui
	 *         en lugar de las coordenadas por separado.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);

	/*---AUXILIARES---
	 * No aplica a esta clase*/

	/*---SOBREESCRITURAS---*/
	/**
	 * Pasa por pantalla el simbolo caracteristico de cada tipo de celula.
	 */
	public abstract String toString();

	/*---GET---*/

	/**
	 * @return Devuelve la "comestibilidad" de la celula (booleano). NOTA: Es
	 *         posible que en el futuro se requiera de un tipo mas complejo para
	 *         representar la comestibilidad.
	 */
	public abstract boolean esComestible();

	/*---SET---
	 * No aplica a esta clase*/
}
