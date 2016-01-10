package pr3.logica.celula;

import java.io.FileWriter;
import java.io.IOException;

import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.logica.Casilla;
import pr3.logica.Superficie;

public interface Celula {
	public abstract String toString();

	public abstract void cargar(String[] cadenaLinea) throws FormatoNumericoIncorrecto;

	public abstract void guardar(FileWriter fich) throws IOException;

	public abstract Casilla ejecutaMovimiento(Casilla casilla, Superficie superficie);

	public abstract boolean esComestible();
}
