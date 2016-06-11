package repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Usuari;
import util.HibernateUtil;

public class CtrlUsuari extends BasicRepo {

	public static Usuari getByPK(String username) {
		Usuari user = new Usuari();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Usuari where username = :username");
			query.setString("username", username);
			user = (Usuari) query.list().get(0);
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	public static List<Usuari> getAll() {
		List<Usuari> uList = new ArrayList<Usuari>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Usuari");
			uList = query.list();

		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return uList;

	}

//	public static Usuari update(Long id, String newName) {
//		Usuari c = new Usuari();
//		Session session = null;
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//
//			Query query = session.createQuery("from Usuari where id = :id");
//			query.setLong("id", id);
//			((Usuari) query.list().get(0)).setNom(newName);
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			if (session != null) {
//				session.getTransaction().rollback();
//			}
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//		return c;
//
//	}

//	public static void delete(Long id) {
//		Session session = null;
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//
//			Query query = session.createQuery("from Usuari where id = :id");
//			query.setLong("id", id);
//			session.delete(((Usuari) query.list().get(0)));
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			if (session != null) {
//				session.getTransaction().rollback();
//			}
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//
//	}

}
