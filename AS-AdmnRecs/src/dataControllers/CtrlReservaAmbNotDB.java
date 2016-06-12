package dataControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import dataInterfaces.CtrlReservaAmbNot;
import excepcions.ExcNoReservaAmbNotificacio;
import excepcions.ExcUsuariNoExisteix;
import model.Recurs;
import model.Reserva;
import model.ReservaAmbNotificacio;
import model.Usuari;
import util.HibernateUtil;

public class CtrlReservaAmbNotDB extends BasicRepo implements CtrlReservaAmbNot {


	@Override
	public ReservaAmbNotificacio getByPK(String nomR, Date d, Integer hi) {
		ReservaAmbNotificacio reserva = new ReservaAmbNotificacio();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery(
					"from Reserva where recurs ='" + nomR + "' and data = '" + d + "' and horainici=" + hi);
			reserva = (ReservaAmbNotificacio) query.list().get(0);
			Hibernate.initialize(reserva.getId());
			if (!reserva.getAmbnotificacio())
				throw new ExcNoReservaAmbNotificacio();
			Hibernate.initialize(reserva.getUsuarisEsNotifica());
		} catch (Exception e) {
			if (session != null) {
				System.out.println(nomR + " " + d.toString() + " " + hi);
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return reserva;
	}

	@Override
	public List<ReservaAmbNotificacio> getByUsuari(Usuari u) {
		List<ReservaAmbNotificacio> mList = new ArrayList<ReservaAmbNotificacio>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Query query = session.createQuery("from Reserva where unusuari='" + u.getUsername() + "'");
			// query.setString("u", u.getUsername());
			mList = (List<ReservaAmbNotificacio>) query.list();
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

	@Override
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

	@Override
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
