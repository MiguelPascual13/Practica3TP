package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorComando;

public interface Comando {
	public abstract void ejecuta(Controlador controlador);

	public abstract Comando parsea(String[] cadenaComando) throws ErrorComando;

	public abstract String textoAyuda();
}
