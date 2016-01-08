package pr3.control;

import java.io.FileNotFoundException;
import java.util.Scanner;

import pr3.control.comandos.Comando;
import pr3.excepciones.ErrorComando;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.logica.Casilla;
import pr3.logica.celula.Celula;
import pr3.logica.mundo.Mundo;
import pr3.logica.mundo.MundoSimple;

public class Controlador {
	private Mundo mundo;
	private Scanner in;
	private boolean simulacionTerminada;

	public Controlador(Mundo mundo, Scanner in) {
		this.simulacionTerminada = false;
		this.mundo = mundo;
		this.in = in;
	}

	void cargar(String nombreFichero) throws FileNotFoundException {
	}

	public void realizaSilulacion() {
		System.out.println("Bienvenido al mundo celular...");
		System.out.println(mundo);
		String[] words = captarEntrada();
		while (!this.simulacionTerminada) {
			try {
				Comando comando = ParserComandos.parseaComando(words);
				comando.ejecuta(this);
			} catch (ErrorComando e1) {

			} catch (NullPointerException e2) {
				System.out.println("ERROR: Comando no existente.");
			}
			if (!this.simulacionTerminada) {
				System.out.println(mundo);
				words = captarEntrada();
			}
		}
		System.out.println("Fin de la simulacion...");
	}

	private String[] captarEntrada() {
		String[] words = null;
		System.out.print("Comando > ");
		String entrada = in.nextLine();
		entrada = entrada.toUpperCase();
		words = entrada.split(" ");
		return words;
	}

	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}

	public void vaciar() {
		this.mundo.vaciar();
	}

	public void evoluciona() {
		this.mundo.evoluciona();
	}

	public void inicializar() {
		this.mundo.inicializaMundo();
	}

	public void eliminarCelula(Casilla casilla) throws IndicesFueraDeRango {
		this.mundo.eliminarCelula(casilla);
	}

	public void crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango {
		this.mundo.crearCelula(casilla, celula);
	}

	public void juega(Mundo mundo) {
		this.mundo = mundo;
	}

	public int crearCelula() throws FormatoNumericoIncorrecto {
		int num = 0;
		System.out.println("De que tipo: Compleja (1) o Simple (2): ");
		try {
			num = in.nextInt();
			in.nextLine();
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
		if (num != 1 && num != 2)
			throw new FormatoNumericoIncorrecto();
		return num;
	}

	public boolean getComplejidad() {
		if (this.mundo.getClass() == MundoSimple.class)
			return true;
		else
			return false;
	}
}
