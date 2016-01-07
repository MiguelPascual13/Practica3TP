package pr3;

import java.util.Scanner;

import pr3.control.Controlador;
import pr3.logica.MundoSimple;

public class Main {
	public static void main(String[] args) {
		MundoSimple mundo = new MundoSimple();
		Scanner in = new Scanner(System.in);
		Controlador control = new Controlador(mundo, in);
		control.realizaSilulacion();
	}
}
