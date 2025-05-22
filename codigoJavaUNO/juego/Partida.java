package juego;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private List<Carta> mazoJugador = new LinkedList<Carta>();
	
	private Socket oponente;
	
	private JLabel cartaPila;
	private JPanel panel_baraja;
	
	private ObjectOutputStream output;
	
	public Partida(Socket oponente, JLabel cartaPila, JPanel panel_baraja) throws IOException {
		this.oponente = oponente;
		this.cartaPila = cartaPila;
		this.panel_baraja = panel_baraja;
		this.output = new ObjectOutputStream(oponente.getOutputStream());
	}
	
	public List<Carta> getDisponibles(){
		return disponibles;
	}
	
	public List<Carta> getTiradas(){
		return tiradas;
	}
	
	public List<Carta> getMazoJugador(){
		return mazoJugador;
	}
	
	
	public void setDisponibles(List<Carta> disponibles) {
		this.disponibles = disponibles;
	}
	
	public void setTiradas(List<Carta> tiradas) {
		this.tiradas = tiradas;
	}
	
	public void setMazoJugador(List<Carta> mazoJugador1) {
		this.mazoJugador = mazoJugador1;
	}
	
	public void setCartaPila(JLabel cartaPila) {
		this.cartaPila = cartaPila;
	}
	
	public void setPanel_Baraja(JPanel panel_baraja) {
		this.panel_baraja = panel_baraja;
	}
	
	
	public void generarCartas() {
		
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
		
		for (int i = 0; i < 7; i++) {
			Carta cartaARepartir1 = disponibles.get(r.nextInt(disponibles.size()));
			mazoJugador.add(cartaARepartir1);
			disponibles.remove(cartaARepartir1);
		}
	}
	
	public void tirarCartaInicial() {
		Random r = new Random();
		boolean repartido = false;
		
		Carta cartaInicial = disponibles.get(r.nextInt(disponibles.size()));
		
		while (!repartido) {
			cartaInicial = disponibles.get(r.nextInt(disponibles.size()));
			if (!cartaInicial.getTipo().equals("Salto de turno") && !cartaInicial.getTipo().equals("Chúpate 2") && !cartaInicial.getTipo().equals("Comodín") && !cartaInicial.getTipo().equals("Chúpate 4")) {
				tirarCarta(cartaInicial);
				repartido = true;
			}
		}
	}
	
	public void actualizarMazoJugadorEnFrame() {
		
		panel_baraja.removeAll();
		panel_baraja.revalidate(); // Actualiza el layout
	    panel_baraja.repaint();

		for (int i = 0; i < mazoJugador.size(); i++) {
			
			Carta cartaAnadir = mazoJugador.get(i);
			
			JLabel cartaLabel = new JLabel();
			cartaLabel.setIcon(new ImageIcon(cartaAnadir.getUrl()));
			cartaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cartaLabel.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        System.out.println("click en una carta del mazo");
			        Carta estaCarta = cartaAnadir;
			        tirarCarta(estaCarta);
			        sincronizarMazosDelOponente();
			    }
			});
			
			System.out.println("Actualizando mazo en el frame");
			panel_baraja.add(cartaLabel);
			panel_baraja.revalidate(); // Actualiza el layout
		    panel_baraja.repaint();
			
		}
	}
	
	public void tirarCarta(Carta carta) {
		
		tiradas.add(carta);
		mazoJugador.remove(carta);
		
		cartaPila.setIcon(new ImageIcon(carta.getUrl()));
		cartaPila.revalidate(); // Actualiza el layout
		cartaPila.repaint();
		
		actualizarMazoJugadorEnFrame();
	}
	
	public void actualizarPilaDescarteFrame() {
		
		cartaPila.setIcon(new ImageIcon(tiradas.get(tiradas.size() - 1).getUrl()));
		cartaPila.revalidate(); // Actualiza el layout
		cartaPila.repaint();
	}
	
	public void sincronizarMazosDelOponente() {
		try {
            if (oponente != null && !oponente.isClosed()) {
            	
            	HashMap<String,List<Carta>> mazos = new HashMap<>();
            	mazos.put("disponibles", disponibles);
            	mazos.put("tiradas",    tiradas);
            	
            	output.writeObject(mazos);
                output.flush();
                output.reset();
            }
        } catch (IOException e) {
            System.out.println("\n[Error al enviar mensaje: " + e.getMessage() + "]");
    }
	}
	
	public Carta robarCarta() {
		if (!disponibles.isEmpty()){
			Random r = new Random();
			Carta cartaRobada = disponibles.get(r.nextInt(disponibles.size()));
			disponibles.remove(cartaRobada);
			System.out.println("Carta robada");
			return cartaRobada;
		}
		else {
			return null;
		}
	}
	
	public void anadirCartaMazoJugador(Carta carta) {
		mazoJugador.add(carta);
		
		JLabel cartaLabel = new JLabel();
		cartaLabel.setIcon(new ImageIcon(carta.getUrl()));
		cartaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cartaLabel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("click en una carta del mazo");
		        Carta estaCarta = carta;
		        tirarCarta(estaCarta);
		        sincronizarMazosDelOponente();
		        actualizarMazoJugadorEnFrame();
		    }
		});
		
		System.out.println("Actualizando mazo en el frame");
		panel_baraja.add(cartaLabel);
		panel_baraja.revalidate(); // Actualiza el layout
	    panel_baraja.repaint();
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
	