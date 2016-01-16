package pr3.logica;

/**
 * 
 * Clase casilla.
 *
 */

public class Casilla {
	private int fila;
	private int columna;

	/**
	 * Contructora de la clase casilla asignandoles los valores especificados
	 * por parametro.
	 * 
	 * @param fila
	 *            Parametro fila.
	 * @param columna
	 *            Parametro columna.
	 */

	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public String toString() {
		return "(" + this.fila + "," + this.columna + ")";
	}

	public int getFila() {
		return this.fila;
	}

	public int getColumna() {
		return this.columna;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
}
