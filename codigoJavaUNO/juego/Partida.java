package juego;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Partida {
	
	private List<Carta> disponibles = new LinkedList<Carta>();
	private List<Carta> tiradas = new LinkedList<Carta>();
	
	private List<Carta> jugador1 = new LinkedList<Carta>();
	private List<Carta> jugador2 = new LinkedList<Carta>();
	
	
	public void generarCartas(LinkedList<Carta> disponibles) {
		
		for (int i = 0; i < 10; i++) {
			Integer in = i;
			String j = in.toString();
			
			disponibles.add(new Carta(j, "Rojo", getClass().getResource("Cards/" + j + "Rojo.png")));
			disponibles.add(new Carta(j, "Amarillo", getClass().getResource("Cards/" + j + "Amarillo.png")));
			disponibles.add(new Carta(j, "Verde", getClass().getResource("Cards/" + j + "Verde.png")));
			disponibles.add(new Carta(j, "Azul", getClass().getResource("Cards/" + j + "Azul.png")));
		}
		
		for (int i = 1; i < 10; i++) {
			Integer in = i;
			String j = in.toString();
			
			disponibles.add(new Carta(j, "Rojo", getClass().getResource("Cards/" + j + "Rojo.png")));
			disponibles.add(new Carta(j, "Amarillo", getClass().getResource("Cards/" + j + "Amarillo.png")));
			disponibles.add(new Carta(j, "Verde", getClass().getResource("Cards/" + j + "Verde.png")));
			disponibles.add(new Carta(j, "Azul", getClass().getResource("Cards/" + j + "Azul.png")));
		}
		
		for (int i = 0; i < 2; i++) {
			disponibles.add(new Carta("Salto de turno", "Rojo", getClass().getResource("Cards/SaltoRojo.png")));
			disponibles.add(new Carta("Salto de turno", "Amarillo", getClass().getResource("Cards/SaltoAmarillo.png")));
			disponibles.add(new Carta("Salto de turno", "Verde", getClass().getResource("Cards/SaltoVerde.png")));
			disponibles.add(new Carta("Salto de turno", "Azul", getClass().getResource("Cards/SaltoAzul.png")));
			disponibles.add(new Carta("Chúpate 2", "Rojo", getClass().getResource("Cards/Chupate2Rojo.png")));
			disponibles.add(new Carta("Chúpate 2", "Amarillo", getClass().getResource("Cards/Chupate2Amarillo.png")));
			disponibles.add(new Carta("Chúpate 2", "Verde", getClass().getResource("Cards/Chupate2Verde.png")));
			disponibles.add(new Carta("Chúpate 2", "Azul", getClass().getResource("Cards/Chupate2Azul.png")));
		}
		
		for (int i = 0; i < 4; i++) {
			disponibles.add(new Carta("Comodín", "Multicolor", getClass().getResource("Cards/Comodin.png")));
			disponibles.add(new Carta("Chúpate 4", "Multicolor", getClass().getResource("Cards/Chupate4.png")));
		}
	}
	
	
	
	public void repartirCartas() {
		Random r = new Random();
		boolean repartido = false;
		
		for (int i = 0; i < 7; i++) {
			Carta cartaARepartir1 = disponibles.get(r.nextInt(disponibles.size()));
			Carta cartaARepartir2 = disponibles.get(r.nextInt(disponibles.size()));
			jugador1.add(cartaARepartir1);
			disponibles.remove(cartaARepartir1);
			jugador2.add(cartaARepartir2);
			disponibles.remove(cartaARepartir2);
		}
		
		Carta cartaInicial = disponibles.get(r.nextInt(disponibles.size()));
		
		while (!repartido) {
			cartaInicial = disponibles.get(r.nextInt(disponibles.size()));
			if (!cartaInicial.getTipo().equals("Salto de turno") && !cartaInicial.getTipo().equals("Chúpate 2") && !cartaInicial.getTipo().equals("Comodín") && !cartaInicial.getTipo().equals("Chúpate 4")) {
				tiradas.add(cartaInicial);
				disponibles.remove(cartaInicial);
				repartido = true;
			}
		}
	}
} 
	
//	public void asignarCartas(JPanel panel_baraja){
//		Partida.generarCartas
//		jugador1.clear();
//		Partida.repartirCartas(jugador1, disponibles);		
//		JLabel lblCarta = new JLabel();
//		lblCarta.setBounds(23, 28, 157, 208);
//		
//		for (int i = 0; i < 7; i++) {
//			
//			JLabel carta = new JLabel();
//			carta.setIcon(new ImageIcon(jugador1.get(i).getUrl()));
//			
//			panel_baraja.add(carta);
//			panel_baraja.revalidate(); // Actualiza el layout
//		    panel_baraja.repaint();
//		}
//
//
//		
//		System.out.println("OKEY");
//	}
	