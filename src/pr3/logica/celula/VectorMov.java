package pr3.logica.celula;

import pr3.logica.Casilla;

public class VectorMov {

	private Casilla[] entorno;
	private int contador;
	private final int MAX_ENTORNO = 8;

	public VectorMov() {
		contador = 0;
		entorno = new Casilla[MAX_ENTORNO];
	}

	public boolean noVacio() {
		return this.contador > 0;
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

	public void setCeldaSiguiente(int fila, int columna) {
		this.entorno[contador++] = new Casilla(fila, columna);
	}
}