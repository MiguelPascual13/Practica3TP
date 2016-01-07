package pr3.logica;

public interface Celula {
	public abstract String toString();

	public abstract boolean cargar();

	public abstract boolean guardar();

	public abstract Casilla ejecutaMovimiento(Casilla casilla, Superficie superficie);

	public abstract boolean esComestible();
}
