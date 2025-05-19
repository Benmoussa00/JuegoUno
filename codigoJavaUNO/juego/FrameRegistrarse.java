package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrameRegistrarse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCorreo;
	private JTextField textFieldContrasena;
	private JTextField textFieldNombreUsuario;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRegistrarse frame = new FrameRegistrarse();
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
	public FrameRegistrarse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setFont(new Font("Dialog", Font.BOLD, 30));
		lblRegistrarse.setBounds(290, 12, 340, 48);
		contentPane.add(lblRegistrarse);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(269, 118, 45, 17);
		contentPane.add(lblCorreo);
		
		textFieldCorreo = new JTextField();
		lblCorreo.setLabelFor(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(316, 116, 169, 21);
		contentPane.add(textFieldCorreo);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(241, 177, 73, 17);
		contentPane.add(lblContrasena);
		
		textFieldContrasena = new JTextField();
		lblContrasena.setLabelFor(textFieldContrasena);
		textFieldContrasena.setColumns(10);
		textFieldContrasena.setBounds(316, 175, 169, 21);
		contentPane.add(textFieldContrasena);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(196, 235, 115, 17);
		contentPane.add(lblNombreUsuario);
		
		textFieldNombreUsuario = new JTextField();
		lblNombreUsuario.setLabelFor(textFieldNombreUsuario);
		textFieldNombreUsuario.setColumns(10);
		textFieldNombreUsuario.setBounds(316, 233, 169, 21);
		contentPane.add(textFieldNombreUsuario);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldCorreo.setEditable(false);
		        textFieldContrasena.setEditable(false);
		        textFieldNombreUsuario.setEditable(false);
				
		        String correo = textFieldCorreo.getText();
		        String contrasena = textFieldContrasena.getText();
		        String usuario = textFieldNombreUsuario.getText();
		        boolean camposValidos = true;

		        
		        String emailValidar = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		        if (!correo.matches(emailValidar)) {
		            JOptionPane.showMessageDialog(contentPane, 
		                "Correo electrónico invalido\nEjemplo valido: usuario@dominio.com",
		                "Error en correo", 
		                JOptionPane.WARNING_MESSAGE);
		            camposValidos = false;
		            textFieldCorreo.requestFocus();
		        }

		        
		        String passwordValidar = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
		        if (!contrasena.matches(passwordValidar)) {
		            JOptionPane.showMessageDialog(contentPane, 
		                "La contraseña debe contener:\n- Minimo 8 caracteres\n- Al menos 1 mayuscula\n- Al menos 1 numero",
		                "Error en contraseña", 
		                JOptionPane.WARNING_MESSAGE);
		            camposValidos = false;
		            textFieldContrasena.requestFocus();
		        }


		        if (usuario.length() < 4) {
		            JOptionPane.showMessageDialog(contentPane, 
		                "El nombre de usuario debe tener al menos 4 caracteres",
		                "Error en usuario", 
		                JOptionPane.WARNING_MESSAGE);
		            camposValidos = false;
		            textFieldNombreUsuario.requestFocus();
		        }


		        if (!camposValidos) {
		        	textFieldCorreo.setEditable(true);
			        textFieldContrasena.setEditable(true);
			        textFieldNombreUsuario.setEditable(true);
		        	return;
		        }
		        	
				if(DBacceso.insertarUsuario(correo, contrasena, usuario)) {
					Cache.guardarEnCache("correo", correo);
                	Cache.guardarEnCache("nombreUsuario", DBacceso.obtenerNombreUsuario(correo));
					
					new FrameMenu().setVisible(true);
					FrameRegistrarse.this.dispose();
				}
				
				textFieldCorreo.setEditable(true);
				textFieldContrasena.setEditable(true);
				textFieldNombreUsuario.setEditable(true);
			}
		});
		btnRegistrarse.setBounds(315, 299, 128, 27);
		contentPane.add(btnRegistrarse);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameLogin().setVisible(true);
				FrameRegistrarse.this.dispose();
			}
		});
		btnIniciarSesion.setBounds(315, 353, 128, 27);
		contentPane.add(btnIniciarSesion);
	}

}
