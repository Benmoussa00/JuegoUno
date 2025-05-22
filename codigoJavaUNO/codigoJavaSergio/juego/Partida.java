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
	
	
	private FramePartida frameJugador;
	
	private ObjectOutputStream output;
	
	private boolean soyServidor;
	private int turno = 0;
	
	public Partida(Socket oponente, FramePartida frameJugador, boolean soyServidor) throws IOException {
		this.oponente = oponente;
		this.frameJugador = frameJugador;
		this.soyServidor = soyServidor;
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
	
	public int getTurno() {
		return turno;
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
	
	public void setTurno(int turno) {
		this.turno = turno;
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
			disponibles.add(new Carta("SaltoTurno", "Rojo", getClass().getResource("Cards/SaltoRojo.png")));
			disponibles.add(new Carta("SaltoTurno", "Amarillo", getClass().getResource("Cards/SaltoAmarillo.png")));
			disponibles.add(new Carta("SaltoTurno", "Verde", getClass().getResource("Cards/SaltoVerde.png")));
			disponibles.add(new Carta("SaltoTurno", "Azul", getClass().getResource("Cards/SaltoAzul.png")));
			disponibles.add(new Carta("Chupate2", "Rojo", getClass().getResource("Cards/Chupate2Rojo.png")));
			disponibles.add(new Carta("Chupate2", "Amarillo", getClass().getResource("Cards/Chupate2Amarillo.png")));
			disponibles.add(new Carta("Chupate2", "Verde", getClass().getResource("Cards/Chupate2Verde.png")));
			disponibles.add(new Carta("Chupate2", "Azul", getClass().getResource("Cards/Chupate2Azul.png")));
		}
		
		for (int i = 0; i < 4; i++) {
			disponibles.add(new Carta("Comodin", "Multicolor", getClass().getResource("Cards/Comodin.png")));
			disponibles.add(new Carta("Chupate4", "Multicolor", getClass().getResource("Cards/Chupate4.png")));
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
				tiradas.add(cartaInicial);
				mazoJugador.remove(cartaInicial);
				
				frameJugador.getlblPilaDescarte().setIcon(new ImageIcon(cartaInicial.getUrl()));
				frameJugador.getlblPilaDescarte().revalidate(); // Actualiza el layout
				frameJugador.getlblPilaDescarte().repaint();
				
				sincronizarDatosConOponente();
		        actualizarMazoJugadorEnFrame();
				repartido = true;
			}
		}
	}
	
	public void actualizarMazoJugadorEnFrame() {
		
		frameJugador.getPanelBaraja().removeAll();
		frameJugador.getPanelBaraja().revalidate(); // Actualiza el layout
		frameJugador.getPanelBaraja().repaint();

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
			    }
			});
			
			System.out.println("Actualizando mazo en el frame");
			frameJugador.getPanelBaraja().add(cartaLabel);
			frameJugador.getPanelBaraja().revalidate(); // Actualiza el layout
			frameJugador.getPanelBaraja().repaint();
			
		}
	}
	
	public void tirarCarta(Carta carta) {
		
		if(esMiTurno()) {
			Carta ultimaCarta = tiradas.get(tiradas.size() - 1);
			
			tiradas.add(carta);
			mazoJugador.remove(carta);
			
			turno++;
			
			frameJugador.getlblPilaDescarte().setIcon(new ImageIcon(carta.getUrl()));
			frameJugador.getlblPilaDescarte().revalidate(); // Actualiza el layout
			frameJugador.getlblPilaDescarte().repaint();
			
			frameJugador.setTextoTurno(esMiTurno());
			
			sincronizarDatosConOponente();
	        actualizarMazoJugadorEnFrame();
		}
		
//		if(carta.getTipo() == ultimaCarta.getTipo() || carta.getTipo() == ultimaCarta.getColor()) {
//			tiradas.add(carta);
//			mazoJugador.remove(carta);
//			
//			frameJugador.getlblPilaDescarte().setIcon(new ImageIcon(carta.getUrl()));
//			frameJugador.getlblPilaDescarte().revalidate(); // Actualiza el layout
//			frameJugador.getlblPilaDescarte().repaint();
//			
//			sincronizarMazosDelOponente();
//	        actualizarMazoJugadorEnFrame();
//		}
	}
	
	public void actualizarPilaDescarteFrame() {
		
		frameJugador.getlblPilaDescarte().setIcon(new ImageIcon(tiradas.get(tiradas.size() - 1).getUrl()));
		frameJugador.getlblPilaDescarte().revalidate(); // Actualiza el layout
		frameJugador.getlblPilaDescarte().repaint();
	}
	
	public void sincronizarDatosConOponente() {
		try {
            if (oponente != null && !oponente.isClosed()) {
            	
            	HashMap<String, Object> datos = new HashMap<>();
            	datos.put("disponibles", disponibles);
            	datos.put("tiradas",    tiradas);
            	datos.put("turno",    turno);
            	
            	output.writeObject(datos);
                output.flush();
                output.reset();
            }
        } catch (IOException e) {
            System.out.println("\n[Error al enviar mensaje: " + e.getMessage() + "]");
    }
	}
	
	public Carta robarCarta() {
		if (esMiTurno()) {
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
		    }
		});
		
		System.out.println("Actualizando mazo en el frame");
		frameJugador.getPanelBaraja().add(cartaLabel);
		frameJugador.getPanelBaraja().revalidate(); // Actualiza el layout
		frameJugador.getPanelBaraja().repaint();
	}
	
	public boolean esMiTurno() {
		if (turno%2 == 0) {
			if (soyServidor) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (soyServidor) {
				return false;
			}
			else {
				return true;
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
	