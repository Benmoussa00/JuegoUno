package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;

public class FrameRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRanking frame = new FrameRanking();
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
	public FrameRanking() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRanking = new JLabel("Ranking");
		lblRanking.setFont(new Font("Dialog", Font.BOLD, 30));
		lblRanking.setBounds(317, 0, 135, 58);
		contentPane.add(lblRanking);
		
		JScrollPane scrollPaneHistorialPartidas = new JScrollPane();
		scrollPaneHistorialPartidas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneHistorialPartidas.setBounds(101, 69, 579, 352);
		contentPane.add(scrollPaneHistorialPartidas);
		
		JPanel panel = new JPanel();
		scrollPaneHistorialPartidas.setViewportView(panel);
		panel.setLayout(new GridLayout(99, 1, 2, 2));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblMiguel = new JLabel("Miguel");
		panel_1.add(lblMiguel);
		
		JLabel label = new JLabel("|");
		panel_1.add(label);
		
		JLabel lblG = new JLabel("G:56 - P:12 - J:68");
		panel_1.add(lblG);
		
		JLabel label_2 = new JLabel("|");
		panel_1.add(label_2);
		
		JLabel lblNewLabel = new JLabel("G: 88% - P: 12%");
		panel_1.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblPaco = new JLabel("Paco");
		panel_3.add(lblPaco);
		
		JLabel label_1 = new JLabel("|");
		panel_3.add(label_1);
		
		JLabel lblGP = new JLabel("G:49 - P:11 - J:60");
		panel_3.add(lblGP);
		
		JLabel label_2_1 = new JLabel("|");
		panel_3.add(label_2_1);
		
		JLabel lblG_1 = new JLabel("G: 82% - P: 18%");
		panel_3.add(lblG_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblJavier = new JLabel("Javier");
		panel_2.add(lblJavier);
		
		JLabel label_1_1 = new JLabel("|");
		panel_2.add(label_1_1);
		
		JLabel lblGP_2 = new JLabel("G:42 - P:20 - J:62");
		panel_2.add(lblGP_2);
		
		JLabel label_2_1_1 = new JLabel("|");
		panel_2.add(label_2_1_1);
		
		JLabel lblG_1_1 = new JLabel("G: 52% - P: 48%");
		panel_2.add(lblG_1_1);
	}
}
