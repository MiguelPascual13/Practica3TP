package pr3.excepciones;

@SuppressWarnings("serial")
public class ErrorDeInicializacion extends Exception {
	public String toString() {
		return "ERROR: Las C�lulas No Caben en el Tablero.";
	}
}
