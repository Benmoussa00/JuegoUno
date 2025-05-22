package juego;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import juego.ThreadEsperarMovimiento;

public class SocketServidor {
	
	private ServerSocket server;
    private Socket oponente;
    private ThreadEsperarMovimiento hiloLeer;
    private volatile boolean running;
    
    private Partida partida;
    FramePartida frameJugador1 = new FramePartida();

    /* Puerto constante */
    private static final int PORT = 5005;
    
    public SocketServidor() {
        this.running = false;
    }
    
    
    public void actualizarMazoJugador1(List<Carta> mazoJugador1) {
    	partida.setMazoJugador(mazoJugador1);
    	
    	partida.actualizarMazoJugadorEnFrame();
    }
    
    public void actualizarMazosJugador1(List<Carta> disponibles, List<Carta> tiradas, int turno) {
    	
    	System.out.println("Ejecutado el metodo para actualizar mazo en el servidor");
    	
    	partida.setDisponibles(disponibles);
    	partida.setTiradas(tiradas);
    	partida.setTurno(turno);
    	
    	frameJugador1.setTextoTurno(partida.esMiTurno());
    	
    	SwingUtilities.invokeLater(() -> {
            partida.actualizarPilaDescarteFrame();
            partida.actualizarMazoJugadorEnFrame();
        });
    	
    }
    
    public void robarCarta() {
    	partida.anadirCartaMazoJugador(partida.robarCarta());
    	System.out.println("Robando carta");
    	partida.sincronizarDatosConOponente();
    }
    
    public void empezarServidor() {
        running = true;
        new Thread(() -> {
            try {
                server = new ServerSocket(PORT);
                
                System.out.println("\n[Servidor iniciado. Esperando cliente...]");
                
                oponente = server.accept();
                hiloLeer = new ThreadEsperarMovimiento(oponente, this);
                hiloLeer.start();
                
                partida = new Partida(oponente, frameJugador1, true);

                System.out.println("\n[Cliente conectado: " + oponente.getInetAddress() + "]");
                
                frameJugador1.setSocketServidor(this);
                frameJugador1.setVisible(true);
                FrameMenu.getInstance().dispose();
                
              //TODO: ELIMINAR
                frameJugador1.getDebugLabel().setText("SERVIDOR");
                
                partida.generarCartas();
                
                partida.repartirCartas();
                
                partida.tirarCartaInicial();
                
                frameJugador1.setTextoTurno(true);
                
                actualizarMazoJugador1(partida.getMazoJugador());
                
                partida.sincronizarDatosConOponente();
                
            } catch (IOException e) {
                if (running) {
                    System.out.println("\n[Error en servidor: " + e.getMessage() + "]");
                }
            }
        }).start();
    }
    
    
    public void StopServer() {
        running = false;
        try {
            // Detener el hilo de lectura
            if (hiloLeer != null) {
                hiloLeer.interrupt();
            }
            
            // Cerrar sockets
            if (oponente != null && !oponente.isClosed()) {
            	oponente.close();
            }
            if (server != null && !server.isClosed()) {
                server.close();
            }
            
//            SwingUtilities.invokeLater(() -> 
//                txtrConversacion.append("\n[Servidor detenido]"));
        } catch (IOException e) {
//            SwingUtilities.invokeLater(() -> 
//                txtrConversacion.append("\n[Error al detener servidor: " + e.getMessage() + "]"));
        }
    }
    

    /**
     * Verifica si el servidor está conectado
     * @return true si el servidor está activo y conectado
     */
    public boolean estaConectado() {
        return running && server != null && !server.isClosed();
    }

    /**
     * Crea un nuevo ServerSocket
     * @return El ServerSocket creado
     */
    public static ServerSocket crearServer() {
        try {
            return new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Error al crear servidor: " + e.getMessage());
            return null;
        }
    }

    /**
     * Acepta una conexión de cliente
     * @param server El ServerSocket que aceptará la conexión
     * @return El Socket del cliente conectado
     */
    public static Socket aceptarCliente(ServerSocket server) {
        try {
            return server.accept();
        } catch (IOException e) {
            System.err.println("Error al aceptar cliente: " + e.getMessage());
            return null;
        }
    }
	
}
