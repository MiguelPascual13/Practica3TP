package pr3.logica.mundo;

import java.io.FileWriter;
import java.io.IOException;

import pr3.excepciones.ErrorDeInicializacion;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.celula.CelulaSimple;

public class MundoSimple extends Mundo {
	private int simples;

	public MundoSimple(int filas, int columnas, int simples) throws ErrorDeInicializacion {
		super(filas, columnas);
		if (this.filas * this.columnas < simples)
			throw new ErrorDeInicializacion();
		this.simples = simples;
		this.inicializaMundo();
	}

	public MundoSimple() {
		super();
		this.simples = 0;
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
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
	}

	public void guardar(FileWriter fich) throws IOException, IndicesFueraDeRango {
		fich.write("simple\n" + this.filas + "\n" + this.columnas + "\n");
		this.superficie.guardar(fich);
	}

}
