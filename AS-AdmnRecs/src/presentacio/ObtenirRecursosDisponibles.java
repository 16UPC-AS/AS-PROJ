package presentacio;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JCalendar;

import Excepcions.ExcNoHiHaRecursosDisponibles;
import Excepcions.ExcPeriodeErroni;
import casosDus.CUConsultaRecurs;

public class ObtenirRecursosDisponibles {

	private JFrame frmAltaReservaAmb;
	private JFrame frmAlta;
	private Date d = null;
	private Integer hi = null;
	private Integer hf = null;
	static JComboBox comboBox;
	static JComboBox comboBox_1;
	static JCalendar calendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObtenirRecursosDisponibles window = new ObtenirRecursosDisponibles();
					window.frmAltaReservaAmb.setVisible(true);
					window.frmAltaReservaAmb.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ObtenirRecursosDisponibles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaReservaAmb = new JFrame();
		frmAltaReservaAmb.setResizable(false);
		frmAltaReservaAmb.setTitle("Alta reserva amb notificacio");
		frmAltaReservaAmb.setBounds(100, 100, 405, 336);
		frmAltaReservaAmb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaReservaAmb.getContentPane().setLayout(null);

		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(6);
		textArea.setBackground(Color.GRAY);
		textArea.setBounds(10, 234, 369, 52);
		frmAltaReservaAmb.getContentPane().add(textArea);

		JLabel lblData = new JLabel("Data de reserva:");
		lblData.setBounds(71, 11, 184, 14);
		frmAltaReservaAmb.getContentPane().add(lblData);

		calendar = new JCalendar();
		calendar.setMinSelectableDate(new Date());
		calendar.setBounds(71, 36, 184, 153);
		frmAltaReservaAmb.getContentPane().add(calendar);

		JLabel lblHoraDinici = new JLabel("Hora d'inici:");
		lblHoraDinici.setBounds(265, 36, 80, 14);
		frmAltaReservaAmb.getContentPane().add(lblHoraDinici);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00",
				"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));
		comboBox.setBounds(265, 61, 80, 20);
		frmAltaReservaAmb.getContentPane().add(comboBox);

		JLabel lblHoraDeFi = new JLabel("Hora de fi:");
		lblHoraDeFi.setBounds(265, 92, 80, 14);
		frmAltaReservaAmb.getContentPane().add(lblHoraDeFi);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "01:00", "02:00", "03:00", "04:00", "05:00",
				"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));
		comboBox_1.setBounds(265, 117, 80, 20);
		frmAltaReservaAmb.getContentPane().add(comboBox_1);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] options = { "Si", "No" };
				int n = JOptionPane.showOptionDialog(frmAltaReservaAmb, "Segur que vols sortir?", "Alerta!",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n == 0)
					System.exit(0);
				else
					return;
			}
		});
		btnCancel.setBounds(71, 200, 89, 23);
		frmAltaReservaAmb.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String parts[] = comboBox.getSelectedItem().toString().split(":");
				hi = Integer.parseInt(parts[0]);
				String[] parts2 = comboBox_1.getSelectedItem().toString().split(":");
				hf = Integer.parseInt(parts2[0]);
				d = calendar.getDate();

				try {
					if (hi > hf)
						throw new ExcPeriodeErroni();

					CUConsultaRecurs cucr = new CUConsultaRecurs();
					// ArrayList<ArrayList<String>> recursos =
					// cucr.obteRecursosDisponiblesPerData(d, hi, hf);
					ArrayList<String> exemple_recursos = new ArrayList(); // S'HA
																			// D'UTILITZAR
																			// LA
																			// FUNCIO
																			// D'ADALT
					// I FER SERVIR ARRAY<ARRAY<STRING>>
					exemple_recursos.add("ASUS X556UA");
					exemple_recursos.add("ASUS F554L");
					exemple_recursos.add("Sala1");
					exemple_recursos.add("EPSON EH-TW5210");
					if (exemple_recursos.size() == 0) throw new ExcNoHiHaRecursosDisponibles();
					DadesReserva ar = new DadesReserva(exemple_recursos);
					ar.NewScreen(exemple_recursos);
					frmAltaReservaAmb.setVisible(false);

				} catch (ExcPeriodeErroni e) {
					textArea.setText("El periode és erroni");
				} catch (ExcNoHiHaRecursosDisponibles e){
					textArea.setText("No hi ha recursos disponibles per ser reservats a la data i \nperíode indicats");
				} catch (Exception e) {
					
				}

			}

		});
		btnOk.setBounds(166, 200, 89, 23);
		frmAltaReservaAmb.getContentPane().add(btnOk);

	}
}
