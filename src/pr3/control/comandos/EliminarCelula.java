package pr3.control.comandos;

import pr3.logica.Casilla;
import pr3.logica.Mundo;

public class EliminarCelula extends Comando {
	private int f;
	private int c;

	/**
	 * Constructora para evitar el uso de la constructora por defecto.
	 */
	public EliminarCelula() {
		this.f = 0;
		this.c = 0;
	}

	public void ejecuta(Mundo mundo) {
		Casilla casilla = new Casilla(this.f, this.c);
		if (!mundo.eliminarCelula(casilla))
			System.out.println("ERROR: Debe ser una casilla ocupada");
		else
			System.out.println("Eliminando la celula de " + casilla + "...");
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 3) {
			if (cadenaComando[0].equals("ELIMINARCELULA")) {
				comando = this;
				this.f = Integer.parseInt(cadenaComando[1]);
				this.c = Integer.parseInt(cadenaComando[2]);
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "ELIMINAR f c:\t\tElimina la Celula de la casilla especificada por parametro.\n";
	}

}
