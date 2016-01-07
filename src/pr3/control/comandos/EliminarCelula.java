package pr3.control.comandos;

import pr3.control.Controlador;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PosicionVacia;
import pr3.logica.Casilla;

public class EliminarCelula implements Comando {
	private int f;
	private int c;

	/**
	 * Constructora para evitar el uso de la constructora por defecto.
	 */
	public EliminarCelula() {
		this.f = 0;
		this.c = 0;
	}

	public void ejecuta(Controlador controlador) {
		Casilla casilla = new Casilla(this.f, this.c);
		try{
		controlador.eliminarCelula(casilla);
		System.out.println("Eliminando la celula de " + casilla + "...");
		}
		catch(IndicesFueraDeRango e1)
		{
			System.out.println("ERROR: Índices Fuera de Rango");
		}
		catch(PosicionVacia e2)
		{
			System.out.println("ERROR: Posicion Vacia");
		}
			
	}

	public Comando parsea(String[] cadenaComando) {
		Comando comando = null;
		if (cadenaComando.length >= 3) {
			if (cadenaComando[0].equals("ELIMINARCELULA")) {
				comando = this;
				this.f = Integer.parseInt(cadenaComando[1]);
				this.c = Integer.parseInt(cadenaComando[2]);
			}
		}
		return comando;
	}

	public String textoAyuda() {
		return "ELIMINAR f c:\t\tElimina la Celula de la casilla especificada por parametro.\n";
	}

}
