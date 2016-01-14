package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorComando;
import pr3.excepciones.ErrorDeInicializacion;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.logica.mundo.Mundo;
import pr3.logica.mundo.MundoComplejo;
import pr3.logica.mundo.MundoSimple;

public class Jugar implements Comando {

	private Mundo mundo;
	private int filas;
	private int columnas;
	private int simples;
	private int complejas;
	
	/**
	 * Contructora de jugar.
	 */

	public Jugar() {
		this.mundo = null;
		this.filas = 0;
		this.columnas = 0;
		this.simples = 0;
		this.complejas = -1;
	}

	/**
	 * Ejecuta el comando jugar mediante la clase controlador. 
	 */
	
	public void ejecuta(Controlador controlador) {
		System.out.println("Creando el nuevo mundo...");
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
	
	/**
	 * Comprueba si la cadena coincide con el comando jugar. 
	 */

	public Comando parsea(String[] cadenaComando) throws ErrorComando {
		Comando comando = null;
		if (cadenaComando.length >= 5) {
			if (cadenaComando[0].equalsIgnoreCase("JUGAR")) {
				try {
					if (cadenaComando[1].equalsIgnoreCase("SIMPLE")) {
						this.parseaParametrosSimple(cadenaComando);
						comando = this;
					} else if (cadenaComando[1].equalsIgnoreCase("COMPLEJO") && cadenaComando.length >= 6) {
						this.parseaParametrosComplejo(cadenaComando);
						comando = this;
					}
				} catch (FormatoNumericoIncorrecto e) {
					System.out.println(e);
					throw new ErrorComando();
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
			this.complejas = -1;
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
