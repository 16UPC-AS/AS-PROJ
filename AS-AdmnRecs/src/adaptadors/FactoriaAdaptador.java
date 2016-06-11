package adaptadors;

public class FactoriaAdaptador {
	private static FactoriaAdaptador singleton;
	private static IAdaptadorEsNotifica adaptadorEsNotifica;

	public static FactoriaAdaptador getInstance() {
		if (singleton == null)
			singleton = new FactoriaAdaptador() {
			};
		return singleton;
	}

	public static IAdaptadorEsNotifica getAdaptadorEsNotifica() {
		return adaptadorEsNotifica;
	}

	public static void setAdaptadorEsNotifica(IAdaptadorEsNotifica adaptadorEsNotifica) {
		FactoriaAdaptador.adaptadorEsNotifica = adaptadorEsNotifica;
	}
}
