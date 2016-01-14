package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorComando;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PosicionVacia;
import pr3.logica.Casilla;

/**
 * 
 * Comando eliminar celula.
 *
 */

public class EliminarCelula implements Comando {
	private int fila;
	private int columna;
	
	/**
	 * Contructora de eliminar celula asignandole 0 a fila y colmna.
	 */

	public EliminarCelula() {
		this.fila = 0;
		this.columna = 0;
	}
	
	/**
	 * Elimina una celula mediante la clase controlador en la posicion especificada.
	 */

	public void ejecuta(Controlador controlador) {
		System.out.println("Eliminando celula...");
		Casilla casilla = new Casilla(this.fila, this.columna);
		try {
			controlador.eliminarCelula(casilla);
			System.out.println("Eliminando la celula de " + casilla + "...");
		} catch (IndicesFueraDeRango e1) {
			System.out.println(e1);
		} catch (PosicionVacia e2) {
			System.out.println(e2);
		}
	}

	/**
	 * Comprueba si la cadena coincide con el comando eliminar celula.
	 */
	
	public Comando parsea(String[] cadenaComando) throws ErrorComando {
		Comando comando = null;
		if (cadenaComando.length >= 3) {
			if (cadenaComando[0].equalsIgnoreCase("ELIMINARCELULA")) {
				try {
					this.parseaParametros(cadenaComando);
					comando = this;
				} catch (FormatoNumericoIncorrecto e) {
					System.out.println(e);
					throw new ErrorComando();
				}
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "ELIMINARCELULA f c:\tElimina la Celula de la casilla especificada por parametro.\n";
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
