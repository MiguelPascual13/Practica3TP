package pr3.control;

import java.util.Scanner;

import pr3.control.comandos.Comando;
import pr3.logica.Casilla;
import pr3.logica.Celula;
import pr3.logica.Mundo;

public class Controlador {
	private Mundo mundo;
	private Scanner in;
	private boolean simulacionTerminada;

	public Controlador(Mundo mundo, Scanner in) {
		this.simulacionTerminada = false;
		this.mundo = mundo;
		this.in = in;
	}

	void cargar(String nombreFichero) {

	}

	public void realizaSilulacion() {
		System.out.println("Bienvenido al mundo celular...");
		System.out.println(mundo);
		String[] words = captarEntrada();
		while (!this.simulacionTerminada) {
			Comando comando = ParserComandos.parseaComando(words);
			if (comando != null) {
				comando.ejecuta(this);
			} else {
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

	public boolean eliminarCelula(Casilla casilla) {
		return this.mundo.eliminarCelula(casilla);
	}

	public boolean crearCelula(Casilla casilla, Celula celula) {
		return this.mundo.crearCelula(casilla, celula);
	}

	public void juega(Mundo mundo) {
		this.mundo = mundo;
	}
}
