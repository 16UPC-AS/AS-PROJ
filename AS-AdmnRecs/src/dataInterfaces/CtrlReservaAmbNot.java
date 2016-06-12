package dataInterfaces;

import java.util.Date;
import java.util.List;

import model.Recurs;
import model.ReservaAmbNotificacio;
import model.Usuari;

public interface CtrlReservaAmbNot {

	public ReservaAmbNotificacio getByPK(String nomR, Date d, Integer hi);

	public List<ReservaAmbNotificacio> getByUsuari(Usuari u);

	public List<ReservaAmbNotificacio> getAll();

	public List<ReservaAmbNotificacio> getByRecurs(Recurs recurs);

}
