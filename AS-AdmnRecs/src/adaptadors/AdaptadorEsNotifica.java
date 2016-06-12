package adaptadors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import excepcions.ExcServeiNoDisponible;
import model.ReservaAmbNotificacio;
import model.Usuari;
import services.ServiceLocator;
import services.SvMissatgeria;

public class AdaptadorEsNotifica implements IAdaptadorEsNotifica {


	public void enviarMissatge(ReservaAmbNotificacio r) throws ExcServeiNoDisponible {
		String recurs = r.getRecurs().getNom();
		Date data = r.getData();
		Integer horaInici = r.getHoraInici();
		Integer horaFi =r.getHoraFi();
		String username = r.getUsuariAutor().getUsername();
		String comentari = r.getComentari();
		Set<String> emails = new HashSet<String>();
		for(Usuari u : r.getUsuarisEsNotifica()){
			emails.add(u.getMail());
		}
		
		
		
		ServiceLocator.getInstance();
		((SvMissatgeria) ServiceLocator.find("SvMissatgeria")).enviarDadesReserva(recurs,data,horaInici,horaFi,username,comentari,emails);
//		((SvMissatgeria) ServiceLocator.find("SvMissatgeria")).enviarDadesReserva(r.getInfoPerServei(),
//				r.getUsuarisEsNotifica());
		
		
	}

}
