package pr3.logica.mundo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PalabraIncorrecta;
import pr3.excepciones.PosicionNoVacia;
import pr3.logica.Casilla;
import pr3.logica.Superficie;
import pr3.logica.celula.Celula;

/**
 * 
 * Clase abstracta mundo. Se encarga de gestionar el mundo que se este
 * ejecutando y de realizar las diferentes actividades de el. Esta implementada
 * como una clase abstracta de la que derivan Mundo Simple y Mundo Complejo.
 *
 */

public abstract class Mundo {
	protected int filas;
	protected int columnas;
	protected Superficie superficie;

	/**
	 * Contructora de la clase abstracta mundo sin parametros.
	 */

	public Mundo() {
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
	}

	/**
	 * Contructora de la clase abstracta mundo con parametros.
	 * 
	 * @param filas
	 * @param columnas
	 */

	public Mundo(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		superficie = new Superficie(this.filas, this.columnas);
	}

	/**
	 * Metodo abstracto que se implementara en las correspondientes clases
	 * hijas. Se encarga de inicializar el mundo correspondiente.
	 */
	public abstract void inicializaMundo();

	/**
	 * Se encarga de guardar en el fichero la correspondiente información del
	 * mundo que se este jugando en ese momento.
	 * 
	 * @param fich
	 *            Se pasa como parametro el atributo que escribira en el
	 *            archivo.
	 * @throws IOException
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que una casilla este fuera
	 *             del rango de la superficie.
	 */

	public void guardar(FileWriter fich) throws IOException, IndicesFueraDeRango {
		fich.write(this.getComplejidad() + this.filas + System.getProperty("line.separator") + this.columnas
				+ System.getProperty("line.separator"));
		this.superficie.guardar(fich);
	}

	/**
	 * Se encarga de cargar en el fichero la correspondiente información del
	 * mundo que se desee jugar en ese momento.
	 * 
	 * @param fich
	 *            se pasa por parametro el scanner de lectura del archivo.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que una casilla este fuera
	 *             de rango.
	 * @throws FormatoNumericoIncorrecto
	 *             Lanza una excepcion en el caso de que aquello que haya leido
	 *             el scanner no sea un numero y sea un formato incorrecto.
	 * @throws PalabraIncorrecta
	 *             Lanza una excepcion en el caso de que el scanner lea un
	 *             string incorrecto con lo que deberia ser.
	 */

	public void cargar(Scanner fich)
			throws IndicesFueraDeRango, FormatoNumericoIncorrecto, PalabraIncorrecta, PosicionNoVacia {
		this.cargarDimension(fich);
		this.superficie = new Superficie(this.filas, this.columnas);
		this.superficie.cargar(fich);
	}

	/**
	 * Vacia la superficie.
	 */

	public void vaciar() {
		superficie.vaciar();
	}

	public String toString() {
		return superficie.toString();
	}

	/**
	 * Crea una nueva celula en la casilla especificada por parametro usando una
	 * subfuncion.
	 * 
	 * @param casilla
	 * @param celula
	 *            La celula que se desea crear.
	 * @return Devuelve un booleano que indica si se ha creado o no.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que la casilla este fuera
	 *             de rango.
	 */

	public boolean crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango {
		return this.crearCelula(casilla.getFila(), casilla.getColumna(), celula);
	}

	/**
	 * Crea una nueva celula en la fila y columna especificadas por parametro
	 * 
	 * @param fila
	 * @param columna
	 * @param celula
	 *            La celula que se desea crear.
	 * @return Devuelve un booleano que indica si se ha creado o no.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que la casilla este fuera
	 *             de rango.
	 */

	public boolean crearCelula(int fila, int columna, Celula celula) throws IndicesFueraDeRango {
		boolean sinErrores = true;
		if (superficie.esVacia(fila, columna)) {
			superficie.setCasilla(fila, columna, celula);
		} else
			sinErrores = false;
		return sinErrores;
	}

	/**
	 * Elimina una celula de la casilla especificada por parametro usando una
	 * subfuncion.
	 * 
	 * @param casilla
	 * @return Devuele un booleano de si se ha conseguido eliminar con exito o
	 *         no.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que la casilla este fuera
	 *             de rango.
	 */

	public boolean eliminarCelula(Casilla casilla) throws IndicesFueraDeRango {
		return this.eliminarCelula(casilla.getFila(), casilla.getColumna());
	}

	/**
	 * Elimina una celula de la fila y columna especificadas por parametro.
	 * 
	 * @param fila
	 * @param columna
	 * @return Devuele un booleano de si se ha conseguido eliminar con exito o
	 *         no.
	 * @throws IndicesFueraDeRango
	 *             Lanza una excepcion en el caso de que la casilla este fuera
	 *             de rango.
	 */

	public boolean eliminarCelula(int fila, int columna) throws IndicesFueraDeRango {
		boolean sinErrores = true;
		if (!superficie.esVacia(fila, columna)) {
			superficie.vaciarCasilla(fila, columna);
		} else
			sinErrores = false;
		return sinErrores;
	}

	/**
	 * Se encarga de gestionar un ciclo vital del mundo que se este jugando.
	 */

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

	private void moverCelula(Casilla casilla, boolean movidas[][]) {
		Casilla destino = superficie.ejecutaMovimiento(casilla);
		if (destino != null)
			movidas[destino.getFila()][destino.getColumna()] = true;
	}

	private void inicializarMovidas(boolean movidas[][]) {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				movidas[i][j] = false;
			}
		}
	}

	private void cargarDimension(Scanner fich) throws FormatoNumericoIncorrecto {
		try {
			this.filas = fich.nextInt();
			this.columnas = fich.nextInt();
			fich.nextLine();
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}

	}

	protected abstract String getComplejidad();
}
