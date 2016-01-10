package pr3.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pr3.control.comandos.Comando;
import pr3.excepciones.ComandoNoExistente;
import pr3.excepciones.ErrorComando;
import pr3.excepciones.FormatoNumericoIncorrecto;
import pr3.excepciones.IndicesFueraDeRango;
import pr3.excepciones.PalabraIncorrecta;
import pr3.excepciones.PosicionNoVacia;
import pr3.excepciones.PosicionVacia;
import pr3.logica.Casilla;
import pr3.logica.celula.Celula;
import pr3.logica.mundo.Mundo;
import pr3.logica.mundo.MundoComplejo;
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

	public void cargar(String nombreFichero) {
		File fichero = new File(nombreFichero);
		Scanner fich = null;
		try {
			fich = new Scanner(fichero);
			Mundo mundoCargar = this.getComplejidad(fich);
			mundoCargar.cargar(fich);
			this.mundo = mundoCargar;
		} catch (FormatoNumericoIncorrecto e1) {
			System.out.println(e1);
		} catch (IndicesFueraDeRango e2) {
			System.out.println(e2);
		} catch (FileNotFoundException e3) {
			System.out.println("ERROR: No se Encontró el Fichero.");
		} catch (PalabraIncorrecta e4) {
			System.out.println(e4);
		} finally {
			if (fich != null)
				fich.close();
		}
	}

	public void guardar(String nombreFichero) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(nombreFichero);
			this.mundo.guardar(fichero);
		} catch (IndicesFueraDeRango e1) {
			System.out.println(e1);
		} catch (IOException e2) {
			System.out.println(e2);
		} finally {
			if (fichero != null)
				try {
					fichero.close();
				} catch (IOException e) {
					System.out.println("ERROR: No se Pudo Cerrar el Fichero.");
				}
		}
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

			} catch (ComandoNoExistente e2) {
				System.out.println(e2);
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
		if (!this.mundo.eliminarCelula(casilla))
			throw new PosicionVacia();
	}

	public void crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango, PosicionNoVacia {
		if (!this.mundo.crearCelula(casilla, celula))
			throw new PosicionNoVacia();
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
		return this.mundo.getClass() == MundoSimple.class;
	}

	private Mundo getComplejidad(Scanner fich) throws PalabraIncorrecta {
		String complejidad = fich.nextLine();
		if (complejidad.equalsIgnoreCase("simple")) {
			return new MundoSimple();
		} else if (complejidad.equalsIgnoreCase("complejo")) {
			return new MundoComplejo();
		} else {
			throw new PalabraIncorrecta();
		}
	}
}
