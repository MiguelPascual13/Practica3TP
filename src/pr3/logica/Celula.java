package pr3.logica;

import java.io.FileReader;

public interface Celula {
	public abstract String toString();

	public abstract void cargar(FileReader entrada);

	public abstract void guardar();

	public abstract Casilla ejecutaMovimiento(Casilla casilla, Superficie superficie);

	public abstract boolean esComestible();
}
