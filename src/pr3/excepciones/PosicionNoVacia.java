package pr3.excepciones;

@SuppressWarnings("serial")
public class PosicionNoVacia extends Exception {
	public String toString() {
		return "ERROR: No se Puede Sobreescribir una C�lula.";
	}
}
