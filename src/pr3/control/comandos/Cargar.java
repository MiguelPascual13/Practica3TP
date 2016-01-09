package pr3.control.comandos;

import java.io.FileNotFoundException;

import pr3.control.Controlador;

public class Cargar implements Comando {

	private String nombreFichero;

	public void ejecuta(Controlador controlador) {
		try {
			controlador.cargar(this.nombreFichero);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 2) {
			if (cadenaComando[0].equals("CARGAR")) {
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
