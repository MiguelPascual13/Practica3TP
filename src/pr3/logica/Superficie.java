package pr3.logica;

/**
 * Clase que sirve para manejar el array bidimensional que representa el mundo
 * celular. Sirve para consultar y modificar los datos de dicho array. Sin
 * embargo, esta clase no tiene conocimiento alguno sobre las "reglas de vida"
 * del mundo.
 */
public class Superficie {
	/*
	 * Array bidimensional del celulas que conformaran la superficie del mundo
	 * celular
	 */
	private Celula[][] tablero;

	/* Estos atributos controlan la dimension del tablero */
	private int filas;
	private int columnas;

	/**
	 * Constructora (unica) cuyos parametros determinan la dimension del array
	 * bidimensional del tablero-
	 * 
	 * @param filas
	 *            Numero de filas del array.
	 * @param columnas
	 *            Numero de columnas del array.
	 */
	public Superficie(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		tablero = new Celula[this.filas][this.columnas];
		/*
		 * Aunque esto ya lo hace java por defecto, lo haremos aquí también por
		 * prudencia.
		 */
		this.vaciar();
	}

	/**
	 * Inicializa a null todas las casillas del tablero.
	 */
	public void vaciar() {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				vaciarCasilla(i, j);
			}
		}
	}

	/**
	 * Hace que la posicion indicada del tablero apunte a null.
	 * 
	 * @param casilla
	 *            Posicion en cuestion.
	 */
	public void vaciarCasilla(Casilla casilla) {
		this.vaciarCasilla(casilla.getFila(), casilla.getColumna());
	}

	/**
	 * Hace que la posicion indicada del tablero apunte a null.
	 * 
	 * @param f
	 *            Primera coordenada de la casilla.
	 * @param c
	 *            Segunda coordenada de la casilla.
	 */
	public void vaciarCasilla(int f, int c) {
		this.tablero[f][c] = null;
	}

	/**
	 * @return Devuelve el numero de filas del tablero.
	 */
	public int getFilas() {
		return this.filas;
	}

	/**
	 * @return Devuelve el numero de columnas del tablero.
	 */
	public int getColumnas() {
		return this.columnas;
	}

	/**
	 * Funcion caracteristica del conjunto de las casillas vacias del tablero.
	 * 
	 * @param i
	 *            Primera coordenada de la casilla.
	 * @param j
	 *            Segunda coordenada de la casilla.
	 * @return Devuelve true si la casilla es vacia, false en caso contrario.
	 */
	public boolean esVacia(int i, int j) {
		boolean vacia = false;
		if (this.tablero[i][j] == null)
			vacia = true;
		return vacia;
	}
	
	/**
	 * Inserta una celula dada en una casilla del tablero especificada.
	 * ATENCION: No se encarga de comprobar que la posicion sea valida.
	 * @param casilla Casilla en cuestion.
	 * @param celula Celula en cuestion.
	 */
	public void insertarCelula(Casilla casilla, Celula celula) {
		insertarCelula(casilla.getFila(), casilla.getColumna(), celula);
	}
	
	/**
	 * Inserta un a celula dada en la casilla del tablero especificada.
	 * ATENCION: No se encarga de comporbar que la posicion sea valida.
	 * @param f Fila de la casilla.
	 * @param c Columna de la casilla.
	 * @param celula Celula a Insertar
	 */
	public void insertarCelula(int f, int c, Celula celula) {
		this.tablero[f][c] = celula;
	}
}
