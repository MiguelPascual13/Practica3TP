package pr3.logica.celula;

import java.io.FileWriter;
import java.io.IOException;

import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.Casilla;
import pr3.logica.Superficie;

public class CelulaSimple implements Celula {

	public static final int MAX_PASOS_SIN_MOVER = 3;
	public static final int PASOS_REPRODUCCION = 3;
	private int pasosDados;
	private int pasosSinMover;
	private boolean esComestible;

	public CelulaSimple() {
		this.pasosDados = 0;
		this.pasosSinMover = 0;
		this.esComestible = true;
	}

	public String toString() {
		return "X";
	}

	public void cargar(String[] cadenaLinea) throws FormatoNumericoIncorrecto {
		try {
			this.pasosDados = Integer.parseInt(cadenaLinea[3]);
			this.pasosSinMover = Integer.parseInt(cadenaLinea[4]);
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
	}

	public void guardar(FileWriter fich) throws IOException {
		fich.write("simple " + this.pasosDados + " " + this.pasosSinMover + "\n");
	}

	public boolean esComestible() {
		return esComestible;
	}

	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) {
		Casilla destino = null;
		VectorMov entorno = new VectorMov();
		boolean mueve = false;
		boolean reproduce = false;
		boolean muereInactividad = false;
		boolean muereIrreproducibilidad = false;
		try {
			this.seleccionarCandidatas(origen, superficie, entorno);
			this.aumentarPasosDados();
			if (mueve = this.seMueve(entorno)) {
				destino = this.elegirCandidata(entorno);
				this.movimiento(origen, destino, superficie);
				if (reproduce = this.seReproduce()) {
					this.reproduce(origen, superficie);
				}
			} else {
				this.aumentarPasosSinMover();
				if (muereInactividad = this.inactividadMaxima()) {
					this.muere(origen, superficie);
				} else if (muereIrreproducibilidad = this.seReproduce()) {
					this.muere(origen, superficie);
				}
			}
			this.mensajes(origen, destino, mueve, reproduce, muereInactividad, muereIrreproducibilidad);
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
		return destino;
	}

	private void aumentarPasosDados() {
		this.pasosDados++;
	}

	private void aumentarPasosSinMover() {
		this.pasosSinMover++;
	}

	private Casilla elegirCandidata(VectorMov entorno) {
		Casilla destino = null;
		int indiceAleatorio = (int) (Math.random() * entorno.getContador());
		int fila = entorno.getFila(indiceAleatorio);
		int columna = entorno.getColumna(indiceAleatorio);
		destino = new Casilla(fila, columna);
		return destino;
	}

	private void movimiento(Casilla origen, Casilla destino, Superficie superficie) throws IndicesFueraDeRango {
		superficie.setCasilla(destino, this);
		superficie.vaciarCasilla(origen);
	}

	private void reproduce(Casilla casilla, Superficie superficie) throws IndicesFueraDeRango {
		superficie.setCasilla(casilla, new CelulaSimple());
	}

	private void muere(Casilla casilla, Superficie superficie) throws IndicesFueraDeRango {
		superficie.vaciarCasilla(casilla);
	}

	private void seleccionarCandidatas(Casilla origen, Superficie superficie, VectorMov entorno)
			throws IndicesFueraDeRango {
		for (int i = origen.getFila() - 1; i <= origen.getFila() + 1; i++) {
			for (int j = origen.getColumna() - 1; j <= origen.getColumna() + 1; j++) {
				if (superficie.enRango(i, j) && superficie.esVacia(i, j)) {
					entorno.setCeldaSiguiente(i, j);
				}
			}
		}
	}

	private boolean seMueve(VectorMov entorno) {
		return entorno.noVacio();
	}

	private boolean seReproduce() {
		return this.pasosDados % PASOS_REPRODUCCION == 0;
	}

	private boolean inactividadMaxima() {
		return this.pasosSinMover >= MAX_PASOS_SIN_MOVER + 1;
	}

	private void mensajes(Casilla origen, Casilla destino, boolean mueve, boolean reproduce, boolean muereInactividad,
			boolean muereIrreproducibilidad) {
		if (mueve) {
			System.out.println("SIMPLE de " + origen + " a " + destino);
			if (reproduce)
				System.out.println("NACE SIMPLE EN " + origen + " (PADRE " + destino + " )");
		} else {
			System.out.println("SIMPLE de " + origen + " NO SE MUEVE");
			if (muereInactividad)
				System.out.println("MUERE SIMPLE EN " + origen + " POR INACTIVIDAD");
			else if (muereIrreproducibilidad)
				System.out.println("MUERE SIMPLE EN " + origen + " POR IRREPRODUCIBILIDAD");
		}
	}
}
