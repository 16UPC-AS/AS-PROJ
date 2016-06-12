package presentacio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import casosDus.CUCreaReservaAmbNotificacio;
import excepcions.ExcNoHiHaProusUsuaris;
import excepcions.ExcNoHiHaRecursos;
import excepcions.ExcNoHiHaReserva;
import excepcions.ExcRecursSalaSolapada;
import excepcions.ExcReservaATope;
import excepcions.ExcReservaCaducada;
import excepcions.ExcServeiNoDisponible;
import excepcions.ExcUsuariNoExisteix;
import model.Usuari;

public class AltaReservaAmbNotificacioControlador {
	private CUCreaReservaAmbNotificacio controladorDomini;
	/** Instancia de la vista. **/
	ObtenirRecursosDisponibles pantalla1;
	DadesReserva pantalla2;
	SeleccionarUsuarisANotificar pantalla3;
	// private AltaReservaAmbNotificacioFrame view;

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
		pantalla1 = new ObtenirRecursosDisponibles();
	}

	public void inicia() {
		pantalla1.launch();
	}

	public void prOkObteRecursosDisponibles(Date d, Integer hi, Integer hf) throws ExcNoHiHaRecursos {
		DadesReserva.NewScreen(CUCreaReservaAmbNotificacio.obteRecursosDisponibles(d, hi, hf));

	}

	public void prOKCreaReservaAmbNotificiacio(String nomR, String username, String comentari)
			throws ExcNoHiHaProusUsuaris, ExcRecursSalaSolapada, ExcNoHiHaReserva, ExcReservaCaducada,
			ExcUsuariNoExisteix, ExcServeiNoDisponible {
		controladorDomini.creaReservaAmbNotificacio(nomR, username, comentari);
		SeleccionarUsuarisANotificar.NewScreen(controladorDomini.obteUsuarisPerAssignar());
	}

	public void prOKAssignaUsuarisAReserva(ArrayList<String> usuaris) throws ExcReservaATope, ExcServeiNoDisponible, ExcUsuariNoExisteix {
		controladorDomini.assignarUsuarisAReserva(usuaris);
		FinestraFinal.NewScreen();
	}

	public void prOkMissatgeFi() {
		// view.tancar();
	}

	public void prCancelaReserva() {
		// String dni = controladorDomini.getDni();
		// Float preuVol = controladorDomini.getPreuVol();
		// view.mostraPagarViatge(dni, preuVol);
	}

	public void prCancela() {
		// view.tancar();
	}
}
