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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import excepcions.ExcNoHiHaProusUsuaris;
import excepcions.ExcNoHiHaReserva;
import excepcions.ExcRecursSalaSolapada;
import excepcions.ExcReservaCaducada;
import excepcions.ExcServeiNoDisponible;
import excepcions.ExcUsuariNoExisteix;

public class DadesReserva {

	private JFrame frame;
	private JTextField textField;
	static JTextArea textArea;
	Map<String, String> infoRecs;
	ArrayList<String> nomRecs;
	JTextArea txtInfo;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(final ArrayList<ArrayList<String>> rec) {
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
	public DadesReserva(ArrayList<ArrayList<String>> rec) {
		initialize(rec);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param rec
	 */
	private void initialize(ArrayList<ArrayList<String>> rec) {
		frame = new JFrame("Alta reserva amb notificacio");
		frame.setResizable(false);
		frame.setBounds(100, 100, 405, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblRecurs = new JLabel("Recurs:");
		lblRecurs.setBounds(33, 13, 46, 14);
		frame.getContentPane().add(lblRecurs);

		infoRecs = new HashMap<String, String>();
		nomRecs = new ArrayList<String>();
		for (ArrayList<String> r : rec) {
			StringBuffer info = new StringBuffer("");
			if (r.get(1) != null) {
				info.append("Marca : ").append(r.get(1)).append("\n").append("Model : ").append(r.get(2));
			} else {
				if (r.get(3) != null)
					info.append("Resolució : ").append(r.get(3));
				else {
					info.append("Aforament : ").append(r.get(4)).append("\n").append("Ubicació : ").append(r.get(5))
							.append("\n");
					if (r.get(6) != null) {
						info.append("Ordinador: \n").append("Marca : ").append(r.get(6)).append(" - ").append(r.get(7))
								.append("\n");
					}
					if (r.get(8) != null)
						info.append("Resolució proj :").append(r.get(8));
				}
			}
			infoRecs.put(r.get(0), info.toString());
			nomRecs.add(r.get(0));
		}
		txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setForeground(new Color(65, 105, 225));
		txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtInfo.setText(infoRecs.get(nomRecs.get(0)));
		txtInfo.setBackground(new Color(240, 240, 240));
		txtInfo.setBounds(33, 91, 132, 91);

		frame.getContentPane().add(txtInfo);
		JComboBox comboBox = new JComboBox(nomRecs.toArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					txtInfo.setText(infoRecs.get(nomRecs.get(comboBox.getSelectedIndex())));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBox.setBounds(33, 40, 132, 20);
		frame.getContentPane().add(comboBox);

		JLabel lblUsuari = new JLabel("Usuari:");
		lblUsuari.setBounds(191, 13, 46, 14);
		frame.getContentPane().add(lblUsuari);

		textField = new JTextField();
		textField.setBounds(191, 40, 132, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblComentari = new JLabel("Comentari:");
		lblComentari.setBounds(190, 73, 84, 14);
		frame.getContentPane().add(lblComentari);

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setBounds(191, 89, 164, 69);
		JScrollPane jScrollPane  = new JScrollPane(textArea);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		frame.getContentPane().add(textArea);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] options = { "Si", "No" };
				int n = JOptionPane.showOptionDialog(frame, "Segur que vols sortir?", "Alerta!",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n == 0)
					System.exit(0);
				else
					return;
			}
		});
		btnCancel.setBounds(76, 195, 89, 23);
		frame.getContentPane().add(btnCancel);
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.ORANGE);
		textArea_1.setRows(6);
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.GRAY);
		textArea_1.setBounds(10, 223, 369, 52);
		frame.getContentPane().add(textArea_1);
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuari = textArea.getText();
				try {
					AltaReservaAmbNotificacioControlador.getInstance().prOKCreaReservaAmbNotificiacio(
							nomRecs.get(comboBox.getSelectedIndex()), textField.getText(), textArea.getText());
					frame.setVisible(false);

				} catch (ExcReservaCaducada e) {
					textArea_1.setText("La reserva ha caducat");

				} catch (ExcNoHiHaReserva e) {
					textArea_1.setText("No hi ha reserva");

				} catch (ExcRecursSalaSolapada e) {
					textArea_1.setText("L'usuari ja té una altra sala reservada en aquest període");
				} catch (ExcUsuariNoExisteix e) {
					textArea_1.setText("L'usuari no existeix");
				} catch (ExcNoHiHaProusUsuaris e) {
					textArea_1.setText("No hi ha prous usuaris");
				} catch (ExcServeiNoDisponible e) {
					textArea_1.setText("El servei no està disponible");
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

		JLabel lblInformaci = new JLabel("Informació:");
		lblInformaci.setBounds(33, 73, 84, 14);
		frame.getContentPane().add(lblInformaci);

	}
}
