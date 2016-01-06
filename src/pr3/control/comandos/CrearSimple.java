package pr3.control.comandos;

import pr3.logica.Casilla;
import pr3.logica.CelulaSimple;
import pr3.logica.Mundo;

public class CrearSimple extends Comando {

	private int f;
	private int c;

	/**
	 * Constructora para evitar el uso de la constructora por defecto.
	 */
	public CrearSimple() {
		this.f = 0;
		this.c = 0;
	}

	public void ejecuta(Mundo mundo) {
		CelulaSimple simple = new CelulaSimple(0, 0);
		Casilla casilla = new Casilla(this.f, this.c);
		if (!mundo.crearCelula(casilla, simple))
			System.out.println("ERROR: Debe ser una casilla vacia");
		else
			System.out.println("Creando celula simple en " + casilla + "...");
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 3) {
			if (cadenaComando[0].equals("CREARCELULASIMPLE")) {
				comando = this;
				this.f = Integer.parseInt(cadenaComando[1]);
				this.c = Integer.parseInt(cadenaComando[2]);
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "CREARSIMPLE f c:\tCrea una nueva Celula Simple en la casilla especificada por parametro.\n";
	}

}
