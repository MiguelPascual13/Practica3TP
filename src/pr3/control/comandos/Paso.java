package pr3.control.comandos;

import pr3.logica.Mundo;

public class Paso extends Comando {

	public void ejecuta(Mundo mundo) {
		System.out.println("Evolucionando...");
		mundo.evoluciona();
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equals("PASO"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "PASO:\t\t\tRealiza una evolucion en el mundo.\n";
	}

}
