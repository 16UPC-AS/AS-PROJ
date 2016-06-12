package dataInterfaces;

import java.util.Date;
import java.util.List;

import model.Recurs;
import model.Reserva;
import model.ReservaAmbNotificacio;
import model.Usuari;

public interface CtrlReserva {

	public Reserva getByPK(String nomR, Date d, Integer hi);

	public List<Reserva> getByUsuari(Usuari u);

	public List<ReservaAmbNotificacio> getAll();

	public List<ReservaAmbNotificacio> getByRecurs(Recurs recurs);

}
