package pr3.excepciones;

@SuppressWarnings("serial")
public class PosicionVacia extends Exception {
	public String toString() {
		return "ERROR: No se Puede Vaciar una Posici�n ya Vac�a.";
	}
}
