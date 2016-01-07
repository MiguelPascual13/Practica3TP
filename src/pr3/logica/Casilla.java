package pr3.logica;

public class Casilla {
	private int fila;
	private int columna;
	
	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * Devuelve un String con las coordenadas en formato (f,c). No incluye salto
	 * de linea.
	 */
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
