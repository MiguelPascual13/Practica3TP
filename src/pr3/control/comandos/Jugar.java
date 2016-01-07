package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.logica.Mundo;

public class Jugar implements Comando {

	private Mundo mundo;

	public Jugar() {
		mundo = null;
	}

	public void ejecuta(Controlador controlador) {
		controlador.juega(this.mundo);
	}

	public Comando parsea(String[] cadenaComando) {
		return null;
	}

	public String textoAyuda() {
		return "JUGAR:\t\t\tComienza la simulacion.\n";
	}

}
