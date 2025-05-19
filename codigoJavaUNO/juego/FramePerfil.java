package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FramePerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField textFieldNombre;

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
		textFieldNombre.setText(Cache.obtenerDeCache("nombreUsuario"));
		
		JButton btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nuevoNombre = textFieldNombre.getText();

                if (nuevoNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(FramePerfil.this, "Por favor, ingresa un nombre válido.");
                    return;
                }
                boolean exito = DBacceso.actualizarNombreUsuario(nuevoNombre);

                if (exito) {
                    JOptionPane.showMessageDialog(FramePerfil.this, "Nombre actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(FramePerfil.this, "Hubo un error al actualizar el nombre.");
                }
            }
		});
		btnGuardar.setBounds(327, 292, 138, 27);
		contentPane.add(btnGuardar);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cache.eliminarDeCache("correo");
				Cache.eliminarDeCache("usuario");
				
				FrameMenu menu = FrameMenu.getInstance();
		        if (menu != null) {
		            menu.dispose();
		        }
				
				FrameLogin loginFrame = new FrameLogin();
                loginFrame.setVisible(true);
                dispose();
			}
		});
		btnCerrarSesin.setBounds(327, 347, 138, 27);
		contentPane.add(btnCerrarSesin);
	}
	
}
