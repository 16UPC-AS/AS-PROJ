package presentacio;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DadesReserva {

	private JFrame frame;
	private JTextField textField;
	static JTextArea textArea;
	/**
	 * Launch the application.  
	 */
	public static void NewScreen(final ArrayList<String> rec) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DadesReserva window = new DadesReserva(rec);
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
	public DadesReserva(ArrayList<String> rec) {
		initialize(rec);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param rec 
	 */
	private void initialize(ArrayList<String> rec) {
		frame = new JFrame("Alta reserva amb notificacio");
		frame.setResizable(false);
		frame.setBounds(100, 100, 405, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRecurs = new JLabel("Recurs:");
		lblRecurs.setBounds(33, 34, 46, 14);
		frame.getContentPane().add(lblRecurs);
		
		JComboBox comboBox = new JComboBox(rec.toArray());
		comboBox.setBounds(33, 59, 132, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblUsuari = new JLabel("Usuari:");
		lblUsuari.setBounds(33, 90, 46, 14);
		frame.getContentPane().add(lblUsuari);
		
		textField = new JTextField();
		textField.setBounds(33, 115, 132, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblComentari = new JLabel("Comentari:");
		lblComentari.setBounds(185, 34, 84, 14);
		frame.getContentPane().add(lblComentari);
		
		textArea = new JTextArea();
		textArea.setBounds(183, 57, 181, 127);
		frame.getContentPane().add(textArea);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] options = {"Si","No"};
				int n = JOptionPane.showOptionDialog(frame, "Segur que vols sortir?","Alerta!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n == 0) System.exit(0);
				else return;
			}
		});
		btnCancel.setBounds(76, 195, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuari = textArea.getText();				
				try {
					ArrayList<String> usuaris = new ArrayList();	//S'HA D'UTILITZAR LA FUNCIO D'ADALT
					// I FER SERVIR ARRAY<ARRAY<STRING>>
					usuaris.add("Alex");
					usuaris.add("Jordi");
					usuaris.add("Joan");
					usuaris.add("Andreu");
					usuaris.add("Alex");
					usuaris.add("Jordi");
					usuaris.add("Joan");
					usuaris.add("Andreu");
					usuaris.add("Alex");
					usuaris.add("Jordi");
					usuaris.add("Joan");
					usuaris.add("Andreu");
					
					SeleccionarUsuarisANotificar suan = new SeleccionarUsuarisANotificar(usuaris);
					suan.NewScreen(usuaris);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// usuariNoExisteix
					// recursSalaSolapada
					// serveiNoDisponible
					// noHiHaProusUsuaris
					
					e.printStackTrace();
				}
			}
		});
		btnOk.setBounds(185, 195, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setRows(6);
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.GRAY);
		textArea_1.setBounds(10, 223, 369, 52);
		frame.getContentPane().add(textArea_1);
	}
}
