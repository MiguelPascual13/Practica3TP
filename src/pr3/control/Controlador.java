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

/**
 * 
 * Clase Controlador encargada de realizar la simulación del mundo correpondiente que se este jugando, sea simple o complejo,
 * siguiendo los deseos del usuario. 
 *
 */

public class Controlador {
	private Mundo mundo;
	private Scanner in;
	private boolean simulacionTerminada;
	
	/**
	 * Constructora con parametros.
	 * @param mundo
	 * 		Mundo sobre el que se realiza la simulación, puede ser simple o complejo y puede cambiar durante la simulacion.
	 * 
	 * @param in
	 *		Flujo de entrada de bytes. 
	 */

	public Controlador(Mundo mundo, Scanner in) {
		this.simulacionTerminada = false;
		this.mundo = mundo;
		this.in = in;
	}
	
	/**
	 * Se encarga de cargar el mundo de un fichero con el nombre correspondiente. Es posible que este metodo 
	 * reciba y coga cuatro excepciones la de formato numerico incorrecto, la de indices fuera de rango, la de archivo no encontrado 
	 * y la de palabra incorrecta. 
	 * 
	 * @param nombreFichero
	 * 		Nombre del fichero desde el cual se desea cargar el mundo.
	 * 
	 */

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

	/**
	 * Se encarga de guardar el mundo "actual" es decir el que este en juego en ese momento, en su correpondiente formato,
	 * sea simple o complejo respectivamente. 
	 * Es posible que este metodo reciba y coga dos excepciones, la de indices fuera de rango y la de IOException. 
	 *  
	 * @param nombreFichero
	 * 	Nombre del fichero en el que se desea guardar el juego. 
	 */
	
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
	
	/**
	 * Se encarga de realizar la simulación pidiendo los comandos al usuario y ejecutandolos de la manera correspondiente
	 * con el tipo de juego que este en funcionamiento sea simple o sea complejo. Es posible que este metodo reciba y coga
	 * dos excepciones, la de error comando y la de comando no existente. 
	 */

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

	/**
	 * Capta como entrada los comandos que el usuario desea utilizar.
	 * @return
	 * 	Devuelve la cadena de strings que contendra un comando. 
	 */
	
	private String[] captarEntrada() {
		String[] words = null;
		System.out.print("Comando > ");
		String entrada = in.nextLine();
		words = entrada.split(" ");
		return words;
	}
	
	/**
	 * Modifica el atributo del controlador recibiendo el valor del parametro.
	 * 
	 * @param simulacionTerminada
	 * 	Recibe por parametro un booleano correspondiente a si la simulación esta terminada. 
	 */

	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}
	
	/**
	 * Vacia el mundo correspondiente.
	 */

	public void vaciar() {
		this.mundo.vaciar();
	}
	
	/**
	 * Realiza un ciclo vital en el mundo correspondiente y las consecuencias que eso conlleva.
	 */

	public void evoluciona() {
		this.mundo.evoluciona();
	}
	
	/**
	 * Inicializa el mundo correspondiente.
	 */

	public void inicializar() {
		this.mundo.inicializaMundo();
	}
	
	/**
	 * Intentara eliminar la celula de la casilla especificada por parametro, y en el caso de que sea posible lo hara. 
	 * @param casilla
	 * 	Casilla correspondiente en la que se eliminara la celula en el caso de que la haya. 
	 * @throws IndicesFueraDeRango
	 * Lanzara la excepcion de que los indices de la casilla estan fuera de rango.
	 * @throws PosicionVacia
	 * Lanzara la excepcion de que la casilla no tiene ninguna celula en su interior. 
	 */

	public void eliminarCelula(Casilla casilla) throws IndicesFueraDeRango, PosicionVacia {
		if (!this.mundo.eliminarCelula(casilla))
			throw new PosicionVacia();
	}
	
	/**
	 * Intentara crear la celula de la casilla especificada por parametro, y en el caso de que sea posible lo hara
	 * @param casilla
	 * 	Casilla correspondiente en la que se crear la celula en el caso de que la haya.
	 * @param celula
	 * 	Celula que se deseara crear. 
	 * @throws IndicesFueraDeRango
	 * Lanzara la excepcion de que los indices de la casilla estan fuera de rango.
	 * @throws PosicionNoVacia
	 * Lanzara la excepcion de que la casilla no tiene ninguna celula en su interior.
	 */

	public void crearCelula(Casilla casilla, Celula celula) throws IndicesFueraDeRango, PosicionNoVacia {
		if (!this.mundo.crearCelula(casilla, celula))
			throw new PosicionNoVacia();
	}

	/**
	 * Actualiza el mundo y lo modifica a aquel que se ha enviado por parametro. Cambiando de simple a complejo o viceversa.
	 * @param mundo
	 * Contiene el mundo al que se desea cambiar. 
	 */
	
	public void juega(Mundo mundo) {
		this.mundo = mundo;
	}
	
	/**
	 * Pide al usuario que tipo de celula desea crear si simple o compleja.  
	 * @return
	 * Devuelve un numero entre 1 o 2 que estan asignados a celula simple y compleja respectivamente. 
	 * @throws FormatoNumericoIncorrecto
	 * Lanza la excepcion de formato numerico incorecto, si se introduce un caracter que no es o 1 o 2. 
	 */

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
	
	/**
	 * Comrpueba la clase de mundo ya que al ser una clase abstracta, puede ser Mundo Simple o Complejo. 
	 * @return
	 * Devuelve la clase correspondiente. 
	 */

	public boolean getComplejidad() {
		return this.mundo.getClass() == MundoSimple.class;
	}
	
	/**
	 * Decide que tipo de mundo se esta cargando para informar de ello al controlador. 
	 * @param fich
	 * Pasa por parametro el flujo de entrada de bytes que esta usandose en la carga. 
	 * @return
	 * Devuelve el correspondiente que se este cargando. 
	 * @throws PalabraIncorrecta
	 * Lanza una excepcion de palabra incorrecta si la palabra que se lee no es ni simple ni complejo. 
	 */

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
