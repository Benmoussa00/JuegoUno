package juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ThreadEsperarMovimiento extends Thread {

	Socket oponente;
	SocketServidor selfS = null;
	SocketCliente selfC = null;
    
    public ThreadEsperarMovimiento(Socket oponente, SocketServidor selfS) {
        this.oponente = oponente;
        this.selfS = selfS;    
    }
    
    public ThreadEsperarMovimiento(Socket oponente, SocketCliente selfC) {
        this.oponente = oponente;
        this.selfC = selfC;    
    }
    
    public void run() {
    	
    	System.out.println("Ha empezado el hilo EsperarMovimientos");
    	
    	try (ObjectInputStream input = new ObjectInputStream(oponente.getInputStream())) {
    		
    		System.out.println("InputStream Empezado");
            
            while (!isInterrupted() && !oponente.isClosed()) {
            	Object datosRecibidos = null;
				try {
					datosRecibidos = input.readObject();
					System.out.println("Datos recibidos por el cliente");
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
                if (datosRecibidos == null) break;
                
                if (datosRecibidos instanceof HashMap<?,?>) {
                    @SuppressWarnings("unchecked")
					HashMap<String, List<Carta>> mazosRecibidos =
                        (HashMap<String, List<Carta>>) datosRecibidos;
                	
                	System.out.println("Mazo recibido por el cliente");
                    
                    if (selfC != null && !mazosRecibidos.isEmpty()) {
                        
                    	List<Carta> disponibles = mazosRecibidos.get("disponibles");
                    	List<Carta> tiradas = mazosRecibidos.get("tiradas");

                        selfC.actualizarMazosJugador2(disponibles, tiradas);
                    }
                    else if (selfS != null && !mazosRecibidos.isEmpty()) {
                    	List<Carta> disponibles = mazosRecibidos.get("disponibles");
                    	List<Carta> tiradas = mazosRecibidos.get("tiradas");

                        selfS.actualizarMazosJugador1(disponibles, tiradas);
                    }
                }
            }
        } catch (IOException e) {
            if (!isInterrupted()) {
                SwingUtilities.invokeLater(() -> {
//                    txtrConversacion.append("\n[Connection error: " + e.getMessage() + "]"));
            });
            }
        } finally {
            try {
                if (oponente != null && !oponente.isClosed()) {
                	oponente.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
