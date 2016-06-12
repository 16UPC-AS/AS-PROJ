package dataControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import dataInterfaces.CtrlReserva;
import model.Recurs;
import model.Reserva;
import model.ReservaAmbNotificacio;
import model.Usuari;
import util.HibernateUtil;

public class CtrlReservaDB extends BasicRepo implements CtrlReserva {

	public Reserva getByPK(String nomR, Date d, Integer hi) {
		Reserva reserva = new Reserva();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva where recurs =:nomR and data = :d and horainici=:hi ");
			query.setString("nomR", nomR);
			query.setDate("d", d);
			query.setInteger("hi", hi);
			reserva = (Reserva) query.list().get(0);
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

	public List<Reserva> getByUsuari(Usuari u) {
		List<Reserva> mList = new ArrayList<Reserva>();
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

	public List<ReservaAmbNotificacio> getAll() {
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

	public List<ReservaAmbNotificacio> getByRecurs(Recurs recurs) {
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
