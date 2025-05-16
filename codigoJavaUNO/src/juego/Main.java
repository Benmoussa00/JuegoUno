package juego;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner t = new Scanner(System.in);
		Partida p = new Partida();
		
		System.out.println("Nombre del jugador 1: ");
		String j1 = t.nextLine();
		System.out.println("\nNombre del jugador 2: ");
		String j2 = t.nextLine();
		
		p.jugar(j1, j2);
		
	}

}