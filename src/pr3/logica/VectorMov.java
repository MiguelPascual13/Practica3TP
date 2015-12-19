package pr3.logica;

/**
 * NOTA: Meter esta clase en un paquete aparte junto con la celula simple para
 * que esta clase no sea visible. Clase auxiliar. Se trata de un array que
 * guarda las posibles casillas a las que se puede mover una celula simple.
 * Contiene metodos para insertar nuevas posiciones candidatas.
 */
public class VectorMov {
	Casilla[] entorno;
	private static final int MAX_ENTORNO = 8;
	int contador;

	/**
	 * Constructora unica sin parametros.
	 */
	public VectorMov() {
		this.entorno = new Casilla[MAX_ENTORNO];
		this.contador = 0;
	}

	/**
	 * Inserta una casilla en la ultima posicion libre del array. ATENCION: No
	 * se comprueban desbordamientos, esa es responsabilidad del llamante.
	 * 
	 * @param fila
	 *            Primera coordenada de la casilla en cuestion.
	 * @param columna
	 *            Segunda coordenada de la casilla en cuestion.
	 */
	public void setCeldaSiguiente(int fila, int columna) {
		this.entorno[contador++] = new Casilla(fila, columna);
	}

	/**
	 * @return Devuelve el contador del array.
	 */
	public int getContador() {
		return this.contador;
	}

	/**
	 * @param indice
	 *            Indice del array
	 * @return Devuelve la primera coordenada de la casilla en el indice
	 *         indicado.
	 */
	public int getFila(int indice) {
		return this.entorno[indice].getFila();
	}

	/**
	 * @param indice
	 *            Indice del array
	 * @return Devuelve la segunda coordenada de la casilla en el indice
	 *         indicado.
	 */
	public int getColumna(int indice) {
		return this.entorno[indice].getColumna();
	}
}
