package pr3.logica;

public class Superficie {

	private int nf;
	private int nc;

	private Celula[][] tablero;

	public Superficie(int nf, int nc) {
		this.nf = nf;
		this.nc = nc;
		tablero = new Celula[this.nf][this.nc];
		vaciar();
	}

	public void vaciar() {
		for (int i = 0; i < nf; i++) {
			for (int j = 0; j < nc; j++) {
				tablero[i][j] = null;
			}
		}
	}

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

	public boolean esVacia(Casilla casilla) {
		boolean vacia = false;
		if (tablero[casilla.getFila()][casilla.getColumna()] == null)
			vacia = true;
		return vacia;
	}

	public void vaciarCasilla(Casilla casilla) {
		this.tablero[casilla.getFila()][casilla.getColumna()] = null;
	}

	public void setCasilla(Casilla casilla, Celula celula) {
		this.tablero[casilla.getFila()][casilla.getColumna()] = celula;
	}

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

	public boolean cargar() {
		return false;
	}

	public boolean guardar() {
		return false;
	}
}
