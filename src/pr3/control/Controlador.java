package pr3.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import pr3.control.comandos.Comando;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PosicionNoVacia;
import pr3.excepciones.PosicionVacia;
import pr3.logica.Casilla;
import pr3.logica.Celula;
import pr3.mundo.Mundo;
import pr3.mundo.MundoSimple;

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
		File fichero = new File(nombreFichero);
		try {
			FileReader entrada = new FileReader(fichero);
		} catch (IOException e) {
			System.out.println("Imposible");
		}
		Scanner sc = new Scanner(fichero);
		while (sc.hasNextLine()) {

		}
		sc.close();
	}

	public void realizaSilulacion() {
		System.out.println("Bienvenido al mundo celular...");
		System.out.println(mundo);
		String[] words = captarEntrada();
		while (!this.simulacionTerminada) {
			Comando comando = ParserComandos.parseaComando(words);
			try {
				comando.ejecuta(this);
			} catch (NullPointerException e) {
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

	public void eliminarCelula(Casilla casilla) throws IndicesFueraDeRango, PosicionVacia {
		this.mundo.eliminarCelula(casilla);
	}

	public void crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango, PosicionNoVacia {
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
		} catch (Exception e) {
			throw new FormatoNumericoIncorrecto();
		}
		if (num != 1 && num != 2)
			throw new FormatoNumericoIncorrecto();
		return num;
	}
	
	public boolean getComplejidad()
	{
		Mundo world = new MundoSimple();
		if(mundo.getClass().equals(world))
			return true;
		else
			return false;
	}
}
