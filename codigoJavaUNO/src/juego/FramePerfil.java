package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FramePerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePerfil frame = new FramePerfil();
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
	public FramePerfil() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMiPerfil = new JLabel("Mi Perfil");
		lblMiPerfil.setFont(new Font("Dialog", Font.BOLD, 30));
		lblMiPerfil.setBounds(327, 12, 134, 83);
		contentPane.add(lblMiPerfil);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(303, 124, 55, 17);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		lblNombre.setLabelFor(textFieldNombre);
		textFieldNombre.setBounds(358, 122, 114, 21);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(342, 291, 105, 27);
		contentPane.add(btnGuardar);
	}
}
