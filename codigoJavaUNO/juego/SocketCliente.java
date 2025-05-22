package juego;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import juego.ThreadEsperarMovimiento;

public class SocketCliente {
	private final static int PORT =5005;

    private String SERVER = "";
    private Socket server;
    
    private boolean repartoInicialHecho = false;
    
    private Partida partida;
    FramePartida frameJugador2 = new FramePartida();
    
    public SocketCliente(String SERVER) {
    	this.SERVER = SERVER;
    }
    
    public void ConectarCliente() {
        server = crearSocket();

        System.out.println("\n[Cliente conectado: " + server.getInetAddress() + "]");
        
        ThreadEsperarMovimiento hiloLeer = new ThreadEsperarMovimiento(server, this);
        hiloLeer.start();
        
        try {
			partida = new Partida(server, frameJugador2.getlblPilaDescarte(), frameJugador2.getPanelBaraja());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        frameJugador2.setSocketCliente(this);
        frameJugador2.setVisible(true);
    	FrameMenu.getInstance().dispose();
    
    	
    	//TODO: ELIMINAR
    	frameJugador2.getDebugLabel().setText("CLIENTE");
    	
    	
    }
    
    public void enviarMovimiento(String mensaje) {
        try {
            if (server != null && !server.isClosed()) {
                PrintStream output = new PrintStream(server.getOutputStream());
                output.println(mensaje);
                output.flush();
                
                System.out.println("\nYo (Cliente): " + mensaje);
            }
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
            
            System.out.println("\nError al enviar mensaje");
        }
    }
    public boolean estaConectado() {
        return server != null && !server.isClosed();
    }
    
    public void cerrarSocket() {
    	try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Socket crearSocket() {
    	try {
			return new Socket(SERVER, PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("\n[Error en servidor: " + e.getMessage() + "]");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("\n[Error en servidor: " + e.getMessage() + "]");
		}
    	return null;
    }
    
    public void actualizarMazosJugador2(List<Carta> disponibles, List<Carta> tiradas) {
    	
    	System.out.println("Ejecutado el metodo para actualizar mazo en el cliente");
    	
    	partida.setDisponibles(disponibles);
    	partida.setTiradas(tiradas);

    	if (!repartoInicialHecho) {
    		partida.repartirCartas();
    		partida.sincronizarMazosDelOponente();
    		repartoInicialHecho = true;
    	}
    	
    	SwingUtilities.invokeLater(() -> {
            partida.actualizarPilaDescarteFrame();
            partida.actualizarMazoJugadorEnFrame();
        });
    	
    }
    
    public void robarCarta() {
    	partida.anadirCartaMazoJugador(partida.robarCarta());
    	partida.sincronizarMazosDelOponente();
    }
}
