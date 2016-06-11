package services;

import java.util.HashMap;
import java.util.HashSet;

public class ServiceLocator {
private static ServiceLocator singleton;
    private static HashMap<String,Object> svs = new HashMap<String, Object>();
	/** Implementació del patró Singleton. **/
	public static ServiceLocator getInstance() {
        if (singleton == null) 
        	singleton = new ServiceLocator() {
        };
        return singleton;
    }
	
	public ServiceLocator() {}

	public Object find(String nom) {
		if (svs.containsKey(nom)){
			return svs.get(nom);
		}
		return null;
		// excepcio servei no disponibles
	}
}
