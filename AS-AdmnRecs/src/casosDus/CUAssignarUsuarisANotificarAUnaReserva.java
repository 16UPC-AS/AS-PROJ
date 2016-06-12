package casosDus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adaptadors.FactoriaAdaptador;
import dataControllers.BasicRepo;
import dataInterfaces.FactoriaDades;
import excepcions.ExcNoHiHaProusUsuaris;
import excepcions.ExcNoHiHaReserva;
import excepcions.ExcReservaATope;
import excepcions.ExcReservaCaducada;
import excepcions.ExcServeiNoDisponible;
import excepcions.ExcUsuariNoExisteix;
import model.ReservaAmbNotificacio;
import model.Usuari;

public class CUAssignarUsuarisANotificarAUnaReserva {

	private static String nomRecurs;
	private static Date data;
	private static Integer horaInici;

	
	public static String getNomRecurs() {
		return nomRecurs;
	}

	public static void setNomRecurs(String nomRecurs) {
		CUAssignarUsuarisANotificarAUnaReserva.nomRecurs = nomRecurs;
	}

	public static Date getData() {
		return data;
	}

	public static void setData(Date data) {
		CUAssignarUsuarisANotificarAUnaReserva.data = data;
	}

	public static Integer getHoraInici() {
		return horaInici;
	}

	public static void setHoraInici(Integer horaInici) {
		CUAssignarUsuarisANotificarAUnaReserva.horaInici = horaInici;
	}

	public static ArrayList<ArrayList<String>> obteUsuarisAAssignar(String nomR, Date d, Integer hi)
			throws ExcNoHiHaProusUsuaris, ExcReservaATope, ExcNoHiHaReserva, ExcReservaCaducada {
		Calendar cal = Calendar.getInstance();
		Date dActual = cal.getTime();

		if (dActual.getYear() > d.getYear() || (dActual.getYear() == d.getYear() && (dActual.getMonth() > d.getMonth()
				|| (dActual.getMonth() == d.getMonth() && (dActual.getDate() > d.getDate()
						|| (dActual.getDate() == d.getDate() && dActual.getHours() > hi)))))) {
			throw new ExcReservaCaducada();
		}
		return obteUsuarisPerAssignar(nomR, d, hi);
	}

	public static ArrayList<ArrayList<String>> obteUsuarisPerAssignar(String nomR, Date d, Integer hi)
			throws ExcNoHiHaProusUsuaris, ExcNoHiHaReserva, ExcReservaCaducada {
		setNomRecurs(nomR);
		setData(d);
		setHoraInici(hi);
		ReservaAmbNotificacio r = FactoriaDades.getReservaAmbNot().getByPK(nomR, d, hi);
		// if (!r.getEsAmbNotificacio())
		// ;// TODO activa excepcio [NoReservaAmbNotificacio]
		List<Usuari> totsUsuaris = FactoriaDades.getCtrlUsuari().getAll();
		Set<Usuari> usus = r.getUsuarisEsNotifica();
		ArrayList<ArrayList<String>> infoUsuaris = new ArrayList<ArrayList<String>>();

		for (Usuari u : totsUsuaris) {
			boolean existeix = false;
			for (Usuari aux : usus) {
				if (aux.equals(u)) {
					existeix = true;
					break;
				}
			}
			if (!existeix)
				infoUsuaris.add(u.getInfo());
		}
		if (infoUsuaris.size() == 0)
			throw new ExcNoHiHaProusUsuaris();
		return infoUsuaris;
	}

	public static void afegirUsuarisAReserva(ArrayList<String> usuaris)
			throws ExcReservaATope, ExcServeiNoDisponible, ExcUsuariNoExisteix {
		Set<Usuari> usuReserv = new HashSet<Usuari>();
		for (String u : usuaris) {
			usuReserv.add(FactoriaDades.getCtrlUsuari().getByPK(u));
		}
		ReservaAmbNotificacio r = FactoriaDades.getReservaAmbNot().getByPK(getNomRecurs(), getData(), getHoraInici());
		r.getUsuarisEsNotifica().addAll(usuReserv);
		if (r.getUsuarisEsNotifica().size() > 10)
			throw new ExcReservaATope();
		BasicRepo.saveOrUpdate(r);
		FactoriaAdaptador.getInstance();
		FactoriaAdaptador.getAdaptadorEsNotifica().enviarMissatge(r);
	}
}
