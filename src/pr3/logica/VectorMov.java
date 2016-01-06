package pr3.logica;

/**
 * Clase que contiene todas las utilidades necesarias para manejar un array
 * unidimiensional de casillas que represente las posiciones a las que una
 * celula simple se podra mover.
 *
 */
public class VectorMov {

	private Casilla[] entorno;
	private int contador;
	private final int MAX_ENTORNO = 8;

	/**
	 * Constructor inicializa un array de casillas de 8 posiciones.
	 */
	public VectorMov() {
		contador = 0;
		entorno = new Casilla[MAX_ENTORNO];
		vaciar();
	}

	/**
	 * Vacia el array.
	 */
	private void vaciar() {
		for (int i = 0; i < MAX_ENTORNO; i++) {
			entorno[i] = null;
		}
	}

	public int getContador() {
		return this.contador;
	}

	public int getFila(int indice) {
		return this.entorno[indice].getFila();
	}

	public int getColumna(int indice) {
		return this.entorno[indice].getColumna();
	}

	public void setCeldaSiguiente(Casilla celda) {
		this.entorno[contador++] = celda;
	}
}