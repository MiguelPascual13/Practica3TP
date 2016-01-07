package pr3.logica;

public class CelulaCompleja implements Celula {
	private final int MAX_COMIDAS = 3;
	private int celulasComidas;
	private boolean esComestible;

	public CelulaCompleja(int celulasComidas) {
		this.celulasComidas = celulasComidas;
	}

	public String toString() {
		return "*";
	}

	public boolean cargar() {
		return false;
	}

	public boolean guardar() {
		return false;
	}

	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) {
		Casilla destino = null;
		int fAleatoria = (int) (Math.random() * superficie.getFilas());
		int cAleatoria = (int) (Math.random() * superficie.getColumnas());
		destino = new Casilla(fAleatoria, cAleatoria);
		if (superficie.esVacia(destino)) {
			superficie.setCasilla(destino, this);
			superficie.vaciarCasilla(origen);
			System.out.println("Movimiento de celula compleja de " + origen + " a " + destino);
		} else {
			if (!superficie.getComestibilidad(destino))
				destino = null;
			else {
				this.celulasComidas++;
				superficie.setCasilla(destino, this);
				superficie.vaciarCasilla(origen);
				System.out.println("Movimiento de celula compleja de " + origen + " a " + destino);
				System.out.println("La celula simple de " + destino + " fue deglutida");
				if (this.celulasComidas >= MAX_COMIDAS) {
					System.out.println("La celula compleja de " + destino + " exploto");
					superficie.vaciarCasilla(destino);
				}
			}
		}
		return destino;
	}

	public boolean esComestible() {
		return this.esComestible;
	}
}
