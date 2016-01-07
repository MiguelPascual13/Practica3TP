package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.mundo.Mundo;

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
		this.complejas = 0;
	}

	public void ejecuta(Controlador controlador) {
		controlador.juega(this.mundo);
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 5) {
			if (cadenaComando[0].equals("JUGAR")) {
				if (cadenaComando[1].equals("SIMPLE")) {
					this.filas = Integer.parseInt(cadenaComando[2]);
					this.columnas = Integer.parseInt(cadenaComando[3]);
					this.simples = Integer.parseInt(cadenaComando[4]);
					comando = this;
				} else if (cadenaComando[1].equals("COMPLEJA") && cadenaComando.length >= 6) {
					this.filas = Integer.parseInt(cadenaComando[2]);
					this.columnas = Integer.parseInt(cadenaComando[3]);
					this.simples = Integer.parseInt(cadenaComando[4]);
					this.complejas = Integer.parseInt(cadenaComando[5]);
					comando = this;
				}
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "JUGAR:\t\t\tComienza la simulacion.\n";
	}

}
