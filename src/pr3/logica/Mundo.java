package pr3.logica;

public abstract class Mundo {
	private int filas;
	private int columnas;
	private final int NUM_CEL_SIMPLES = 3;
	private final int NUM_CEL_COMPLEJAS = 2;
	private Superficie superficie;

	
	public Mundo(){
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
	}
	
	public Mundo(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		superficie = new Superficie(this.filas, this.columnas);
		this.inicializaMundo();
	}

	public abstract void inicializaMundo();
	
	public boolean guardar()
	{
		return false;
	}
	
	public boolean cargar()
	{
		return true;
	}
	
	public void inicializarSuperficie(int numCelulas, Celula celula) {
		Casilla casilla = new Casilla(0, 0);
		for (int i = 0; i < numCelulas; i++) {
			int f = (int) (Math.random() * superficie.getFilas());
			int c = (int) (Math.random() * superficie.getColumnas());
			casilla.setFila(f);
			casilla.setColumna(c);

			while (!crearCelula(casilla, celula)) {
				f = (int) (Math.random() * superficie.getFilas());
				c = (int) (Math.random() * superficie.getColumnas());
				casilla.setFila(f);
				casilla.setColumna(c);
			}
		}
	}

	/**
	 * Vacia la superficie del mundo.
	 */
	public void vaciar() {
		superficie.vaciar();
	}

	/**
	 * System.out.println(mundo) >>>> matriz nf x nc con la informacion del
	 * mundo
	 */
	public String toString() {
		return superficie.toString();
	}

	/**
	 * Trata de crear una celula en una casilla vacia (IMPORTANTE)
	 * 
	 * @param casilla
	 *            lugar donde se pretende enchufar la celula
	 * @param celula
	 *            celula que se pretende enchufar
	 * @return exito o fracaso de la operacion
	 */
	public boolean crearCelula(Casilla casilla, Celula celula) {
		boolean sinErrores = false;
		if (casilla.getFila() >= 0 && casilla.getFila() < superficie.getFilas()) {
			if (casilla.getFila() >= 0 && casilla.getColumna() < superficie.getColumnas()) {
				if (superficie.esVacia(casilla)) {
					sinErrores = true;
					superficie.setCasilla(casilla, celula);
				}
			}
		}
		return sinErrores;
	}

	/**
	 * Trata de eliminar una celula existente (IMPORTANTE) en una casilla
	 * determinada del tablero.
	 * 
	 * @param casilla
	 *            posicion en la que se encuentra la casilla a eliminar.
	 * @return devuelve un booleano que representa el exito o fracaso al
	 *         realizar la operacion.
	 */
	public boolean eliminarCelula(Casilla casilla) {
		boolean sinErrores = false;
		if (casilla.getFila() >= 0 && casilla.getFila() < superficie.getFilas()) {
			if (casilla.getColumna() >= 0 && casilla.getColumna() < superficie.getColumnas()) {
				if (!superficie.esVacia(casilla)) {
					superficie.vaciarCasilla(casilla);
					sinErrores = true;
				}
			}
		}
		return sinErrores;
	}

	/**
	 * Trata de mover todas las casillas no vacias y que no se hallan movido
	 * todavia.
	 */
	public void evoluciona() {
		boolean[][] movidas = new boolean[superficie.getFilas()][superficie.getColumnas()];
		inicializarMovidas(movidas);
		for (int i = 0; i < superficie.getFilas(); i++) {
			for (int j = 0; j < superficie.getColumnas(); j++) {
				Casilla casilla = new Casilla(i, j);
				if (!superficie.esVacia(casilla) && !movidas[casilla.getFila()][casilla.getColumna()]) {
					moverCelula(casilla, movidas);
				}
			}
		}
		System.out.println();
	}

	/**
	 * Actualiza la amtriz de booleanos de las casillas que ya se han movido.
	 * 
	 * @param casilla
	 *            casilla de origen de la celula que se acaba de intentar mover.
	 * @param movidas
	 *            matriz de booleanos-
	 */
	public void moverCelula(Casilla casilla, boolean movidas[][]) {
		Casilla destino = superficie.ejecutaMovimiento(casilla);
		if (destino != null)
			movidas[destino.getFila()][destino.getColumna()] = true;
	}

	/**
	 * Inicializa la matriz de booleanos que controla el movimiento del mundo a
	 * false.
	 * 
	 * @param movidas
	 *            matriz de booleanos.
	 */
	private void inicializarMovidas(boolean movidas[][]) {
		for (int i = 0; i < superficie.getFilas(); i++) {
			for (int j = 0; j < superficie.getColumnas(); j++) {
				movidas[i][j] = false;
			}
		}
	}
}
