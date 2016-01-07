package pr3.control.comandos;

import pr3.control.Controlador;

public class Paso implements Comando {

	public void ejecuta(Controlador controlador) {
		System.out.println("Evolucionando...");
		controlador.evoluciona();
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
