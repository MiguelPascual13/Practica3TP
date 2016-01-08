package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.ErrorComando;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.Casilla;

public class EliminarCelula implements Comando {
	private int fila;
	private int columna;

	public EliminarCelula() {
		this.fila = 0;
		this.columna = 0;
	}

	public void ejecuta(Controlador controlador) {
		Casilla casilla = new Casilla(this.fila, this.columna);
		try {
			controlador.eliminarCelula(casilla);
			System.out.println("Eliminando la celula de " + casilla + "...");
		} catch (IndicesFueraDeRango e) {
			System.out.println(e);
		}
	}

	public Comando parsea(String[] cadenaComando) throws ErrorComando {
		Comando comando = null;
		if (cadenaComando.length >= 3) {
			if (cadenaComando[0].equals("ELIMINARCELULA")) {
				try {
					this.parseaParametros(cadenaComando);
					comando = this;
				} catch (FormatoNumericoIncorrecto e) {
					System.out.println(e);
					throw new ErrorComando();
				}
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "ELIMINARCELULA f c:\tElimina la Celula de la casilla especificada por parametro.\n";
	}

	private void parseaParametros(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		try {
			this.fila = Integer.parseInt(cadenaComando[1]);
			this.columna = Integer.parseInt(cadenaComando[2]);
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
	}
}
