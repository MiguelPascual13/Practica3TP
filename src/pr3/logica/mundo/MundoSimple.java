package pr3.logica.mundo;

import pr3.excepciones.ErrorDeInicializacion;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.celula.CelulaSimple;

/**
 * 
 * Clase Mundo Simple, clase hija de la clase abstracta Mundo. Implementa los
 * metodos abstractos de mundo ademas de implementar algunos suyos propios. Se
 * encarga de gestionar todo el mundo simple que se trate en un juego
 * correspondiente.
 *
 */
public class MundoSimple extends Mundo {
	private int simples;

	/**
	 * Contructora con parametros de la clase Mundo Simple.
	 * 
	 * @param filas
	 * @param columnas
	 * @param simples
	 * @throws ErrorDeInicializacion
	 *             Lanza una excepcion en caso de que ocurra un error de
	 *             inicializacion.
	 */

	public MundoSimple(int filas, int columnas, int simples)
			throws ErrorDeInicializacion {
		super(filas, columnas);
		if (this.filas * this.columnas < simples)
			throw new ErrorDeInicializacion();
		this.simples = simples;
		this.inicializaMundo();
	}

	/**
	 * Contructora que llama a la contructora super de su padre y no tiene
	 * parametros.
	 */

	public MundoSimple() {
		super();
		this.simples = 0;
	}

	/**
	 * Inicializa un mundo simple. En esta funcion es posible que se coga una
	 * excepcion de indices fuera de rango.
	 */

	public void inicializaMundo() {
		this.vaciar();
		try {
			for (int i = 0; i < this.simples; i++) {
				int fila = (int) (Math.random() * superficie.getFilas());
				int columna = (int) (Math.random() * superficie.getColumnas());

				while (!crearCelula(fila, columna, new CelulaSimple())) {
					fila = (int) (Math.random() * superficie.getFilas());
					columna = (int) (Math.random() * superficie.getColumnas());
				}
			}
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
	}

	protected String getComplejidad() {
		return "simple\n";
	}
}
