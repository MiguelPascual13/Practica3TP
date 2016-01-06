package pr3.logica;

/**
 * Clase con todas las utilidades necesarias para consultar, modificar
 * (ATENCION: Clase Mutable) e imprimir por pantalla coordenadas cartesianas de
 * dos componentes.
 */
public class Casilla {

	/*-----ATRIBUTOS-----*/
	private int f;
	private int c;

	/*-----METODOS-----*/

	/*-----CONSTRUCTORAS-----*/
	/**
	 * Constructora (unica) con parametros para inicializar los atributos de la
	 * clase.
	 * 
	 * @param f
	 *            fila de la casilla.
	 * @param c
	 *            columna de la casilla.
	 */
	public Casilla(int f, int c) {
		this.f = f;
		this.c = c;
	}

	/*-----METODOS EN GENERAL-----*/
	/**
	 * System.out.println(casilla) >>>> (fila, columna)
	 */
	public String toString() {
		return "(" + this.f + "," + this.c + ")";
	}

	/*-----METODOS GET-----*/

	/**
	 * @return devuelve la fila de la casilla.
	 */
	public int getFila() {
		return this.f;
	}

	/**
	 * @return devuelve la columna de la casilla.
	 */
	public int getColumna() {
		return this.c;
	}

	/*-----METODOS SET-----*/

	/**
	 * Cambia la fila de la casilla.
	 * 
	 * @param f
	 *            nueva fila de la casilla.
	 */
	public void setFila(int f) {
		this.f = f;
	}

	/**
	 * Cambia la columna de la casilla.
	 * 
	 * @param c
	 *            nueva columna de la casilla.
	 */
	public void setColumna(int c) {
		this.c = c;
	}
}
