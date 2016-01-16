package pr3.logica.mundo;

import pr3.excepciones.ErrorDeInicializacion;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.celula.CelulaCompleja;
import pr3.logica.celula.CelulaSimple;

/**
 * 
 * Clase Mundo Complejo, clase hija de la clase abstracta Mundo. Implementa los
 * metodos abstractos de mundo ademas de implementar algunos suyos propios. Se
 * encarga de gestionar todo el mundo complejo que se trate en un juego
 * correspondiente.
 *
 */

public class MundoComplejo extends Mundo {
	private int simples;
	private int complejas;

	/**
	 * Contructora con parametros de la clase Mundo Complejo.
	 * 
	 * @param filas
	 * @param columnas
	 * @param simples
	 * @param complejas
	 * @throws ErrorDeInicializacion
	 *             Lanza una excepcion en caso de que ocurra un error de
	 *             inicializacion.
	 */

	public MundoComplejo(int filas, int columnas, int simples, int complejas)
			throws ErrorDeInicializacion {
		super(filas, columnas);
		if (filas * columnas < simples + complejas)
			throw new ErrorDeInicializacion();
		this.simples = simples;
		this.complejas = complejas;
		this.inicializaMundo();
	}

	/**
	 * Contructora que llama a la contructora super de su padre y no tiene
	 * parametros.
	 */

	public MundoComplejo() {
		super();
		this.simples = 0;
		this.complejas = 0;
	}

	/**
	 * Inicializa un mundo complejo. En esta funcion es posible que se coga una
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
			for (int i = 0; i < this.complejas; i++) {
				int fila = (int) (Math.random() * superficie.getFilas());
				int columna = (int) (Math.random() * superficie.getColumnas());

				while (!crearCelula(fila, columna, new CelulaCompleja())) {
					fila = (int) (Math.random() * superficie.getFilas());
					columna = (int) (Math.random() * superficie.getColumnas());
				}
			}
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
	}

	protected String getComplejidad() {
		return "complejo\n";
	}
}
