package pr3.excepciones;

@SuppressWarnings("serial")
public class IndicesFueraDeRango extends Exception {
	public String toString() {
		return "ERROR: �ndices Fuera de Rango.";
	}
}
