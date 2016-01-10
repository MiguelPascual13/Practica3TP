package pr3.control.comandos;

import pr3.control.Controlador;

public class Cargar implements Comando {

	private String nombreFichero;

	public void ejecuta(Controlador controlador) {
		System.out.println("Cargando...");
		controlador.cargar(this.nombreFichero);
	}

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
		return "CARGAR:\t\t\tCarga una partida de un archivo de texto.\n";
	}

}
