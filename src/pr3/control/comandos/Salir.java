package pr3.control.comandos;

import pr3.logica.Mundo;

public class Salir extends Comando {
	public void ejecuta(Mundo mundo) {
		System.out.println("Finalizando simulacion...");
		mundo.setSimulacionTerminada(true);
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equals("SALIR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "SALIR:\t\t\tFinaliza la Simulacion.\n";
	}
}
