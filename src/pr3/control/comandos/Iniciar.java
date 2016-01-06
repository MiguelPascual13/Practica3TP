package pr3.control.comandos;

import pr3.logica.Mundo;

public class Iniciar extends Comando {

	public void ejecuta(Mundo mundo) {
		System.out.println("Inicializando mundo...");
		mundo.inicializar();
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equals("INICIAR"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "INICIAR:\t\tInicializa el mundo.\n";
	}

}
