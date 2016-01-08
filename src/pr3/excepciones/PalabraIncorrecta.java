package pr3.excepciones;

@SuppressWarnings("serial")
public class PalabraIncorrecta extends Exception {
	public String toString() {
		return "ERROR: Palabra Incorrecta.";
	}
}
