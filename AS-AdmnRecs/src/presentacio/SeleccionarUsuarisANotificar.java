package presentacio;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import excepcions.ExcReservaATope;
import excepcions.ExcServeiNoDisponible;
import excepcions.ExcUsuariNoExisteix;

public class SeleccionarUsuarisANotificar {

	private JFrame frame;
	static JScrollPane scrollPane;
	static CheckBoxList cbList;
	static JTextArea textArea;
	Map<String, String> usernames;
	ArrayList<String> nomUsers;

	/**
	 * Launch the application.
	 * 
	 * @param usuaris
	 */
	public static void NewScreen(final ArrayList<ArrayList<String>> usuaris) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarUsuarisANotificar window = new SeleccionarUsuarisANotificar(usuaris);
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
	 * 
	 * @param usuaris
	 */
	public SeleccionarUsuarisANotificar(ArrayList<ArrayList<String>> usuaris) {
		initialize(usuaris);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param usuaris
	 */
	private void initialize(ArrayList<ArrayList<String>> usuaris) {
		frame = new JFrame("Alta reserva amb notificacio");
		frame.setResizable(false);
		frame.setBounds(100, 100, 405, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSeleccionaElsUsuaris = new JLabel("Selecciona els usuaris a notificar:");
		lblSeleccionaElsUsuaris.setBounds(38, 17, 285, 14);
		frame.getContentPane().add(lblSeleccionaElsUsuaris);

		usernames = new HashMap<String, String>();
		nomUsers = new ArrayList<String>();
		for (ArrayList<String> r : usuaris) {
			usernames.put(r.get(1), r.get(0));
			nomUsers.add(r.get(1));
		}
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options = { "Si", "No" };
				int n = JOptionPane.showOptionDialog(frame, "Segur que vols sortir?", "Alerta!",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n == 0)
					System.exit(0);
				else
					return;
			}
		});
		btnCancel.setBounds(67, 205, 89, 23);
		frame.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(194, 205, 89, 23);
		frame.getContentPane().add(btnOk);

		textArea = new JTextArea();
		textArea.setForeground(Color.ORANGE);
		textArea.setRows(6);
		textArea.setEditable(false);
		textArea.setBackground(Color.GRAY);
		textArea.setBounds(10, 234, 369, 52);
		frame.getContentPane().add(textArea);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 42, 275, 141);
		frame.getContentPane().add(scrollPane);
		cbList = new CheckBoxList(usuaris.size());
		JCheckBox[] myList = new JCheckBox[usuaris.size()];

		for (int i = 0; i < nomUsers.size(); ++i) {
			JCheckBox check = new JCheckBox(nomUsers.get(i));
			myList[i] = check;
		}
		cbList.setListData(myList);

		scrollPane.setViewportView(cbList);
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					// JOptionPane.showMessageDialog(frame, "La reserva s'ha
					// creat correctament");
					ArrayList<Boolean> usus = cbList.getSeleccionats();
					StringBuffer sb = new StringBuffer("");
					ArrayList<String> usernamesAAssignar = new ArrayList<String>();
					int index = 0;
					for (Boolean nomUser : usus) {
						if (nomUser) {
							sb.append(usernames.get(nomUsers.get(index)));
							usernamesAAssignar.add(usernames.get(nomUsers.get(index)));
						}
						++index;
					}

					AltaReservaAmbNotificacioControlador.getInstance().prOKAssignaUsuarisAReserva(usernamesAAssignar);
					frame.setVisible(false);
					// System.exit(0);
				} catch (ExcReservaATope e1) {
					textArea.setText("No es poden assignar més de 10 usuaris per a ser notificats");
				} catch (ExcServeiNoDisponible e1) {
					textArea.setText("El servei no està disponible");
				} catch (ExcUsuariNoExisteix e1) {
					textArea.setText("El servei no està disponible");
				}
			}
		});

		// frame.getContentPane().add(scrollPane);
	}
}
