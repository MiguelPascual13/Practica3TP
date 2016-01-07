package pr3.excepciones;

public class ErrorDeInicializacion extends Exception {
	public String toString() {
		return "ERROR: Las Celulas No Caben en el tablero";
	}
}
