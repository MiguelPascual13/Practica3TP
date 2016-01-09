package pr3.logica.mundo;

import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.Casilla;
import pr3.logica.Superficie;
import pr3.logica.celula.Celula;

public abstract class Mundo {
	protected int filas;
	protected int columnas;
	protected Superficie superficie;

	public Mundo() {
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
	}

	public Mundo(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		superficie = new Superficie(this.filas, this.columnas);
	}

	public abstract void inicializaMundo();

	public boolean guardar() {
		return false;
	}

	public boolean cargar() {
		return true;
	}

	public void vaciar() {
		superficie.vaciar();
	}

	public String toString() {
		return superficie.toString();
	}

	public boolean crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango{
		return this.crearCelula(casilla.getFila(), casilla.getColumna(), celula);
	}

	public boolean crearCelula(int fila, int columna, Celula celula) throws IndicesFueraDeRango{
		boolean sinErrores = true;
		if (superficie.esVacia(fila, columna)) {
			superficie.setCasilla(fila, columna, celula);
		} else
			sinErrores = false;
		return sinErrores;
	}

	public boolean eliminarCelula(Casilla casilla) throws IndicesFueraDeRango {
		return this.eliminarCelula(casilla.getFila(), casilla.getColumna());
	}

	public boolean eliminarCelula(int fila, int columna) throws IndicesFueraDeRango {
		boolean sinErrores = true;
		if (!superficie.esVacia(fila, columna)) {
			superficie.vaciarCasilla(fila, columna);
		} else
			sinErrores = false;
		return sinErrores;
	}

	public void evoluciona() {
		try {
			boolean[][] movidas = new boolean[superficie.getFilas()][superficie.getColumnas()];
			inicializarMovidas(movidas);
			for (int i = 0; i < superficie.getFilas(); i++) {
				for (int j = 0; j < superficie.getColumnas(); j++) {
					Casilla casilla = new Casilla(i, j);
					if (!superficie.esVacia(i, j) && !movidas[i][j]) {
						moverCelula(casilla, movidas);
					}
				}
			}
			System.out.println();
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
	}

	public void moverCelula(Casilla casilla, boolean movidas[][]) {
		Casilla destino = superficie.ejecutaMovimiento(casilla);
		if (destino != null)
			movidas[destino.getFila()][destino.getColumna()] = true;
	}

	public void moverCelula(int fila, int columna, boolean movidas[][]) {
		Casilla casilla = new Casilla(fila, columna);
		this.moverCelula(casilla, movidas);
	}

	private void inicializarMovidas(boolean movidas[][]) {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				movidas[i][j] = false;
			}
		}
	}
}
