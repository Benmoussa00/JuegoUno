package juego;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import juego.ThreadEsperarMovimiento;

public class SocketCliente {
	private final static int PORT =5005;

    private String SERVER = "";
    
    private Socket server;
    
    public SocketCliente(String SERVER) {
    	this.SERVER = SERVER;
    }
    
    public void ConectarCliente() {
    	boolean exit=false;
        
        server = crearSocket();
        
        ThreadEsperarMovimiento hiloLeer = new ThreadEsperarMovimiento(server);
        hiloLeer.start();

        System.out.println("\n[Servidor iniciado. Esperando cliente...]");
        System.out.println("\n[Cliente conectado: " + server.getInetAddress() + "]");
    }
    
    public void enviarMovimiento(String mensaje) {
        try {
            if (server != null && !server.isClosed()) {
                PrintStream output = new PrintStream(server.getOutputStream());
                output.println(mensaje);
                output.flush();
                SwingUtilities.invokeLater(() -> {
                	System.out.println("\nYo (Cliente): " + mensaje);
                });
            }
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
            SwingUtilities.invokeLater(() -> 
            	System.out.println("\nError al enviar mensaje"));
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
}
