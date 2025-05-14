package juego;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FramePerfil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNombre;

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

        // Botón "Guardar" para actualizar el nombre
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(327, 291, 134, 27);
        contentPane.add(btnGuardar);

        // Botón "Cerrar sesión"
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBounds(327, 329, 134, 27);
        contentPane.add(btnCerrarSesion);

        // Acción del botón "Guardar"
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el nombre que el usuario quiere guardar
                String nuevoNombre = textFieldNombre.getText();

                if (nuevoNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(FramePerfil.this, "Por favor, ingresa un nombre válido.");
                    return;
                }

                // Llamar al método para actualizar el nombre en la base de datos
                boolean exito = DBAcceso.actualizarNombreUsuario(nuevoNombre);

                if (exito) {
                    JOptionPane.showMessageDialog(FramePerfil.this, "Nombre actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(FramePerfil.this, "Hubo un error al actualizar el nombre.");
                }
            }
        });

        // Acción del botón "Cerrar sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí deberías limpiar cualquier variable de sesión que tengas, como el correo del usuario, etc.
                FrameLogin loginFrame = new FrameLogin();
                loginFrame.setVisible(true);
                dispose(); // Cierra la ventana actual (Perfil)
            }
        });
    }
}

