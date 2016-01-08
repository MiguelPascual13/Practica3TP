package pr3.control;

import pr3.control.comandos.*;

public class ParserComandos {
	private static Comando[] comandos = { new Ayuda(), new Cargar(), new CrearCelula(), new EliminarCelula(),
			new Guardar(), new Iniciar(), new Jugar(), new Paso(), new Salir(), new Vaciar() };

	static public String ayudaComandos() {
		String ayuda = "";
		for (int i = 0; i < comandos.length; i++) {
			ayuda += comandos[i].textoAyuda();
		}
		return ayuda;
	}

	static public Comando parseaComando(String[] cadenas) {
		Comando comando = null;
		boolean encontrado = false;
		int i = 0;
		while (i < comandos.length && !encontrado) {
			if (comandos[i].parsea(cadenas) != null) {
				encontrado = true;
			} else {
				i++;
			}
		}

		if (encontrado)
			comando = comandos[i];

		return comando;
	}
}
