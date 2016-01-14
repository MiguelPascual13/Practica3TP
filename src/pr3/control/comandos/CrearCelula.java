package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorComando;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PosicionNoVacia;
import pr3.logica.Casilla;
import pr3.logica.celula.Celula;
import pr3.logica.celula.CelulaCompleja;
import pr3.logica.celula.CelulaSimple;

/**
 * 
 * Comando crear celula.
 *
 */

public class CrearCelula implements Comando {

	private int fila;
	private int columna;

	/**
	 * Contructora de crear celula asignandole 0 a fila y colmna.
	 */
	
	public CrearCelula() {
		this.fila = 0;
		this.columna = 0;
	}
	
	/**
	 * Crea una celula mediante la clase controlador y dependiendo de su complejidad se crea una celula en un mundo simple
	 * o en un mundo complejo.
	 */

	public void ejecuta(Controlador controlador) {
		System.out.println("Creando celula...");
		if (controlador.getComplejidad())
			this.ejecutaSimple(controlador);
		else
			this.ejecutaComplejo(controlador);
	}
	
	/**
	 * Comprueba si la cadena coincide con el comando crear celula.
	 */

	public Comando parsea(String[] cadenaComando) throws ErrorComando {
		Comando comando = null;
		if (cadenaComando.length >= 3) {
			if (cadenaComando[0].equalsIgnoreCase("CREARCELULA")) {
				try {
					this.parseaParametros(cadenaComando);
					comando = this;
				} catch (Exception e) {
					System.out.println(e);
					throw new ErrorComando();
				}
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "CREARCELULA f c:\tCrea una nueva Celula en la casilla especificada por parametro.\n";
	}

	private void ejecutaComplejo(Controlador controlador) {
		Celula celula = null;
		String caracteristica = null;
		try {
			if (controlador.crearCelula() == 1) {
				celula = new CelulaCompleja();
				caracteristica = "compleja";
			} else {
				celula = new CelulaSimple();
				caracteristica = "simple";
			}
			Casilla casilla = new Casilla(this.fila, this.columna);
			controlador.crearCelula(casilla, celula);
			System.out.println("Creando celula " + caracteristica + " en " + casilla + "...");
		} catch (IndicesFueraDeRango e1) {
			System.out.println(e1);
		} catch (FormatoNumericoIncorrecto e2) {
			System.out.println(e2);
		} catch (PosicionNoVacia e3) {
			System.out.println(e3);
		}
	}

	private void ejecutaSimple(Controlador controlador) {
		try {
			Celula celula = new CelulaSimple();
			Casilla casilla = new Casilla(this.fila, this.columna);
			controlador.crearCelula(casilla, celula);
			System.out.println("Creando celula simple en " + casilla + "...");
		} catch (IndicesFueraDeRango e1) {
			System.out.println(e1);
		} catch (PosicionNoVacia e2) {
			System.out.println(e2);
		}
	}

	private void parseaParametros(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		try {
			this.fila = Integer.parseInt(cadenaComando[1]);
			this.columna = Integer.parseInt(cadenaComando[2]);
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
	}
}
