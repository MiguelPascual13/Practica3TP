package pr3.logica.celula;

import java.io.FileWriter;
import java.io.IOException;

import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.Casilla;
import pr3.logica.Superficie;

/**
 * 
 * Clase celula compleja que implementa a la clase celula. Se encarga de
 * gestionar todo el tema relacionado con las leyes que rigen a una celula
 * compleja.
 *
 */

public class CelulaCompleja implements Celula {
	public static final int MAX_COMIDAS = 3;
	private int celulasComidas;
	private boolean esComestible;

	/**
	 * Contructora para celula compleja asignandole un valor a celulas comidas y
	 * tambien indicar que no es comestible.
	 */

	public CelulaCompleja() {
		this.celulasComidas = 0;
		this.esComestible = false;
	}

	public String toString() {
		return "*";
	}

	/**
	 * Se encarga de cargar del fichero el dato correspondiente a una celula
	 * compleja.
	 */

	public void cargar(String[] cadenaLinea) throws FormatoNumericoIncorrecto {
		try {
			this.celulasComidas = Integer.parseInt(cadenaLinea[3]);
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
	}

	/**
	 * Se encarga de guardar en el fichero el dato correspondiente a una celula
	 * compleja.
	 */

	public void guardar(FileWriter fich) throws IOException {
		fich.write("compleja " + this.celulasComidas + System.getProperty("line.separator"));
	}

	public boolean esComestible() {
		return this.esComestible;
	}

	/**
	 * Se encarga de gestionar todo el movimiento de una celula compleja, recibe
	 * una casilla origen por parametro y si se puede mover se mueve.
	 */

	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) throws IndicesFueraDeRango {
		Casilla destino = null;
		boolean come = false;
		boolean explota = false;
		boolean mueve = false;
		int fila = (int) (Math.random() * superficie.getFilas());
		int columna = (int) (Math.random() * superficie.getColumnas());
		if (mueve = superficie.esVacia(fila, columna)) {
			destino = new Casilla(fila, columna);
			this.movimiento(origen, destino, superficie);
		} else {
			if (come = superficie.getComestibilidad(fila, columna)) {
				destino = new Casilla(fila, columna);
				this.movimiento(origen, destino, superficie);
				mueve = true;
				this.aumentarComidas();
			}
			if ((explota = this.celulasComidas >= MAX_COMIDAS) && mueve) {
				this.muerte(destino, superficie);
			}
		}
		this.mensajes(origen, destino, come, explota, mueve);
		return destino;
	}

	private void movimiento(Casilla origen, Casilla destino, Superficie superficie) throws IndicesFueraDeRango {
		superficie.setCasilla(destino, this);
		superficie.vaciarCasilla(origen);
	}

	private void muerte(Casilla casilla, Superficie superficie) throws IndicesFueraDeRango {
		superficie.vaciarCasilla(casilla);
	}

	private void aumentarComidas() {
		this.celulasComidas++;
	}

	private void mensajes(Casilla origen, Casilla destino, boolean come, boolean explota, boolean mueve) {
		if (mueve) {
			System.out.println("COMPLEJA de " + origen + " a " + destino);
			if (come) {
				System.out.println("COME");
				if (explota)
					System.out.println("EXPLOTA");
				else
					System.out.println("NO EXPLOTA");
			} else
				System.out.println("NO COME");
		} else
			System.out.println("COMPLEJA de " + origen + " NO SE MUEVE");
	}
}
