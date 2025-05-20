package juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ThreadEsperarMovimiento extends Thread {

	Socket oponente;
    
    public ThreadEsperarMovimiento(Socket client) {
        this.oponente = client;
    }
    
    public void run() {
        try (BufferedReader input = new BufferedReader(
            new InputStreamReader(oponente.getInputStream()))) {
            
            while (!isInterrupted() && !oponente.isClosed()) {
                String line = input.readLine();
                if (line == null) break;
                
                SwingUtilities.invokeLater(() -> {
//                    txtrConversacion.append("\nCliente: " + line);
//                    txtrConversacion.setCaretPosition(txtrConversacion.getDocument().getLength());
                });
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
