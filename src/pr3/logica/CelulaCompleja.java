package pr3.logica;

/**
 * Clase heredada de celula que contine las mismas funcionalidades que una
 * celula en general pero particularizadas al caso complejo, es decir, no son
 * comestibles, se mueve con sus propias reglas,...
 */
public class CelulaCompleja extends Celula {
	private final int MAX_COMIDAS = 3;
	private int celulasComidas;

	/*-----CONSTRUCTORAS-----*/
	/**
	 * Constructora (unica) crea una nueva celula compleja con ciertas celulas
	 * ya comidas.
	 * 
	 * @param celulasComidas
	 *            celulas que la celula que se va a crear se ha comido ya.
	 */
	public CelulaCompleja(int celulasComidas) {
		this.celulasComidas = celulasComidas;
	}

	/*-----METODOS EN GENERAL-----*/
	/**
	 * System.out.println(compleja) >>>> "*"
	 */
	public String toString() {
		return "*";
	}

	/**
	 * Mueve la celula a una casilla aleatoria del tablero, siempre que esta
	 * esté vacia o bien contenga a una celula comestible, en caso de contener
	 * la casilla de destino una celula compleja, esta no se movera.
	 */
	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) {
		Casilla destino = null;
		int fAleatoria = (int) (Math.random() * superficie.getFilas());
		int cAleatoria = (int) (Math.random() * superficie.getColumnas());
		destino = new Casilla(fAleatoria, cAleatoria);
		if (superficie.esVacia(destino)) {
			superficie.setCasilla(destino, this);
			superficie.vaciarCasilla(origen);
			System.out.println("Movimiento de celula compleja de " + origen + " a " + destino);
		} else {
			if (!superficie.getComestibilidad(destino))
				destino = null;
			else {
				this.celulasComidas++;
				superficie.setCasilla(destino, this);
				superficie.vaciarCasilla(origen);
				System.out.println("Movimiento de celula compleja de " + origen + " a " + destino);
				System.out.println("La celula simple de " + destino + " fue deglutida");
				if (this.celulasComidas >= MAX_COMIDAS)
				{
					System.out.println("La celula compleja de "+destino+" exploto");
					superficie.vaciarCasilla(destino);
				}
			}
		}
		return destino;
	}

	public boolean esComestible() {
		return this.esComestible;
	}
}
