package presentacio;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CreaReservaAmbNotificacioBaseView extends JPanel {
	protected JTextField messageArea;

	/**
	 * Create the panel.
	 */
	public CreaReservaAmbNotificacioBaseView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout());

		messageArea = new JTextField();
		messageArea.setEditable(false);
		add(messageArea, BorderLayout.SOUTH);
		messageArea.setColumns(10);
		// messageArea.setText("ContractarViatgeViewTemplate.ContractarViatgeViewTemplate");

	}

	public JTextField getMessageArea() {
		return messageArea;
	}

	public void mostraMissatge(String missatge) {
		messageArea.setText(missatge);
	}

}
