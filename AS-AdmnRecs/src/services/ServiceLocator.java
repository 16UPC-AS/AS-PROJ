package services;

import java.util.HashMap;

import excepcions.ExcServeiNoDisponible;

public class ServiceLocator {
	private static ServiceLocator singleton;
	private static HashMap<String, Object> svs = new HashMap<String, Object>();

	/** Implementació del patró Singleton. **/
	public static ServiceLocator getInstance() {
		if (singleton == null)
			singleton = new ServiceLocator() {
			};
		return singleton;
	}

	public ServiceLocator() {
	}

	public static Object find(String nom) throws ExcServeiNoDisponible {
		if (nom.equals(SvMissatgeria.getServicename())) {
			return new SvMissatgeria();
		} else {
			throw new ExcServeiNoDisponible();
		}
	}
}
