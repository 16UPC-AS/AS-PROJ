package init;

import dataControllers.BasicRepo;
import excepcions.ExcUsuariNoExisteix;
import model.Usuari;

public class carrega {

	public static void main(String[] args) throws ExcUsuariNoExisteix {
//
//		for (int i = 0; i < 10; ++i) {
//			Ordinador ord = new Ordinador("ordinador" + i, "marca" + i, "model" + i);
//			BasicRepo.saveOrUpdate(ord);
//		}
//		for (int i = 0; i < 10; ++i) {
//			Projector proj = new Projector("projector" + i, "1920x1080");
//			BasicRepo.saveOrUpdate(proj);
//		}
//
//		for (int i = 0; i < 10; ++i) {
//			Sala sala = new Sala("sala" + i, "ubicacio" + i, (i * 10) + 5);
//			BasicRepo.saveOrUpdate(sala);
//		}
//		for (int i = 10; i < 20; ++i) {
//			Ordinador ord = new Ordinador("ordinador" + i, "marca" + i, "model" + i);
//			BasicRepo.saveOrUpdate(ord);
//			Projector proj = new Projector("projector" + i, "1920x1080");
//			BasicRepo.saveOrUpdate(proj);
//			Sala sala = new Sala("sala" + i, "ubicacio" + i, i * 10);
//			sala.setOrdinador(ord);
//			sala.setProjector(proj);
//			BasicRepo.saveOrUpdate(sala);
//		}
		for (int i = 0; i < 30; ++i) {
			Usuari user = new Usuari("username" + i, "name" + i, "reservesarqsoft@gmail.com");
			BasicRepo.saveOrUpdate(user);
		}

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
		// usuaris.add("username3");
		// CUUsuarisAAsig.afegirUsuarisAReserva(usuaris);
		// System.out.println(CUCreaReservaAmbNotificacio.obteRecursosDisponibles(d,
		// 15, 24));
		// CUCreaReservaAmbNotificacio.creaReservaAmbNotificacio("ordinador1",
		// "username9", "ksahdkh");
		// System.out.println(
		// FactoriaDades.getReservaAmbNot().getByPK("ordinador1", d,
		// 15).getUsuarisEsNotifica().toString());
		// System.out.println(CUCreaReservaAmbNotificacio.obteUsuarisPerAssignar());
		// CUCreaReservaAmbNotificacio.assignarUsuarisAReserva(usuaris);

	}

}
