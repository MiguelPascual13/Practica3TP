package pr3.logica;

/**
 * Clase para representar a las celulas simples, un caso particular de celulas
 * que tienen el movimiento limitado a las casillas colindantes, se pueden
 * reproducir y son comestibles. También pueden morir por inactividad.
 */
public class CelulaSimple extends Celula {

	/*
	 * "Constantes" vitales de la celula simple. Respectivamente indican: Cada
	 * cuantos pasos se reproducirá la celula. Cuantos pasos puede estar la
	 * celula sin moverse.
	 */
	private static final int PASOS_REPRODUCCION = 3;
	private static final int MAX_PASOS_SIN_MOVER = 1;

	/*
	 * Estos atributos indican respectivamente: El tiempo que lleva "viva" la
	 * celula. Los pasos que lleva la celula sin moverse desde la ultima vez que
	 * lo hizo.
	 */
	private int pasosDados;
	private int pasosSinMover;

	/**
	 * Constructora (unica) con parametros que inicializa la una celula simple
	 * con los datos dados.
	 * 
	 * @param pasosDados
	 *            Unidades de tiempo que lleva viva la celula.
	 * @param pasosSinMover
	 *            Numero de pasos que ha estado la celula sin moverse desde la
	 *            ultima vez que se movio.
	 */
	public CelulaSimple(int pasosDados, int pasosSinMover) {
		esComestible = true; // Las celulas simples son comestibles.
		this.pasosDados = pasosDados;
		this.pasosSinMover = pasosSinMover;
	}

	/**
	 * Ejecuta el movimiento sobre la superficie acorde con las normas de
	 * movimiento de una celula simple.
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		Casilla destino = null;

		/* Proceso de seleccion de casillas candidatas */
		VectorMov entorno = new VectorMov();
		for (int i = f - 1; i <= f + 1; i++) {
			for (int j = c - 1; j <= c + 1; j++) {
				if (i >= 0 && i < superficie.getFilas()) {
					if (j >= 0 && j < superficie.getColumnas()) {
						if (superficie.esVacia(i, j)) {
							entorno.setCeldaSiguiente(i, j);
						}
					}
				}
			}
		}

		/*
		 * Pase lo que pase, ha transcurrido una unidad de tiempo.
		 */
		this.pasosDados++;
		
		/*Proceso de movimiento/no movimiento*/
		Casilla origen = new Casilla(f, c);
		if (entorno.getContador() > 0) {
			int aleatorio = (int) (Math.random() * entorno.getContador());
			int f_aleat = entorno.getFila(aleatorio);
			int c_aleat = entorno.getColumna(aleatorio);
			destino = new Casilla(f_aleat, c_aleat);
			superficie.insertarCelula(f_aleat, c_aleat, this);
			superficie.vaciarCasilla(f, c);
			System.out.println("Movimiento de celula simple de " + origen + " a " + destino);
			if (this.pasosDados % PASOS_REPRODUCCION == 0) {
				CelulaSimple hija = new CelulaSimple(0, 0);
				superficie.insertarCelula(f, c, hija);
				System.out.println("Nace nueva celula simple en " + origen + " cuyo padre ha sido " + destino);
			}
		} else {
			this.pasosSinMover++;
			if (this.pasosSinMover >= MAX_PASOS_SIN_MOVER + 1) {
				superficie.vaciarCasilla(f, c);
				System.out.println("Muere la celula simple de la casilla " + origen + " por inactividad");
			} else if (this.pasosDados % PASOS_REPRODUCCION == 0) {
				System.out.println("Muere la celula simple de la casilla " + origen + " por irreproducibilidad");
				superficie.vaciarCasilla(f, c);
			}
		}

		return destino;
	}

	/**
	 * A la hora de imprimir por pantalla la superficie del mundo, sera mas
	 * sencillo dividir dicha funcionalidad. Este metodo devuelve el string
	 * caracteristico de una celula simple.
	 */
	public String toString() {
		return "X";
	}

	/**
	 * Devuelve la "comestibilidad" de la celula. En el caso de las celulas
	 * simples siempre devuelve true. La gracia de este metodo es usarlo con
	 * polimorfismo.
	 */
	public boolean esComestible() {
		return this.esComestible;
	}

}
