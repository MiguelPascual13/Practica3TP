package pr3.logica;

/**
 * Clase (MUTABLE) utilizada para determinar totalmente la posicion de una celula en el
 * mundo mediante un sistema de coordenadas cartesianas discretas
 * (bidimiensional).
 */
public class Casilla {
	private int fila;
	private int columna;

	/**
	 * Constructora (unica) que asigna las coordenadas especificadas por
	 * parametro.
	 * 
	 * @param fila
	 *            Primera coordenada.
	 * @param columna
	 *            Segunda coordena.
	 */
	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * @return Devuelve la primera coordenada de la casilla.
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * @return Devuelve la segunda coordenada de la casilla.
	 */
	public int getColumna() {
		return this.columna;
	}
	
	/**
	 * Mofifica la primera coordenada de la casilla.
	 * @param fila Nuevo valor de la primera coordenada.
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	/**
	 * Modifica la segunda coordenada de la casilla.
	 * @param columna Nuevo valor de la segunda coordenada.
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}
}
