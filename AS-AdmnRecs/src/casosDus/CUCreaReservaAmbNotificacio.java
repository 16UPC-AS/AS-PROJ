package casosDus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import adaptadors.FactoriaAdaptador;
import dataControllers.BasicRepo;
import dataInterfaces.FactoriaDades;
import excepcions.ExcNoHiHaProusUsuaris;
import excepcions.ExcNoHiHaRecursos;
import excepcions.ExcNoHiHaReserva;
import excepcions.ExcRecursSalaSolapada;
import excepcions.ExcReservaATope;
import excepcions.ExcReservaCaducada;
import excepcions.ExcServeiNoDisponible;
import excepcions.ExcUsuariNoExisteix;
import model.Recurs;
import model.ReservaAmbNotificacio;
import model.Usuari;

public class CUCreaReservaAmbNotificacio {

	private static Date data;
	private static Integer horaInici;
	private static Integer horaFi;
	private static String nomR;

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

	public static ArrayList<ArrayList<String>> obteRecursosDisponibles(Date d, Integer hi, Integer hf)
			throws ExcNoHiHaRecursos {
		setData(d);
		setHoraInici(hi);
		setHoraFi(hf);
		return CUConsultaRecursosDisponiblesPerData.obteRecursosDisponiblesPerData(d, hi, hf);
	}

	public static void creaReservaAmbNotificacio(String nomR, String username, String comentari)
			throws ExcRecursSalaSolapada, ExcUsuariNoExisteix, ExcServeiNoDisponible {
		Usuari u = FactoriaDades.getCtrlUsuari().getByPK(username);
		Recurs rec = FactoriaDades.getCtrlRecurs().getByPK(nomR);

		if (rec.etsSala()) {
			List<ReservaAmbNotificacio> lResv = FactoriaDades.getReservaAmbNot().getByUsuari(u);
			for (ReservaAmbNotificacio r : lResv) {
				if (r.reservaSolapada(data, horaInici, horaFi, nomR)) {
					throw new ExcRecursSalaSolapada();
				}
			}
		}

		ReservaAmbNotificacio r = new ReservaAmbNotificacio(rec, u, getData(), getHoraInici(), getHoraFi(), comentari);
		try {
			r.getUsuarisEsNotifica().add(u);
		} catch (DataIntegrityViolationException e) {
			throw new ExcUsuariNoExisteix();
		}
		BasicRepo.saveOrUpdate(r);
		setNomR(nomR);
		FactoriaAdaptador.getInstance();
		FactoriaAdaptador.getAdaptadorEsNotifica().enviarMissatge(r);
	}

	public static ArrayList<ArrayList<String>> obteUsuarisPerAssignar()
			throws ExcNoHiHaProusUsuaris, ExcNoHiHaReserva, ExcReservaCaducada {
		return CUAssignarUsuarisANotificarAUnaReserva.obteUsuarisPerAssignar(getNomR(), getData(), getHoraInici());
	}

	public static void assignarUsuarisAReserva(ArrayList<String> usuaris)
			throws ExcReservaATope, ExcServeiNoDisponible, ExcUsuariNoExisteix {
		CUAssignarUsuarisANotificarAUnaReserva.afegirUsuarisAReserva(usuaris);
	}

}
