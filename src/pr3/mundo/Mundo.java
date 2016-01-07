package pr3.mundo;

import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PosicionNoVacia;
import pr3.excepciones.PosicionVacia;
import pr3.logica.Casilla;
import pr3.logica.Celula;
import pr3.logica.Superficie;

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

	public void crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango, PosicionNoVacia {
		crearCelula(casilla.getFila(), casilla.getColumna(), celula);
	}

	public void crearCelula(int fila, int columna, Celula celula) throws IndicesFueraDeRango, PosicionNoVacia {
		if (superficie.esVacia(fila, columna)) {
			superficie.setCasilla(fila, columna, celula);
		} else
			throw new PosicionNoVacia();
	}

	public void eliminarCelula(Casilla casilla) throws IndicesFueraDeRango, PosicionVacia {
		this.eliminarCelula(casilla.getFila(), casilla.getColumna());
	}

	public void eliminarCelula(int fila, int columna) throws IndicesFueraDeRango, PosicionVacia {
		if (!superficie.esVacia(fila, columna)) {
			superficie.vaciarCasilla(fila, columna);
		} else
			throw new PosicionVacia();
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
			// La exccepción jamás se dará.
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
		for (int i = 0; i < superficie.getFilas(); i++) {
			for (int j = 0; j < superficie.getColumnas(); j++) {
				movidas[i][j] = false;
			}
		}
	}
}
