package pr3.control.comandos;

import pr3.control.Controlador;

public interface Comando {
	public abstract void ejecuta(Controlador controlador);
	public abstract Comando parsea(String[] cadenaComando);
	public abstract String textoAyuda();
}
