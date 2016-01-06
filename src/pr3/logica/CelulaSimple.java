package pr3.logica;

/**
 * Clase heredada de Celula que contiene las mismas funcionalidades de una
 * celula general con las particularidades de una celula de tipo simple. Puede
 * reproducirse, es comestible, puede morir por falta de ejercio,...
 */
public class CelulaSimple extends Celula {
	/*-----ATRIBUTOS-----*/
	private final int MAX_PASOS_SIN_MOVER = 3;
	private final int PASOS_REPRODUCCION = 3;
	private int pasosDados;
	private int pasosSinMover;

	/*-----CONSTRUCTORAS-----*/

	/**
	 * Constructora (unica), crea una celula simple coon ciertos pasos ya dados
	 * y ciertos pasos sin mover.
	 * 
	 * @param pasosDados
	 *            Pasos que la celula ha permanecido viva en el tablero.
	 * @param pasosSinMover
	 *            Pasos que la celula ha estado sin moverse desde el ultimo
	 *            movimiento "efectivo que realizo".
	 */
	public CelulaSimple(int pasosDados, int pasosSinMover) {
		this.pasosDados = pasosDados;
		this.pasosSinMover = pasosSinMover;
		esComestible = true;
	}

	/*-----METODOS EN GENERAL-----*/

	/**
	 * System.out.println(simple) >>>> "X"
	 */
	public String toString() {
		return "X";
	}

	/**
	 * Mueve la celula aleatoriamente a alguna de sus casillas vecinas,
	 * reprodudiendose cuando llegue el momento y muriendo cuando se den las
	 * condiciones citadas en el enunciado de la practica.
	 */
	public Casilla ejecutaMovimiento(Casilla origen, Superficie superficie) {

		Casilla destino = null;
		VectorMov entorno = new VectorMov();

		for (int i = origen.getFila() - 1; i <= origen.getFila() + 1; i++) {
			for (int j = origen.getColumna() - 1; j <= origen.getColumna() + 1; j++) {
				Casilla posicionCandidata = new Casilla(i, j);
				if (i >= 0 && i < superficie.getFilas()) {
					if (j >= 0 && j < superficie.getColumnas()) {
						if (superficie.esVacia(posicionCandidata)) {
							entorno.setCeldaSiguiente(posicionCandidata);
						}
					}
				}
			}
		}

		this.pasosDados++;

		if (entorno.getContador() > 0) {
			int aleatorio = (int) (Math.random() * entorno.getContador());
			int f = entorno.getFila(aleatorio);
			int c = entorno.getColumna(aleatorio);
			destino = new Casilla(f, c);
			superficie.setCasilla(destino, this);
			superficie.vaciarCasilla(origen);
			System.out.println("Movimiento de celula simple de " + origen + " a " + destino);
			if (this.pasosDados % PASOS_REPRODUCCION == 0) {
				CelulaSimple hija = new CelulaSimple(0, 0);
				superficie.setCasilla(origen, hija);
				System.out.println("Nace nueva celula simple en " + origen + " cuyo padre ha sido " + destino);
			}
		} else {
			this.pasosSinMover++;
			if (this.pasosSinMover >= MAX_PASOS_SIN_MOVER + 1) {
				superficie.vaciarCasilla(origen);
				System.out.println("Muere la celula simple de la casilla " + origen + " por inactividad");
			} else if (this.pasosDados % PASOS_REPRODUCCION == 0) {
				System.out.println("Muere la celula simple de la casilla " + origen + " por irreproducibilidad");
				superficie.vaciarCasilla(origen);
			}
		}

		return destino;
	}

	public boolean esComestible() {
		return esComestible;
	}
}
