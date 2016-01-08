package pr3.excepciones;

@SuppressWarnings("serial")
public class ErrorDeInicializacion extends Exception {
	public String toString() {
		return "ERROR: Las Células No Caben en el Tablero.";
	}
}
