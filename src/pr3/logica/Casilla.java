package pr3.logica;

/**
 * Clase utilizada para determinar totalmente la posicion de una celula en el
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

	public void setFila(int fila) {
		
	}
	
	public void setColumna(int columna) {
		
	}
}
