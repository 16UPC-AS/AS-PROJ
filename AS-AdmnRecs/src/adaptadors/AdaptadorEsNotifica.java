package adaptadors;

import model.ReservaAmbNotificacio;
import services.ServiceLocator;
import services.SvMissatgeria;

public class AdaptadorEsNotifica implements IAdaptadorEsNotifica {

	public void enviarMissatge(ReservaAmbNotificacio r) throws Exception {
		r.getInfoPerServei();
		((SvMissatgeria) ServiceLocator.getInstance().find("SvMissatgeria")).enviarDadesReserva();
	}
}
