package adaptadors;

import excepcions.ExcServeiNoDisponible;
import model.ReservaAmbNotificacio;

public interface IAdaptadorEsNotifica {

	void enviarMissatge(ReservaAmbNotificacio r) throws ExcServeiNoDisponible;
	
}
