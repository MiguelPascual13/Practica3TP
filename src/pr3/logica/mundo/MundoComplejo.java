package pr3.logica.mundo;

import pr3.excepciones.ErrorDeInicializacion;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.celula.CelulaCompleja;
import pr3.logica.celula.CelulaSimple;

public class MundoComplejo extends Mundo {
	private int simples;
	private int complejas;

	public MundoComplejo(int filas, int columnas, int simples, int complejas) throws ErrorDeInicializacion {
		super(filas, columnas);
		if (filas * columnas < simples + complejas)
			throw new ErrorDeInicializacion();
		this.simples = simples;
		this.complejas = complejas;
		this.inicializaMundo();
	}

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
}
