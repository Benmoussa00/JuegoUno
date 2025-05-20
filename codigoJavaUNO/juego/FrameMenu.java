package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FrameMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static FrameMenu instance;
	private JTextField txtIp;
	private JButton btnConectarse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMenu frame = new FrameMenu();
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
	public FrameMenu() {
		instance = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUno = new JLabel("UNO");
		lblUno.setFont(new Font("Dialog", Font.BOLD, 30));
		lblUno.setBounds(350, 37, 79, 91);
		contentPane.add(lblUno);
		
		JButton btnJugar = new JButton("Iniciar Servidor");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new FramePartida().setVisible(true);
				FrameMenu.this.dispose();
				
				SocketServidor servidor = new SocketServidor();
                servidor.empezarServidor();
                
                btnJugar.setEnabled(false);
                btnConectarse.setEnabled(false);
			}
		});
		btnJugar.setBounds(319, 166, 135, 27);
		contentPane.add(btnJugar);
		
		JButton btnEstadisticas = new JButton("Mis Estadisticas");
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameEstadisticas().setVisible(true);
			}
		});
		btnEstadisticas.setBounds(319, 270, 135, 27);
		contentPane.add(btnEstadisticas);
		
		JButton btnMiPerfil = new JButton("Mi Perfil");
		btnMiPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FramePerfil().setVisible(true);
			}
		});
		btnMiPerfil.setBounds(319, 370, 135, 27);
		contentPane.add(btnMiPerfil);
		
		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameRanking().setVisible(true);
			}
		});
		btnRanking.setBounds(319, 321, 135, 27);
		contentPane.add(btnRanking);
		
		txtIp = new JTextField();
		txtIp.setToolTipText("Introduce la ip del servidor");
		txtIp.setBounds(319, 205, 136, 27);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ip = txtIp.getText().trim();

				String ipRegex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

				if (!ip.isEmpty() && ip.matches(ipRegex)) {
					
					btnJugar.setEnabled(false);
					btnConectarse.setEnabled(false);
					
					SocketCliente cliente = new SocketCliente(txtIp.getText());
	                cliente.ConectarCliente();
					
					new FramePartida().setVisible(true);
					FrameMenu.this.dispose();
					
				}
				else {
					
					JOptionPane.showMessageDialog(FrameMenu.this,
                            "IP no valida, introduce una IP valida.",
                            "Introduce una ip valida.",
                            JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		btnConectarse.setBounds(319, 231, 135, 27);
		contentPane.add(btnConectarse);
		
	}
	
	public static FrameMenu getInstance() {
        return instance;
    }
}
