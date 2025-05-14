package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrameLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCorreo;
	private JTextField textFieldContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
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
	public FrameLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Iniciar Sesión");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 30));
		lblLogin.setBounds(294, 0, 340, 48);
		contentPane.add(lblLogin);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(285, 106, 45, 17);
		contentPane.add(lblCorreo);
		
		textFieldCorreo = new JTextField();
		lblCorreo.setLabelFor(textFieldCorreo);
		textFieldCorreo.setBounds(332, 104, 169, 21);
		contentPane.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(257, 165, 73, 17);
		contentPane.add(lblContrasena);
		
		textFieldContrasena = new JTextField();
		textFieldContrasena.setBounds(332, 163, 169, 21);
		contentPane.add(textFieldContrasena);
		textFieldContrasena.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBounds(321, 237, 128, 27);
		contentPane.add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(321, 291, 128, 27);
		contentPane.add(btnRegistrarse);
	}
}
