package dataControllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import dataInterfaces.CtrlUsuari;
import excepcions.ExcUsuariNoExisteix;
import model.Usuari;
import util.HibernateUtil;

public class CtrlUsuariDB extends BasicRepo implements CtrlUsuari {

	@Override
	public Usuari getByPK(String username) {
		Usuari user = new Usuari();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Usuari where username = :username");
			query.setString("username", username);
			user = (Usuari) query.list().get(0);
			Hibernate.initialize(user.getReserves());
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

	@Override
	public List<Usuari> getAll() {
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
}
