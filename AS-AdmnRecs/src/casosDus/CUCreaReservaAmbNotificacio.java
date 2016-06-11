package casosDus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adaptadors.FactoriaAdaptador;
import dades.FactoriaDades;
import model.Recurs;
import model.ReservaAmbNotificacio;
import model.Usuari;
import repositories.BasicRepo;

public class CUCreaReservaAmbNotificacio {

	private static Date data;
	private static Integer horaInici;
	private static Integer horaFi;
	private static String nomR;
	private static String comentari;
	private static String username;
	private static String mail;

	public static Date getData() {
		return data;
	}

	public static void setData(Date data) {
		CUCreaReservaAmbNotificacio.data = data;
	}

	public static Integer getHoraInici() {
		return horaInici;
	}

	public static void setHoraInici(Integer horaInici) {
		CUCreaReservaAmbNotificacio.horaInici = horaInici;
	}

	public static Integer getHoraFi() {
		return horaFi;
	}

	public static void setHoraFi(Integer horaFi) {
		CUCreaReservaAmbNotificacio.horaFi = horaFi;
	}

	public static String getNomR() {
		return nomR;
	}

	public static void setNomR(String nomR) {
		CUCreaReservaAmbNotificacio.nomR = nomR;
	}

	public static String getComentari() {
		return comentari;
	}

	public static void setComentari(String comentari) {
		CUCreaReservaAmbNotificacio.comentari = comentari;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		CUCreaReservaAmbNotificacio.username = username;
	}

	public static String getMail() {
		return mail;
	}

	public static void setMail(String mail) {
		CUCreaReservaAmbNotificacio.mail = mail;
	}

	public static ArrayList<ArrayList<String>> obteRecursosDisponibles(Date d, Integer hi, Integer hf) {
		setData(d);
		setHoraInici(hi);
		setHoraFi(hf);
		return CUConsultaRecurs.obteRecursosDisponiblesPerData(d, hi, hf);
	}

	public static void creaReservaAmbNotificacio(String nomR, String username, String comentari) {
		try {
			Usuari u = FactoriaDades.getCtrlUsuari().getByPK(username);
			Recurs rec = FactoriaDades.getCtrlRecurs().getByPK(nomR);

			if (rec.etsSala()) {
				List<ReservaAmbNotificacio> lResv = FactoriaDades.getCtrlReserva().getByUsuari(u);
				for (ReservaAmbNotificacio r : lResv) {
					if (!r.periodeNoSolapat(data, horaInici, horaFi) && r.recursEtsSala()
							&& !nomR.equals(r.getNomRecurs())) {
						// activa excepcio [recursSalaSolapada]
					}
				}
			}

			ReservaAmbNotificacio r = new ReservaAmbNotificacio(rec, u, getData(), getHoraInici(), getHoraFi(), comentari);
			FactoriaDades.getCtrlRecurs().saveOrUpdate(r);
			FactoriaAdaptador.getAdaptadorEsNotifica().enviarMissatge(r);

			setNomR(nomR);
			setComentari(comentari);
			setUsername(username);
			setMail(u.getMail());

		} catch (Exception e) {

		}
	}

	public static ArrayList<ArrayList<String>> obteUsuarisPerAssignar() {
		return obteUsuarisPerAssignar(getNomR(), getData(), getHoraInici());
	}

	public static ArrayList<ArrayList<String>> obteUsuarisPerAssignar(String nomR, Date d, Integer hi) {
		Calendar cal = Calendar.getInstance();
		Date dActual = cal.getTime();
		d = cal.getTime();

		if (dActual.after(d)) {
			// TODO activa excepcio [reservaCaducada]
		}
		ReservaAmbNotificacio r = FactoriaDades.getCtrlReserva().getByPK(nomR, d, hi);
//		if (!r.getEsAmbNotificacio())
			;// TODO activa excepcio [NoReservaAmbNotificacio]
		List<Usuari> totsUsuaris = FactoriaDades.getCtrlUsuari().getAll();
		List<Usuari> usus = r.getUsuarisEsNotifica();
		if (usus.size() >= 10)
			; // TODO activa exc reservaATope
		ArrayList<ArrayList<String>> infoUsuaris = new ArrayList<ArrayList<String>>();

		for (Usuari u : totsUsuaris) {
			if (!usus.contains(u)) {
				infoUsuaris.add(u.getInfo()); // IMPLEMENTAR FUNCIO
			}
		}
		if (infoUsuaris.size() == 0)
			;// TODO activa exp nohihaprouusuaris
		return infoUsuaris;

	}

	public static void assignarUsuarisAReserva(ArrayList<String> usuaris) {

		Set<Usuari> usuReserv = new HashSet<Usuari>();
		for (String u : usuaris) {
			usuReserv.add(FactoriaDades.getCtrlUsuari().getByPK(u));
		}
		ReservaAmbNotificacio r = FactoriaDades.getCtrlReserva().getByPK(getNomR(), getData(), getHoraInici());
		r.getUsuarisEsNotifica().addAll(usuReserv);
		BasicRepo.saveOrUpdate(r);

	}

}
