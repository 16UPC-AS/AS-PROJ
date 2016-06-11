package adaptadors;

import model.ReservaAmbNotificacio;

public interface IAdaptadorEsNotifica {

	void enviarMissatge(ReservaAmbNotificacio r) throws Exception;
	
}
