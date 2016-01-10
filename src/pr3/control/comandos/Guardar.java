package pr3.control.comandos;

import pr3.control.Controlador;

public class Guardar implements Comando {

	private String nombreFichero;

	public void ejecuta(Controlador controlador) {
		System.out.println("Guardando mundo...");
		controlador.guardar(this.nombreFichero);
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 2) {
			if (cadenaComando[0].equalsIgnoreCase("GUARDAR")) {
				this.nombreFichero = cadenaComando[1];
				comando = this;
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "GUARDAR:\t\tGuarda la Partida en un Fichero de Texto.\n";
	}

}
