package pr3.mundo;

import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.CelulaSimple;

public class MundoSimple extends Mundo {
	private int simples;

	public MundoSimple(int filas, int columnas, int simples) {
		super(filas, columnas);
		this.simples = simples;
		this.inicializaMundo();
	}
	
	public MundoSimple()
	{
		super();
		this.simples=0;
		this.inicializaMundo();
	}
	
	public void inicializaMundo() {
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
			// No se da nunca.
		}
	}

}
