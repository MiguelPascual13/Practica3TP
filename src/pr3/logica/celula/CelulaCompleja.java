package pr3.logica.celula;

import java.io.FileReader;

import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.Casilla;
import pr3.logica.Superficie;

public class CelulaCompleja implements Celula {
	private final int MAX_COMIDAS = 3;
	private int celulasComidas;
	private boolean esComestible;

	public CelulaCompleja(int celulasComidas) {
		this.celulasComidas = celulasComidas;
	}

	public CelulaCompleja() {
		this.celulasComidas = 0;
	}

	public String toString() {
		return "*";
	}

	public void cargar(FileReader entrada) {

	}

	public void guardar() {
	}

	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) {
		Casilla destino = null;
		try {
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
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
		return destino;
	}

	public boolean esComestible() {
		return this.esComestible;
	}
}
