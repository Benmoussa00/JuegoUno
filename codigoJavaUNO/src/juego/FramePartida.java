package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;

public class FramePartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setBounds(100, 100, 797, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(49, 12, 221, 315);
		contentPane.add(panel);
		
		JLabel lblCartadetras = new JLabel("");
		lblCartadetras.setIcon(new ImageIcon(FramePartida.class.getResource("/juego/cartaDetras.png")));
		panel.add(lblCartadetras);
		
		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_2_1.setBackground(new Color(46, 194, 126));
		panel_1_1_2_1.setBounds(465, 364, 152, 211);
		contentPane.add(panel_1_1_2_1);
		panel_1_1_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label_5 = new JLabel(" 1");
		label_5.setForeground(new Color(246, 245, 244));
		label_5.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1_2_1.add(label_5, BorderLayout.NORTH);
		
		JLabel lblCartaDelante_4 = new JLabel("1");
		lblCartaDelante_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartaDelante_4.setForeground(new Color(246, 245, 244));
		lblCartaDelante_4.setFont(new Font("Dialog", Font.BOLD, 90));
		panel_1_1_2_1.add(lblCartaDelante_4, BorderLayout.CENTER);
		
		JLabel label_1_4 = new JLabel("1");
		label_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1_4.setForeground(new Color(246, 245, 244));
		label_1_4.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1_2_1.add(label_1_4, BorderLayout.SOUTH);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_2.setBackground(new Color(224, 27, 36));
		panel_1_1_2.setBounds(349, 364, 152, 211);
		contentPane.add(panel_1_1_2);
		panel_1_1_2.setLayout(new BorderLayout(0, 0));
		
		JLabel label_4 = new JLabel(" 4");
		label_4.setForeground(new Color(246, 245, 244));
		label_4.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1_2.add(label_4, BorderLayout.NORTH);
		
		JLabel lblCartaDelante_3 = new JLabel("4");
		lblCartaDelante_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartaDelante_3.setForeground(new Color(246, 245, 244));
		lblCartaDelante_3.setFont(new Font("Dialog", Font.BOLD, 90));
		panel_1_1_2.add(lblCartaDelante_3, BorderLayout.CENTER);
		
		JLabel label_1_3 = new JLabel("4");
		label_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1_3.setForeground(new Color(246, 245, 244));
		label_1_3.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1_2.add(label_1_3, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(224, 27, 36));
		panel_1.setBounds(464, 33, 178, 287);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCartaDelante = new JLabel("2");
		lblCartaDelante.setForeground(new Color(246, 245, 244));
		lblCartaDelante.setFont(new Font("Dialog", Font.BOLD, 90));
		lblCartaDelante.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblCartaDelante);
		
		JLabel label = new JLabel(" 2");
		label.setForeground(new Color(246, 245, 244));
		label.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1.add(label, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("2 ");
		label_1.setForeground(new Color(246, 245, 244));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1.add(label_1, BorderLayout.SOUTH);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_1.setBackground(new Color(28, 113, 216));
		panel_1_1_1.setBounds(235, 364, 152, 211);
		contentPane.add(panel_1_1_1);
		panel_1_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label_3 = new JLabel(" 7");
		label_3.setForeground(new Color(246, 245, 244));
		label_3.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1_1.add(label_3, BorderLayout.NORTH);
		
		JLabel lblCartaDelante_2 = new JLabel("7");
		lblCartaDelante_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartaDelante_2.setForeground(new Color(246, 245, 244));
		lblCartaDelante_2.setFont(new Font("Dialog", Font.BOLD, 90));
		panel_1_1_1.add(lblCartaDelante_2, BorderLayout.CENTER);
		
		JLabel label_1_2 = new JLabel("7");
		label_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1_2.setForeground(new Color(246, 245, 244));
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1_1.add(label_1_2, BorderLayout.SOUTH);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1.setBackground(new Color(246, 211, 45));
		panel_1_1.setBounds(117, 364, 152, 211);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCartaDelante_1 = new JLabel("2");
		lblCartaDelante_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartaDelante_1.setForeground(new Color(246, 245, 244));
		lblCartaDelante_1.setFont(new Font("Dialog", Font.BOLD, 90));
		panel_1_1.add(lblCartaDelante_1, BorderLayout.CENTER);
		
		JLabel label_1_1 = new JLabel("2 ");
		label_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1_1.setForeground(new Color(246, 245, 244));
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1.add(label_1_1, BorderLayout.SOUTH);
		
		JLabel label_2 = new JLabel(" 2");
		label_2.setForeground(new Color(246, 245, 244));
		label_2.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_1_1.add(label_2, BorderLayout.NORTH);
	}
}
