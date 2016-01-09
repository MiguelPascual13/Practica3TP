package pr3.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.celula.Celula;
import pr3.logica.celula.CelulaCompleja;
import pr3.logica.celula.CelulaSimple;

public class Superficie {

	private int filas;
	private int columnas;

	private Celula[][] tablero;

	public boolean enRango(int fila, int columna) {
		boolean sinErrores = true;
		if (fila < 0 || fila >= this.filas)
			sinErrores = false;
		else if (columna < 0 || columna >= this.columnas)
			sinErrores = false;
		return sinErrores;
	}

	public Superficie(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		tablero = new Celula[this.filas][this.columnas];
		vaciar();
	}

	public void vaciar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = null;
			}
		}
	}

	public Casilla ejecutaMovimiento(Casilla casilla) {
		Casilla destino = this.tablero[casilla.getFila()][casilla.getColumna()].ejecutaMovimiento(casilla, this);
		return destino;
	}

	public int getFilas() {
		return this.filas;
	}

	public int getColumnas() {
		return this.columnas;
	}

	public boolean getComestibilidad(Casilla casilla) throws IndicesFueraDeRango {
		return this.getComestibilidad(casilla.getFila(), casilla.getColumna());
	}

	public boolean getComestibilidad(int fila, int columna) throws IndicesFueraDeRango {
		if (!this.enRango(fila, columna))
			throw new IndicesFueraDeRango();
		return tablero[fila][columna].esComestible();
	}

	public boolean esVacia(Casilla casilla) throws IndicesFueraDeRango {
		return this.esVacia(casilla.getFila(), casilla.getColumna());
	}

	public boolean esVacia(int fila, int columna) throws IndicesFueraDeRango {
		if (!enRango(fila, columna))
			throw new IndicesFueraDeRango();
		boolean vacia = false;
		if (tablero[fila][columna] == null)
			vacia = true;
		return vacia;
	}

	public void vaciarCasilla(Casilla casilla) throws IndicesFueraDeRango {
		this.vaciarCasilla(casilla.getFila(), casilla.getColumna());
	}

	public void vaciarCasilla(int fila, int columna) throws IndicesFueraDeRango {
		if (!this.enRango(fila, columna))
			throw new IndicesFueraDeRango();
		this.tablero[fila][columna] = null;
	}

	public void setCasilla(Casilla casilla, Celula celula) throws IndicesFueraDeRango {
		this.setCasilla(casilla.getFila(), casilla.getColumna(), celula);
	}

	public void setCasilla(int fila, int columna, Celula celula) throws IndicesFueraDeRango {
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

	public void cargar(Scanner fich) throws IndicesFueraDeRango {
		Celula celula = null;
		while (fich.hasNextLine()) {
			String linea = fich.nextLine();
			String[] cadenaLinea = linea.split(" ");
			int fila = Integer.parseInt(cadenaLinea[0]);
			int columna = Integer.parseInt(cadenaLinea[1]);
			if (cadenaLinea[2].equals("simple")) {
				celula = new CelulaSimple();
			} else if (cadenaLinea[2].equals("compleja")) {
				celula = new CelulaCompleja();
			}
			celula.cargar(cadenaLinea);
			this.setCasilla(fila, columna, celula);
		}
	}

	public void guardar(FileWriter fich) throws IOException, IndicesFueraDeRango {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if (!this.esVacia(i, j)) {
					fich.write(i + " " + j + " ");
					this.tablero[i][j].guardar(fich);
				}
			}
		}
	}
}
