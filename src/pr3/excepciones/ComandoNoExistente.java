package pr3.excepciones;

@SuppressWarnings("serial")
public class ComandoNoExistente extends Exception{
	public String toString() {
		return "ERROR: Comando No Existente.";
	}
}
