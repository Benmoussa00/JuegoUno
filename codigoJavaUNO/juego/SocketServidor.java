package juego;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

    /* Puerto constante */
    private static final int PORT = 5005;
    
    public SocketServidor() {
        this.running = false;
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


    public void enviarMovimiento(String mensaje) {
        try {
            if (oponente != null && !oponente.isClosed()) {
                PrintStream output = new PrintStream(oponente.getOutputStream());
                output.println(mensaje);
                output.flush();
                
//                SwingUtilities.invokeLater(() -> {
//                    txtrConversacion.append("\nYo (Servidor): " + mensaje);
//                    txtrConversacion.setCaretPosition(txtrConversacion.getDocument().getLength());
//                });
            }
        } catch (IOException e) {
//            SwingUtilities.invokeLater(() -> 
//                txtrConversacion.append("\n[Error al enviar mensaje: " + e.getMessage() + "]"));
        }
    }


    public void empezarServidor() {
        running = true;
        new Thread(() -> {
            try {
                server = new ServerSocket(PORT);
//                SwingUtilities.invokeLater(() -> 
//                    txtrConversacion.append("\n[Servidor iniciado. Esperando cliente...]"));
                
                oponente = server.accept();
                hiloLeer = new ThreadEsperarMovimiento(oponente);
                hiloLeer.start();
                
//                SwingUtilities.invokeLater(() -> 
//                    txtrConversacion.append("\n[Cliente conectado: " + oponente.getInetAddress() + "]"));
            } catch (IOException e) {
                if (running) { // Solo mostrar error si no fue una parada intencional
//                    SwingUtilities.invokeLater(() -> 
//                        txtrConversacion.append("\n[Error en servidor: " + e.getMessage() + "]"));
                }
            }
        }).start();
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
