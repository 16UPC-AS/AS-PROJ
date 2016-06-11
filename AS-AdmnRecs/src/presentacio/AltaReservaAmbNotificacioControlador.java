package presentacio;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import casosDus.CUCreaReservaAmbNotificacio;
import javassist.compiler.ast.Pair;

public class AltaReservaAmbNotificacioControlador {
	private CUCreaReservaAmbNotificacio controladorDomini;
	/** Instancia de la vista. **/
//	private AltaReservaAmbNotificacioFrame view;

	/* Singleton start */
	private static AltaReservaAmbNotificacioControlador singleton;

	public static AltaReservaAmbNotificacioControlador getInstance() {
		if (singleton == null)
			singleton = new AltaReservaAmbNotificacioControlador() {
			};
		return singleton;
	}

	public AltaReservaAmbNotificacioControlador() {
		controladorDomini = new CUCreaReservaAmbNotificacio();
//		view = new AltaReservaAmbNotificacioFrame();
	}
	/* Singleton end */

	public void inicia() {
//		view.mostra();
//		view.mostraInici();
	}

	public void prOkObteCiutats() {
		
	}

	public void prOkAssignaViatge(String ciutat, String dniCl, Date dataIni, Date dataFi) {
//		if ("".equals(ciutat)) {
//			view.mostraMissatge("No has sel.leccionat cap ciutat");
//		} else if ("".equals(dniCl)) {
//			view.mostraMissatge("No has introduit el DNI");
//		} else if (dataIni == null) {
//			view.mostraMissatge("No has introduit cap data d'inici");
//		} else if (dataFi == null) {
//			view.mostraMissatge("No has introduit cap data de tornada");
//		} else if (!dataIni.before(dataFi) || dataIni.before(Calendar.getInstance().getTime())) {
//			view.mostraMissatge("El periode de temps no es valid");
//		} else {
//			try {
////				controladorDomini.enregistraViatge(dniCl, dataIni, dataFi, ciutat);
//				view.preguntaReservaHabitacio();
//			} catch (Exception e) {
//				if ("clientNoExisteix".equals(e.getMessage())) {
//					view.mostraMissatge("El client no existeix");
//				} else if ("jaTeViatge".equals(e.getMessage())) {
//					view.mostraMissatge("El client ja te un viatge en el periode seleccionat");
//				} else {
//					view.mostraMissatge("Error desconegut");
//				}
//			}
//		}
	}
//
//	public void prSiReservar() {
//		try {
//			HashSet<Pair<String, Float>> h = controladorDomini.mostraHotelsLliures();
//			String dni = controladorDomini.getDni();
//			Date dataIni = controladorDomini.getDataIni();
//			Date dataFin = controladorDomini.getDataFin();
//			view.mostraHotelsCiutat(h, dni, dataIni, dataFin);
//		} catch (Exception e) {
//			if ("hotelsNoLliures".equals(e.getMessage())) {
//				view.mostraMissatge("El sistema no te hotels disponibles");
//			} else {
//				view.mostraMissatge("Error desconegut");
//			}
//		}
//
//	}
//
//	public void prNoReservar() {
//		String dni = controladorDomini.getDni();
//		Float preuVol = controladorDomini.getPreuVol();
//		view.mostraPagarViatge(dni, preuVol);
//	}
//
//	public void prOkReserva(String nomHotel) {
//		Float preuTotal = controladorDomini.resevarHabitacio(nomHotel);
//		String dni = controladorDomini.getDni();
//		view.mostraPagarViatge(dni, preuTotal);
//	}
//
//	public void prOkPagarViatge(String numTarg, Date dataCad) {
//		try {
//			Boolean b = controladorDomini.pagament(numTarg, dataCad);
//			if (!b) {
//				view.mostraMissatge("El pagament no ha estat autoritzat");
//			} else {
//				view.mostraMissatgeFi();
//			}
//		} catch (Exception e) {
//			if ("serveiNoDisponible".equals(e.getMessage())) {
//				view.mostraMissatge("El servei no esta disponible");
//			} else {
//				view.mostraMissatge("Error desconegut");
//			}
//		}
//	}

	public void prOkMissatgeFi() {
//		view.tancar();
	}

	public void prCancelaReserva() {
//		String dni = controladorDomini.getDni();
//		Float preuVol = controladorDomini.getPreuVol();
//		view.mostraPagarViatge(dni, preuVol);
	}

	public void prCancela() {
//		view.tancar();
	}
}