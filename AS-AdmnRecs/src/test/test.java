package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import casosDus.CUConsultaRecurs;
import casosDus.CUCreaReservaAmbNotificacio;
import casosDus.CUUsuarisAAsig;
import model.Ordinador;
import model.Projector;
import model.Sala;
import model.Usuari;
import repositories.BasicRepo;

public class test {

	public static void main(String[] args) {

		Ordinador ord = new Ordinador("pc3", "marca1", "model1");
		BasicRepo.saveOrUpdate(ord);
		Projector proj = new Projector("pc1", "1920x1080");
		BasicRepo.saveOrUpdate(proj);
		Sala sala = new Sala("sala2", "bcn", 710);
		sala.setOrdinador(ord);
		sala.setProjector(proj);
		BasicRepo.saveOrUpdate(sala);
		// Projector proj2 = new Projector("proj2", sala, "1920x1080");
		// BasicRepo.saveOrUpdate(proj2);
		// Usuari user = new Usuari("username1", "name1", "mail1");
		// BasicRepo.saveOrUpdate(user);

		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2016);
		// cal.set(Calendar.MONTH, Calendar.APRIL);
		// cal.set(Calendar.DAY_OF_MONTH, 17);
		// Date d = cal.getTime();
		// System.out.println(CUConsultaRecurs.obteRecursosDisponiblesPerData(d,
		// 10, 12).toString());
		// System.out.println(CUUsuarisAAsig.obteUsuarisAAssignar("proj2", d,
		// 13));
		// ArrayList<String> usuaris = new ArrayList<String>();
		// usuaris.add("username1");
		// CUUsuarisAAsig.afegirUsuarisAReserva(usuaris);
		// System.out.println(CUCreaReservaAmbNotificacio.obteRecursosDisponibles(d,
		// 14, 17));
		// CUCreaReservaAmbNotificacio.creaReservaAmbNotificacio("pc1",
		// "username1", "ksahdkh");
		// System.out.println(CUCreaReservaAmbNotificacio.obteUsuarisPerAssignar());
		// CUCreaReservaAmbNotificacio.assignarUsuarisAReserva(usuaris);

	}

}
