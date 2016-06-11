package repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import model.Recurs;
import model.ReservaAmbNotificacio;
import model.Usuari;
import util.HibernateUtil;

public class CtrlReservaAmbNot extends BasicRepo {

	public static ReservaAmbNotificacio getByID(Long id) {
		ReservaAmbNotificacio reserva = new ReservaAmbNotificacio();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva where id = :id");
			query.setLong("id", id);
			reserva = (ReservaAmbNotificacio) query.list().get(0);
			Hibernate.initialize(reserva.getId());
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return reserva;
	}

	public static ReservaAmbNotificacio getByPK(String nomR, Date d, Integer hi) {
		ReservaAmbNotificacio reserva = new ReservaAmbNotificacio();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva where recurs =:nomR and data = :d and horainici=:hi ");
			query.setString("nomR", nomR);
			query.setDate("d", d);
			query.setInteger("hi", hi);
			reserva = (ReservaAmbNotificacio) query.list().get(0);
			Hibernate.initialize(reserva.getId());
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return reserva;
	}
	
	public static List<ReservaAmbNotificacio> getByUsuari(Usuari u) {
		List<ReservaAmbNotificacio> mList = new ArrayList<ReservaAmbNotificacio>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva where usuari=: u");
			query.setString("u", u.getUsername());
			mList = query.list();

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

	public static List<ReservaAmbNotificacio> getAll() {
		List<ReservaAmbNotificacio> mList = new ArrayList<ReservaAmbNotificacio>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva");
			mList = query.list();

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

	public static List<ReservaAmbNotificacio> getByRecurs(Recurs recurs) {
		List<ReservaAmbNotificacio> mList = new ArrayList<ReservaAmbNotificacio>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva where idrecurs  = :id");
			mList = query.list();
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
