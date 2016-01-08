package pr3.control.comandos;

import pr3.control.Controlador;

public class Cargar implements Comando {

	private String nombreFichero;

	@Override
	public void ejecuta(Controlador controlador) {
		// TODO Auto-generated method stub

	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		// TODO Auto-generated method stub
		return null;
	}

	public String textoAyuda() {
		return "CARGAR:\t\t\tCarga una partida de un archivo de texto.\n";
	}

}
