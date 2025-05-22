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
					HashMap<String, Object> mazosRecibidos =
                        (HashMap<String, Object>) datosRecibidos;
                    
                    Object disponiblesObj = mazosRecibidos.get("disponibles");
                    Object tiradasObj = mazosRecibidos.get("tiradas");
                    Object turnoObj = mazosRecibidos.get("turno");

                    if (disponiblesObj instanceof List<?> 
                        && tiradasObj instanceof List<?> 
                        && turnoObj instanceof Integer) {
                        
                        List<Carta> disponibles = (List<Carta>) disponiblesObj;
                        List<Carta> tiradas = (List<Carta>) tiradasObj;
                        int turno = (Integer) turnoObj;
                        
                        if (selfC != null) {
                            
                            selfC.actualizarMazosJugador2(disponibles, tiradas, turno);
                        }
                        else if (selfS != null) {

                            selfS.actualizarMazosJugador1(disponibles, tiradas, turno);
                        }
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
