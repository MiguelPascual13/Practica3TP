package pr3.control.comandos;

import java.io.FileNotFoundException;

import pr3.control.Controlador;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PalabraIncorrecta;
import pr3.excepciones.PosicionNoVacia;

/**
 * 
 * Comando cargar.
 *
 */

public class Cargar implements Comando {

	private String nombreFichero;

	/**
	 * Carga el mundo deseado en el controlador.
	 */
	public void ejecuta(Controlador controlador) throws FileNotFoundException, PalabraIncorrecta, IndicesFueraDeRango,
			FormatoNumericoIncorrecto, PosicionNoVacia {
		System.out.println("Cargando...");
		controlador.cargar(this.nombreFichero);
	}

	/**
	 * Comprueba si la cadena coincide con el comando cargar.
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 2) {
			if (cadenaComando[0].equalsIgnoreCase("CARGAR")) {
				this.nombreFichero = cadenaComando[1];
				comando = this;
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "CARGAR nombFich:\t\t\tCarga una partida de un archivo de texto.\n";
	}

}
