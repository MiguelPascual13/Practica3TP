package pr3.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PalabraIncorrecta;
import pr3.logica.celula.Celula;
import pr3.logica.celula.CelulaCompleja;
import pr3.logica.celula.CelulaSimple;

/**
 * 
 * Clase superficie se encarga de gestionar todas la superficie del mundo que se
 * este jugando.
 *
 */

public class Superficie {

	private int filas;
	private int columnas;

	private Celula[][] tablero;

	/**
	 * Comprueba si la fila y la columna especificadas por parametro estan
	 * dentro del rango de la superficie.
	 * 
	 * @param fila
	 * @param columna
	 * @return Devuelve un booleano que indica si se han complido todas las
	 *         condiciones o no.
	 */

	public boolean enRango(int fila, int columna) {
		boolean sinErrores = true;
		if (fila < 0 || fila >= this.filas)
			sinErrores = false;
		else if (columna < 0 || columna >= this.columnas)
			sinErrores = false;
		return sinErrores;
	}

	/**
	 * Contructora de superficie, se le asignan las columnas y las filas y se
	 * crea el nuevo tablero asignandole a todas las casillas null.
	 * 
	 * @param filas
	 * @param columnas
	 */

	public Superficie(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		tablero = new Celula[this.filas][this.columnas];
		vaciar();
	}

	/**
	 * Se encarga de asignarle "nada" a todas las casillas del tablero.
	 */

	public void vaciar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = null;
			}
		}
	}

	/**
	 * Realiza el movimiento y obtiene el destino al que se ha movido una
	 * celula.
	 * 
	 * @param casilla
	 *            Contiene la casilla de origen.
	 * @return Devuelve la casilla de destino.
	 */

	public Casilla ejecutaMovimiento(Casilla casilla) {
		Casilla destino = this.tablero[casilla.getFila()][casilla.getColumna()]
				.ejecutaMovimiento(casilla, this);
		return destino;
	}

	public int getFilas() {
		return this.filas;
	}

	public int getColumnas() {
		return this.columnas;
	}

	/**
	 * Comprueba si la celula es comestible o no usando una funcion igual que
	 * llama pero con mas parametros.
	 * 
	 * @param casilla
	 * @return Devuelve el booleano correspondiente.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public boolean getComestibilidad(Casilla casilla)
			throws IndicesFueraDeRango {
		return this.getComestibilidad(casilla.getFila(), casilla.getColumna());
	}

	/**
	 * Comprueba si la celula es comestible o no.
	 * 
	 * @param fila
	 * @param columna
	 * @return Devuelve el booleano correspondiente.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public boolean getComestibilidad(int fila, int columna)
			throws IndicesFueraDeRango {
		if (!this.enRango(fila, columna))
			throw new IndicesFueraDeRango();
		return tablero[fila][columna].esComestible();
	}

	/**
	 * Comprueba si la casilla es vacia llamando a una subfuncion.
	 * 
	 * @param casilla
	 * @return Devuelve un booleano correspondiente.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public boolean esVacia(Casilla casilla) throws IndicesFueraDeRango {
		return this.esVacia(casilla.getFila(), casilla.getColumna());
	}

	/**
	 * Comprueba si la casilla es vacia.
	 * 
	 * @param fila
	 * @param columna
	 * @return Devuelve un booleano correspondiente.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public boolean esVacia(int fila, int columna) throws IndicesFueraDeRango {
		if (!enRango(fila, columna))
			throw new IndicesFueraDeRango();
		boolean vacia = false;
		if (tablero[fila][columna] == null)
			vacia = true;
		return vacia;
	}

	/**
	 * Vacia la casilla correspondiente llamando a una subfuncion con fila y
	 * columna.
	 * 
	 * @param casilla
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public void vaciarCasilla(Casilla casilla) throws IndicesFueraDeRango {
		this.vaciarCasilla(casilla.getFila(), casilla.getColumna());
	}

	/**
	 * Vacia la casilla correspondiente especificada con los parametros.
	 * 
	 * @param fila
	 * @param columna
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public void vaciarCasilla(int fila, int columna) throws IndicesFueraDeRango {
		if (!this.enRango(fila, columna))
			throw new IndicesFueraDeRango();
		this.tablero[fila][columna] = null;
	}

	/**
	 * Modifica la casilla asignandole la celula especificada por paramtero
	 * usando una subfuncion.
	 * 
	 * @param casilla
	 * @param celula
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public void setCasilla(Casilla casilla, Celula celula)
			throws IndicesFueraDeRango {
		this.setCasilla(casilla.getFila(), casilla.getColumna(), celula);
	}

	/**
	 * Modifica la casilla asignandole la nueva celula especificada por
	 * parametro.
	 * 
	 * @param fila
	 * @param columna
	 * @param celula
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 */

	public void setCasilla(int fila, int columna, Celula celula)
			throws IndicesFueraDeRango {
		if (!this.enRango(fila, columna))
			throw new IndicesFueraDeRango();
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

	/**
	 * Carga del scanner del fichero especificado por parametro el juego que se
	 * habia guardado.
	 * 
	 * @param fich
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en caso de que la casilla esta fuera de
	 *             rango de la superficie.
	 * @throws PalabraIncorrecta
	 *             Lanza una excepcion en caso de que la palabra no sea la
	 *             adecuada.
	 * @throws FormatoNumericoIncorrecto
	 *             Lanza una excepcion en caso de que el numero que obtenga el
	 *             scanner no sea del formato correcto.
	 */

	public void cargar(Scanner fich) throws IndicesFueraDeRango,
			PalabraIncorrecta, FormatoNumericoIncorrecto {
		Celula celula = null;
		while (fich.hasNextLine()) {
			String linea = fich.nextLine();
			String[] cadenaLinea = linea.split(" ");
			if (cadenaLinea.length >= 4) {
				Casilla casilla = this.parseaCasilla(cadenaLinea);
				if (cadenaLinea[2].equalsIgnoreCase("simple")
						&& cadenaLinea.length == 5) {
					celula = new CelulaSimple();
				} else if (cadenaLinea[2].equalsIgnoreCase("compleja")
						&& cadenaLinea.length == 4) {
					celula = new CelulaCompleja();
				} else {
					throw new PalabraIncorrecta();
				}
				celula.cargar(cadenaLinea);
				this.setCasilla(casilla, celula);
			} else
				throw new PalabraIncorrecta();
		}
	}

	/**
	 * Guarda el juego que este en funcionamiento para poder retomarlo en el
	 * momento en el que el usuario lo desee.
	 * 
	 * @param fich
	 * @throws IOException
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que la casilla este fuera
	 *             de rango.
	 */

	public void guardar(FileWriter fich) throws IOException,
			IndicesFueraDeRango {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if (!this.esVacia(i, j)) {
					fich.write(i + " " + j + " ");
					this.tablero[i][j].guardar(fich);
				}
			}
		}
	}

	private Casilla parseaCasilla(String[] cadenaLinea)
			throws FormatoNumericoIncorrecto {
		Casilla casilla = null;
		try {
			int fila = Integer.parseInt(cadenaLinea[0]);
			int columna = Integer.parseInt(cadenaLinea[1]);
			casilla = new Casilla(fila, columna);
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
		return casilla;
	}
}
