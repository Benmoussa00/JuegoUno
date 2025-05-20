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
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	SocketServidor socketservidor;

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
		panel_robar.setBounds(49, 12, 221, 315);
		contentPane.add(panel_robar);
		
		JLabel lblCartadetras = new JLabel("");
		lblCartadetras.setIcon(new ImageIcon(FramePartida.class.getResource("/juego/cartaDetras.png")));
		panel_robar.add(lblCartadetras);
		
		JPanel panel_monton = new JPanel();
		panel_monton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_monton.setBackground(new Color(224, 27, 36));
		panel_monton.setBounds(464, 33, 178, 287);
		contentPane.add(panel_monton);
		panel_monton.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCartaCentro = new JLabel("2");
		lblCartaCentro.setForeground(new Color(246, 245, 244));
		lblCartaCentro.setFont(new Font("Dialog", Font.BOLD, 90));
		lblCartaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		panel_monton.add(lblCartaCentro);
		
		JLabel labelTop = new JLabel(" 2");
		labelTop.setForeground(new Color(246, 245, 244));
		labelTop.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_monton.add(labelTop, BorderLayout.NORTH);
		
		JLabel labelBottom = new JLabel("2 ");
		labelBottom.setForeground(new Color(246, 245, 244));
		labelBottom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBottom.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_monton.add(labelBottom, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(49, 362, 659, 387);
		contentPane.add(scrollPane);
		
		JPanel panel_baraja = new JPanel();
		scrollPane.setViewportView(panel_baraja);
		panel_baraja.setLayout(new GridLayout(2, 99, 2, 2));
	}
}
