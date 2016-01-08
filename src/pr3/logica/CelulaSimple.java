package pr3.logica;

import java.io.FileReader;

import pr3.excepciones.IndicesFueraDeRango;

public class CelulaSimple implements Celula {

	private final int MAX_PASOS_SIN_MOVER = 3;
	private final int PASOS_REPRODUCCION = 3;
	private int pasosDados;
	private int pasosSinMover;
	private boolean esComestible;

	public CelulaSimple(int pasosDados, int pasosSinMover) {
		this.pasosDados = pasosDados;
		this.pasosSinMover = pasosSinMover;
		this.esComestible = true;
	}

	public CelulaSimple() {
		this.pasosDados = 0;
		this.pasosSinMover = 0;
		this.esComestible = true;
	}

	public String toString() {
		return "X";
	}

	public void cargar(FileReader entrada) {

	}

	public void guardar() {
	}

	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) {

		Casilla destino = null;
		VectorMov entorno = new VectorMov();
		try {
			for (int i = origen.getFila() - 1; i <= origen.getFila() + 1; i++) {
				for (int j = origen.getColumna() - 1; j <= origen.getColumna() + 1; j++) {
					Casilla posicionCandidata = new Casilla(i, j);
					if (i >= 0 && i < superficie.getFilas()) {
						if (j >= 0 && j < superficie.getColumnas()) {
							if (superficie.esVacia(posicionCandidata)) {
								entorno.setCeldaSiguiente(posicionCandidata);
							}
						}
					}
				}
			}

			this.pasosDados++;

			if (entorno.getContador() > 0) {
				int aleatorio = (int) (Math.random() * entorno.getContador());
				int f = entorno.getFila(aleatorio);
				int c = entorno.getColumna(aleatorio);
				destino = new Casilla(f, c);
				superficie.setCasilla(destino, this);
				superficie.vaciarCasilla(origen);
				System.out.println("Movimiento de celula simple de " + origen + " a " + destino);
				if (this.pasosDados % PASOS_REPRODUCCION == 0) {
					CelulaSimple hija = new CelulaSimple();
					superficie.setCasilla(origen, hija);
					System.out.println("Nace nueva celula simple en " + origen + " cuyo padre ha sido " + destino);
				}
			} else {
				this.pasosSinMover++;
				if (this.pasosSinMover >= MAX_PASOS_SIN_MOVER + 1) {
					superficie.vaciarCasilla(origen);
					System.out.println("Muere la celula simple de la casilla " + origen + " por inactividad");
				} else if (this.pasosDados % PASOS_REPRODUCCION == 0) {
					System.out.println("Muere la celula simple de la casilla " + origen + " por irreproducibilidad");
					superficie.vaciarCasilla(origen);
				}
			}
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
		return destino;
	}

	public boolean esComestible() {
		return esComestible;
	}
}
