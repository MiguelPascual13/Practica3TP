package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.control.ParserComandos;

public class Ayuda implements Comando {
	public void ejecuta(Controlador controlador) {
		System.out.println(ParserComandos.ayudaComandos());
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando[0].equalsIgnoreCase("AYUDA"))
			comando = this;
		return comando;
	}

	public String textoAyuda() {
		return "\nAYUDA:\tBreve descripcion del efecto de los comandos.\n";
	}
}
