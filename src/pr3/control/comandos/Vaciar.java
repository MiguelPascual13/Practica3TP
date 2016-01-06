package pr3.control.comandos;

import pr3.logica.Mundo;

public class Vaciar extends Comando {

	public void ejecuta(Mundo mundo) {
		System.out.println("Vaciando mundo...");
		mundo.vaciar();
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equals("VACIAR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "VACIAR:\t\t\tVacia el mundo.\n";
	}

}
