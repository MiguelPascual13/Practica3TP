package pr3.control.comandos;

import pr3.control.Controlador;

public class Salir implements Comando {
	public void ejecuta(Controlador controlador) {
		controlador.setSimulacionTerminada(true);
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("SALIR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "SALIR:\t\t\tFinaliza la Simulacion.\n";
	}
}
