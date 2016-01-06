package pr3.logica;

public class Superficie {

	private int nf;
	private int nc;

	private Celula[][] tablero;

	/**
	 * Constructora que inicializa la superficie con el tamano indicado en los
	 * parametros
	 * 
	 * @param nf
	 *            numero de filas de la superficie
	 * @param nc
	 *            numero de columnas de la superfcie
	 */
	public Superficie(int nf, int nc) {
		this.nf = nf;
		this.nc = nc;
		tablero = new Celula[this.nf][this.nc];
		vaciar();
	}

	/**
	 * Vacia la superfcie dejandola a null
	 */
	public void vaciar() {
		for (int i = 0; i < nf; i++) {
			for (int j = 0; j < nc; j++) {
				tablero[i][j] = null;
			}
		}
	}

	/**
	 * dada una casilla de origen, se realizan los cambios adecuados en la
	 * superficie para que esta se mueva segun sus reglas.
	 * 
	 * @param casilla casilla de origen
	 * @return devuelve la casilla de destino, o null en caso de no moverse.
	 */
	public Casilla ejecutaMovimiento(Casilla casilla) {
		Casilla destino = this.tablero[casilla.getFila()][casilla.getColumna()].ejecutaMovimiento(casilla, this);
		return destino;
	}

	public int getFilas() {
		return this.nf;
	}

	public int getColumnas() {
		return this.nc;
	}

	public boolean getComestibilidad(Casilla casilla) {
		return tablero[casilla.getFila()][casilla.getColumna()].esComestible();
	}
	
	/**
	 * Devuelve un booleano indicando si la casilla se encuentra vacia o no
	 * @param casilla casilla en cuestion
	 * @return true si vacia, false si llena
	 */
	public boolean esVacia(Casilla casilla) {
		boolean vacia = false;
		if (tablero[casilla.getFila()][casilla.getColumna()] == null)
			vacia = true;
		return vacia;
	}
	
	/**
	 * Vacia la casilla especificada por parametro
	 * @param casilla casilla a vaciar
	 */
	public void vaciarCasilla(Casilla casilla) {
		this.tablero[casilla.getFila()][casilla.getColumna()] = null;
	}
	
	/**
	 * Echufa una celula en la caasilla determinada por parametro
	 * @param casilla casilla en cuestion
	 * @param celula celula en cuestion
	 */
	public void setCasilla(Casilla casilla, Celula celula) {
		this.tablero[casilla.getFila()][casilla.getColumna()] = celula;
	}

	/**
	 * System.out.println(superficie) >>>> matriz 3 x 3 con la informacion de la
	 * superficie.
	 */
	public String toString() {
		String superficie = "";
		Casilla casilla = new Casilla(0, 0);
		for (int i = 0; i < this.getFilas(); i++) {
			for (int j = 0; j < this.getColumnas(); j++) {
				casilla.setFila(i);
				casilla.setColumna(j);
				if (this.esVacia(casilla))
					superficie += "-";
				else
					superficie += this.tablero[i][j].toString();
				superficie += '\t';
			}
			superficie += '\n';
		}
		return superficie;
	}
}
