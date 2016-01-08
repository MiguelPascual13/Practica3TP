package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorDeInicializacion;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.mundo.Mundo;
import pr3.mundo.MundoComplejo;
import pr3.mundo.MundoSimple;

public class Jugar implements Comando {

	private Mundo mundo;
	private int filas;
	private int columnas;
	private int simples;
	private int complejas;

	public Jugar() {
		this.mundo = null;
		this.filas = 0;
		this.columnas = 0;
		this.simples = 0;
		this.complejas = -1;
	}

	public void ejecuta(Controlador controlador) {
		try {
			if (this.complejas != -1)
				this.mundo = new MundoComplejo(this.filas, this.columnas, this.simples, this.complejas);
			else
				this.mundo = new MundoSimple(this.filas, this.columnas, this.simples);
			controlador.juega(this.mundo);
		} catch (ErrorDeInicializacion e) {
			System.out.println(e);
		}
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 5) {
			if (cadenaComando[0].equals("JUGAR")) {
				try {
					if (cadenaComando[1].equals("SIMPLE")) {
						this.parseaParametrosSimple(cadenaComando);
						comando = this;
					} else if (cadenaComando[1].equals("COMPLEJO") && cadenaComando.length >= 6) {
						this.parseaParametrosComplejo(cadenaComando);
						comando = this;
					}
				} catch (FormatoNumericoIncorrecto e) {
					System.out.println(e);
				}
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "JUGAR:\t\t\tComienza la simulacion.\n";
	}

	private void parseaParametrosSimple(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		try {
			this.filas = Integer.parseInt(cadenaComando[2]);
			this.columnas = Integer.parseInt(cadenaComando[3]);
			this.simples = Integer.parseInt(cadenaComando[4]);
			if (this.filas <= 0 || this.columnas <= 0 || this.simples < 0)
				throw new FormatoNumericoIncorrecto();
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
	}

	private void parseaParametrosComplejo(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		try {
			this.filas = Integer.parseInt(cadenaComando[2]);
			this.columnas = Integer.parseInt(cadenaComando[3]);
			this.simples = Integer.parseInt(cadenaComando[4]);
			this.complejas = Integer.parseInt(cadenaComando[5]);
			if (this.filas <= 0 || this.columnas <= 0 || this.simples < 0 || this.complejas < 0)
				throw new FormatoNumericoIncorrecto();
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
	}
}
