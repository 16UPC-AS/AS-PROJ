package dataControllers;

import org.hibernate.Session;

import excepcions.ExcUsuariNoExisteix;
import model.Reserva;
import model.Usuari;
import util.HibernateUtil;

public class BasicRepo {
	public static void saveOrUpdate(Object o) throws ExcUsuariNoExisteix {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(o);
			session.getTransaction().commit();
		} catch (org.hibernate.id.IdentifierGenerationException e) {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			if ((o instanceof Reserva) && e.getMessage().toLowerCase()
					.contains(new String(
							"ids for this class must be manually assigned before calling save(): model.Usuari")
									.toLowerCase())) {
				throw new ExcUsuariNoExisteix();
			}

		} catch (Exception e) {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void persist(Object o) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.persist(o);
			// session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
