package dades;

import repositories.CtrlOrdinador;
import repositories.CtrlProjector;
import repositories.CtrlRecurs;
import repositories.CtrlReservaAmbNot;
import repositories.CtrlSala;
import repositories.CtrlUsuari;

public class FactoriaDades {
	private static FactoriaDades singleton;
	private static CtrlOrdinador ctrlOrdinador;
	private static CtrlProjector ctrlProjector;
	private static CtrlRecurs ctrlRecurs;
	private static CtrlReservaAmbNot ctrlReserva;
	private static CtrlReservaAmbNot ctrlReservaAmbNot;
	private static CtrlSala ctrlSala;
	private static CtrlUsuari ctrlUsuari;

	public static FactoriaDades getInstance() {
		if (singleton == null)
			singleton = new FactoriaDades() {
			};
		return singleton;
	}

	public FactoriaDades() {
		super();
	}

	public static CtrlOrdinador getCtrlOrdinador() {
		if (ctrlOrdinador == null)
			ctrlOrdinador = new CtrlOrdinador();
		return ctrlOrdinador;
	}

	public static CtrlProjector getCtrlProjector() {
		if (ctrlProjector == null)
			ctrlProjector = new CtrlProjector();
		return ctrlProjector;
	}

	public static CtrlRecurs getCtrlRecurs() {
		if (ctrlRecurs == null)
			ctrlRecurs = new CtrlRecurs();
		return ctrlRecurs;
	}

	public static CtrlReservaAmbNot getCtrlReserva() {
		if (ctrlReserva == null)
			ctrlReserva = new CtrlReservaAmbNot();
		return ctrlReserva;
	}

	public static CtrlSala getCtrlSala() {
		if (ctrlSala == null)
			ctrlSala = new CtrlSala();
		return ctrlSala;
	}

	public static CtrlUsuari getCtrlUsuari() {
		if (ctrlUsuari == null)
			ctrlUsuari = new CtrlUsuari();
		return ctrlUsuari;
	}

}
