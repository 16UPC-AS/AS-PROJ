package test;

import model.Usuari;
import repositories.CtrlUsuari;

public class TestUsuaris {

	public static void main(String[] args) {
		Usuari u2 = new Usuari("FakeUser", "FakeName", "Fake@Mail.com");
		CtrlUsuari.saveOrUpdate(u2);

		System.out.println(u2.getNom() + " " + u2.getUsername() + " " + u2.getMail());
		// u2 = UsuariRepo.getByPK(u2.getUniqueConstraint());

	}
}
