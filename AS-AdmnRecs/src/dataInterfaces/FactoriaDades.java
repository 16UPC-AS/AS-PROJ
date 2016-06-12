package dataInterfaces;

import dataControllers.CtrlRecursDB;
import dataControllers.CtrlReservaAmbNotDB;
import dataControllers.CtrlReservaDB;
import dataControllers.CtrlUsuariDB;

public class FactoriaDades {
	private static FactoriaDades singleton;
	private static CtrlRecurs ctrlRecurs;
	private static CtrlReserva ctrlReserva;
	private static CtrlReservaAmbNot ctrlReservaAmbNot;
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

	public static CtrlRecurs getCtrlRecurs() {
		if (ctrlRecurs == null)
			ctrlRecurs = new CtrlRecursDB();
		return ctrlRecurs;
	}

	public static CtrlReserva getCtrlReserva() {
		if (ctrlReserva == null)
			ctrlReserva = new CtrlReservaDB();
		return ctrlReserva;
	}

	public static CtrlUsuari getCtrlUsuari() {
		if (ctrlUsuari == null)
			ctrlUsuari = new CtrlUsuariDB();
		return ctrlUsuari;
	}

	public static CtrlReservaAmbNot getReservaAmbNot() {
		if (ctrlReservaAmbNot == null)
			ctrlReservaAmbNot = new CtrlReservaAmbNotDB();
		return ctrlReservaAmbNot;
	}

}
