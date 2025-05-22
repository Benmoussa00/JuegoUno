package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FramePartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JPanel panel_baraja;
	private JLabel lblDebugLabel;
	
	private JLabel lblPilaDescarte;
	
	private SocketCliente socketCliente = null;
	private SocketServidor socketServidor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePartida frame = new FramePartida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePartida() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 794);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_robar = new JPanel();
		panel_robar.setBounds(89, 82, 154, 197);
		contentPane.add(panel_robar);
		
		JLabel lblPilaDeRobo = new JLabel("");
		lblPilaDeRobo.setIcon(new ImageIcon(FramePartida.class.getResource("/juego/Cards/cartaPorDetras.png")));
		lblPilaDeRobo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_robar.add(lblPilaDeRobo);
		
		lblPilaDeRobo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	System.out.println("click en pila de robo");
		    	
				if (socketCliente != null) {
					socketCliente.robarCarta();
					System.out.println("robando carta en el cliente");
				}
				else if(socketServidor != null) {
					socketServidor.robarCarta();
					System.out.println("robando carta en el server");
				}
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(49, 362, 659, 387);
		contentPane.add(scrollPane);
		
		panel_baraja = new JPanel();
		scrollPane.setViewportView(panel_baraja);
		panel_baraja.setLayout(new GridLayout(2, 99, 60, 5));
		
		lblDebugLabel = new JLabel("ServidorOCliente");
		lblDebugLabel.setBounds(325, 12, 114, 17);
		contentPane.add(lblDebugLabel);
		
		JPanel panel_pila_descarte = new JPanel();
		panel_pila_descarte.setBounds(519, 82, 154, 197);
		contentPane.add(panel_pila_descarte);
		
		lblPilaDescarte = new JLabel("");
		panel_pila_descarte.add(lblPilaDescarte);
	}
	
	public JPanel getPanelBaraja() {
        return panel_baraja;
    }
	
	public JLabel getDebugLabel() {
		return lblDebugLabel;
	}
	
	public JLabel getlblPilaDescarte() {
		return lblPilaDescarte;
	}
	
	public void setlblPilaDescarte() {
		
	}
	
	public void setSocketCliente(SocketCliente socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	public void setSocketServidor(SocketServidor socketServidor) {
		this.socketServidor = socketServidor;
	}
	
}
