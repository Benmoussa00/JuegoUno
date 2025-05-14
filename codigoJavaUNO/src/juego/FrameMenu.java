package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUno = new JLabel("UNO");
		lblUno.setFont(new Font("Dialog", Font.BOLD, 30));
		lblUno.setBounds(348, 99, 79, 91);
		contentPane.add(lblUno);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FramePartida().setVisible(true);
				FrameMenu.this.dispose();
			}
		});
		btnJugar.setBounds(319, 216, 135, 27);
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
	}
}
