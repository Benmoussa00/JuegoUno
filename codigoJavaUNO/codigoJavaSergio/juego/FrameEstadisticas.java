package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;

public class FrameEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEstadisticas frame = new FrameEstadisticas();
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
	public FrameEstadisticas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMisEstadisticas = new JLabel("Mis Estadisticas");
		lblMisEstadisticas.setFont(new Font("Dialog", Font.BOLD, 30));
		lblMisEstadisticas.setBounds(276, 12, 236, 61);
		contentPane.add(lblMisEstadisticas);
		
		JLabel lblPartidasGanadas = new JLabel("Partidas Ganadas");
		lblPartidasGanadas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPartidasGanadas.setBounds(150, 79, 148, 17);
		contentPane.add(lblPartidasGanadas);
		
		JLabel lblPartidasPerdidas = new JLabel("Partidas Perdidas");
		lblPartidasPerdidas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPartidasPerdidas.setBounds(331, 79, 142, 17);
		contentPane.add(lblPartidasPerdidas);
		
		JLabel lblPartidasJugadas = new JLabel("Partidas Jugadas");
		lblPartidasJugadas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPartidasJugadas.setBounds(520, 79, 153, 17);
		contentPane.add(lblPartidasJugadas);
		
		JLabel labelGanadas = new JLabel("43");
		labelGanadas.setBounds(205, 107, 26, 17);
		contentPane.add(labelGanadas);
		
		JLabel labelPerdidas = new JLabel("15");
		labelPerdidas.setBounds(388, 108, 26, 17);
		contentPane.add(labelPerdidas);
		
		JLabel labelPorcentaje = new JLabel("58");
		labelPorcentaje.setBounds(574, 108, 26, 17);
		contentPane.add(labelPorcentaje);
		
		JLabel lblPartidasGanadasPorcen = new JLabel("Porcentaje Ganadas");
		lblPartidasGanadasPorcen.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPartidasGanadasPorcen.setBounds(214, 159, 153, 17);
		contentPane.add(lblPartidasGanadasPorcen);
		
		JLabel labelPartidasGanadasPor = new JLabel("74%");
		labelPartidasGanadasPor.setBounds(279, 186, 26, 17);
		contentPane.add(labelPartidasGanadasPor);
		
		JLabel lblPartidasPerdidasPorcen = new JLabel("Porcentaje Perdidas");
		lblPartidasPerdidasPorcen.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPartidasPerdidasPorcen.setBounds(432, 159, 153, 17);
		contentPane.add(lblPartidasPerdidasPorcen);
		
		JLabel labelPartidasPerdidasPor = new JLabel("26%");
		labelPartidasPerdidasPor.setBounds(497, 186, 26, 17);
		contentPane.add(labelPartidasPerdidasPor);
		
		JLabel lblHistorialDePartidas = new JLabel("Historial de partidas");
		lblHistorialDePartidas.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHistorialDePartidas.setBounds(299, 234, 247, 17);
		contentPane.add(lblHistorialDePartidas);
		
		JScrollPane scrollPaneHistorialPartidas = new JScrollPane();
		scrollPaneHistorialPartidas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneHistorialPartidas.setBounds(105, 256, 579, 193);
		contentPane.add(scrollPaneHistorialPartidas);
		
		JPanel panel = new JPanel();
		scrollPaneHistorialPartidas.setViewportView(panel);
		panel.setLayout(new GridLayout(99, 1, 0, 2));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblVsMiguel = new JLabel("VS Miguel | GANADA | 12-5-2025 9:57");
		panel_1.add(lblVsMiguel);
		
		JPanel panel_1_1 = new JPanel();
		panel.add(panel_1_1);
		
		JLabel lblVsMiguel_2 = new JLabel("VS Miguel | PERDIDA | 12-5-2025 9:34");
		panel_1_1.add(lblVsMiguel_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel.add(panel_1_1_1);
		
		JLabel lblVsMiguel_1_1 = new JLabel("VS Manolo | GANADA | 11-5-2025 23:44");
		panel_1_1_1.add(lblVsMiguel_1_1);
	}
}
