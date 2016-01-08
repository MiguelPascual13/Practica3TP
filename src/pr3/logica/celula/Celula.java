package pr3.logica.celula;

import java.io.FileReader;

import pr3.logica.Casilla;
import pr3.logica.Superficie;

public interface Celula {
	public abstract String toString();

	public abstract void cargar(FileReader entrada);

	public abstract void guardar();

	public abstract Casilla ejecutaMovimiento(Casilla casilla, Superficie superficie);

	public abstract boolean esComestible();
}
