package pr3.control.comandos;

import pr3.control.Controlador;

public class Vaciar implements Comando {

	public void ejecuta(Controlador controlador) {
		System.out.println("Vaciando mundo...");
		controlador.vaciar();
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("VACIAR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "VACIAR:\t\t\tVacia el mundo.\n";
	}

}
