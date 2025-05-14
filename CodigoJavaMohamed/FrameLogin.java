package juego;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class FrameLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldCorreo;
    private JTextField textFieldContrasena;

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

        // Lógica de iniciar sesión con validación de correo
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = textFieldCorreo.getText();
                String contrasena = textFieldContrasena.getText();

                // Verificar si el correo es válido (puedes usar un patrón regex aquí)
                if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    JOptionPane.showMessageDialog(FrameLogin.this,
                            "Por favor, ingresa un correo electrónico válido.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Verificar si el correo existe en la base de datos
                if (!DBAcceso.correoExiste(correo)) {
                    JOptionPane.showMessageDialog(FrameLogin.this,
                            "El correo no está registrado.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar la contraseña
                if (DBAcceso.verificarContrasena(correo, contrasena)) {
                    // Si la contraseña es correcta, abre el menú
                    FrameMenu menuFrame = new FrameMenu();
                    menuFrame.setVisible(true);
                    FrameLogin.this.dispose(); // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(FrameLogin.this,
                            "Contraseña incorrecta.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
    }

    // Método para validar el formato del correo
    public boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return correo.matches(regex);
    }
}
