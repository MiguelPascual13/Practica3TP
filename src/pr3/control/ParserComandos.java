package pr3.control;

import pr3.control.comandos.Ayuda;
import pr3.control.comandos.Cargar;
import pr3.control.comandos.Comando;
import pr3.control.comandos.CrearCelula;
import pr3.control.comandos.EliminarCelula;
import pr3.control.comandos.Guardar;
import pr3.control.comandos.Iniciar;
import pr3.control.comandos.Jugar;
import pr3.control.comandos.Paso;
import pr3.control.comandos.Salir;
import pr3.control.comandos.Vaciar;
import pr3.excepciones.ComandoNoExistente;
import pr3.excepciones.ErrorComando;

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

	static public Comando parseaComando(String[] cadenas) throws ErrorComando, ComandoNoExistente {
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
		else
			throw new ComandoNoExistente();

		return comando;
	}
}
