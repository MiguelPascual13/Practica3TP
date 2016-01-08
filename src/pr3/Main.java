package pr3;

import java.util.Scanner;

import pr3.control.Controlador;
import pr3.excepciones.ErrorDeInicializacion;
import pr3.logica.mundo.MundoSimple;

public class Main {
	public static void main(String[] args) {
		try {
			MundoSimple mundo = new MundoSimple(3, 3, 3);
			Scanner in = new Scanner(System.in);
			Controlador control = new Controlador(mundo, in);
			control.realizaSilulacion();
		} catch (ErrorDeInicializacion e) {
			System.out.println(e);
		}
	}
}
