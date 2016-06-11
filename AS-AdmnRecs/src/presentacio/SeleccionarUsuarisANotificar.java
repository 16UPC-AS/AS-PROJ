package presentacio;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SeleccionarUsuarisANotificar {

	private JFrame frame;
	static JScrollPane scrollPane;
	static CheckBoxList cbList;
	static JTextArea textArea;

	/**
	 * Launch the application.
	 * 
	 * @param usuaris
	 */
	public static void NewScreen(final ArrayList<String> usuaris) {
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
	public SeleccionarUsuarisANotificar(ArrayList<String> usuaris) {
		initialize(usuaris);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param usuaris
	 */
	private void initialize(ArrayList<String> usuaris) {
		frame = new JFrame("Alta reserva amb notificacio");
		frame.setResizable(false);
		frame.setBounds(100, 100, 405, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSeleccionaElsUsuaris = new JLabel("Selecciona els usuaris a notificar:");
		lblSeleccionaElsUsuaris.setBounds(38, 17, 285, 14);
		frame.getContentPane().add(lblSeleccionaElsUsuaris);

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
		textArea.setRows(6);
		textArea.setEditable(false);
		textArea.setBackground(Color.GRAY);
		textArea.setBounds(10, 234, 369, 52);
		frame.getContentPane().add(textArea);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 42, 275, 141);
		frame.getContentPane().add(scrollPane);
		cbList = new CheckBoxList();
		JCheckBox[] myList = new JCheckBox[usuaris.size()];
		for (int i = 0; i < usuaris.size(); ++i) {
			JCheckBox check = new JCheckBox(usuaris.get(i));
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
					List<String> usus = (List<String>) cbList.getSelectedValuesList();
					StringBuffer sb = new StringBuffer("");
					for (String nomUser : usus) {
						sb.append(nomUser);
					}

					textArea.setText(sb.toString());
					// System.exit(0);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					// reservaATope
					// serveiNoDisponible
					e1.printStackTrace();
				}
			}
		});

		// frame.getContentPane().add(scrollPane);
	}
}
