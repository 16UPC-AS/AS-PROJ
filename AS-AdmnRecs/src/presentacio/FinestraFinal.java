package presentacio;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FinestraFinal {

	private JFrame frame;
	JTextArea txtInfo;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraFinal window = new FinestraFinal();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinestraFinal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param rec
	 */
	private void initialize() {
		frame = new JFrame("Alta reserva amb notificacio");
		frame.setResizable(false);
		frame.setBounds(100, 100, 405, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setForeground(new Color(65, 105, 225));
		txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtInfo.setText("Reserva realitzada correctament!");
		txtInfo.setBackground(new Color(240, 240, 240));
		txtInfo.setBounds(77, 127, 291, 75);

		frame.getContentPane().add(txtInfo);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnOk.setBounds(149, 240, 89, 23);
		frame.getContentPane().add(btnOk);

		JLabel lblInformaci = new JLabel("Informació:");
		lblInformaci.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInformaci.setBounds(138, 24, 130, 60);
		frame.getContentPane().add(lblInformaci);

	}
}
