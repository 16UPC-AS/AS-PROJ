package dataControllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import dataInterfaces.CtrlRecurs;
import model.Recurs;
import util.HibernateUtil;

public class CtrlRecursDB extends BasicRepo implements CtrlRecurs {

	@Override
	public Recurs getByPK(String nom) {
		Recurs recurs = new Recurs();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Recurs where nom = :nom");
			query.setString("nom", nom);
			recurs = (Recurs) query.list().get(0);
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return recurs;
	}

	@Override
	public List<Recurs> getAll() {
		List<Recurs> mList = new ArrayList<Recurs>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Recurs order by type asc,nom asc");
			mList = query.list();

			Hibernate.initialize(mList);
			for (Recurs rec : mList) {
				Hibernate.initialize(rec.getReserves());
			}

		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return mList;
	}

}
