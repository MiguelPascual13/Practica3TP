package pr3.logica;

import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.celula.Celula;

public class Superficie {

	private int filas;
	private int columnas;

	private Celula[][] tablero;

	private void enRango(int fila, int columna) throws IndicesFueraDeRango {
		if (fila < 0 || fila >= this.filas)
			throw new IndicesFueraDeRango();
		else if (columna < 0 || columna >= this.columnas)
			throw new IndicesFueraDeRango();
	}

	public Superficie(int nf, int nc) {
		this.filas = nf;
		this.columnas = nc;
		tablero = new Celula[this.filas][this.columnas];
		vaciar();
	}

	public void vaciar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = null;
			}
		}
	}

	public Casilla ejecutaMovimiento(Casilla casilla) {
		Casilla destino = this.tablero[casilla.getFila()][casilla.getColumna()].ejecutaMovimiento(casilla, this);
		return destino;
	}

	public int getFilas() {
		return this.filas;
	}

	public int getColumnas() {
		return this.columnas;
	}

	public boolean getComestibilidad(Casilla casilla) throws IndicesFueraDeRango {
		return this.getComestibilidad(casilla.getFila(), casilla.getColumna());
	}

	public boolean getComestibilidad(int fila, int columna) throws IndicesFueraDeRango {
		this.enRango(fila, columna);
		return tablero[fila][columna].esComestible();
	}

	public boolean esVacia(Casilla casilla) throws IndicesFueraDeRango {
		return this.esVacia(casilla.getFila(), casilla.getColumna());
	}

	public boolean esVacia(int fila, int columna) throws IndicesFueraDeRango {
		this.enRango(fila, columna);
		boolean vacia = false;
		if (tablero[fila][columna] == null)
			vacia = true;
		return vacia;
	}

	public void vaciarCasilla(Casilla casilla) throws IndicesFueraDeRango {
		this.vaciarCasilla(casilla.getFila(), casilla.getColumna());
	}

	public void vaciarCasilla(int fila, int columna) throws IndicesFueraDeRango {
		this.enRango(fila, columna);
		this.tablero[fila][columna] = null;
	}

	public void setCasilla(Casilla casilla, Celula celula) throws IndicesFueraDeRango {
		this.setCasilla(casilla.getFila(), casilla.getColumna(), celula);
	}

	public void setCasilla(int fila, int columna, Celula celula) throws IndicesFueraDeRango {
		this.enRango(fila, columna);
		this.tablero[fila][columna] = celula;
	}

	public String toString() {
		String superficie = "";
		try {
			for (int i = 0; i < this.getFilas(); i++) {
				for (int j = 0; j < this.getColumnas(); j++) {
					if (this.esVacia(i, j))
						superficie += "-";
					else
						superficie += this.tablero[i][j].toString();
					superficie += '\t';
				}
				superficie += '\n';
			}
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
		return superficie;
	}

	public boolean cargar() {
		return false;
	}

	public boolean guardar() {
		return false;
	}
}
