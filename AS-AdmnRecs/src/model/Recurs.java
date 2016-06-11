package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the recursos database table.
 * 
 */
@Entity
@Table(name = "recursos")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@NamedQuery(name = "Recurs.findAll", query = "SELECT r FROM Recurs r")
public class Recurs implements Serializable {
	public Recurs(String nom, Integer type) {
		super();
		this.nom = nom;
		this.type = type;
	}

	private static final long serialVersionUID = 1L;

	@Id
	private String nom;

	private Integer type;

	// bi-directional many-to-one association to ReservaAmbNotificacio
	@OneToMany(mappedBy = "recurs")
	private List<ReservaAmbNotificacio> reservesAmbNotificacio;

	// bi-directional many-to-one association to ReservaSenseNotificacio
	@OneToMany(mappedBy = "recurs")
	private List<ReservaSenseNotificacio> reservesSenseNotificacio;

	public Recurs() {
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<ReservaAmbNotificacio> getReservesAmbNotificacio() {
		return this.reservesAmbNotificacio;
	}

	public void setReservesAmbNotificacio(List<ReservaAmbNotificacio> reservesAmbNotificacio) {
		this.reservesAmbNotificacio = reservesAmbNotificacio;
	}

	public ReservaAmbNotificacio addReservesAmbNotificacio(ReservaAmbNotificacio reservesAmbNotificacio) {
		getReservesAmbNotificacio().add(reservesAmbNotificacio);
		reservesAmbNotificacio.setRecurs(this);

		return reservesAmbNotificacio;
	}

	public ReservaAmbNotificacio removeReservesAmbNotificacio(ReservaAmbNotificacio reservesAmbNotificacio) {
		getReservesAmbNotificacio().remove(reservesAmbNotificacio);
		reservesAmbNotificacio.setRecurs(null);

		return reservesAmbNotificacio;
	}

	public List<ReservaSenseNotificacio> getReservesSenseNotificacio() {
		return this.reservesSenseNotificacio;
	}

	public void setReservesSenseNotificacio(List<ReservaSenseNotificacio> reservesSenseNotificacio) {
		this.reservesSenseNotificacio = reservesSenseNotificacio;
	}

	public ReservaSenseNotificacio addReservesSenseNotificacio(ReservaSenseNotificacio reservesSenseNotificacio) {
		getReservesSenseNotificacio().add(reservesSenseNotificacio);
		reservesSenseNotificacio.setRecurs(this);

		return reservesSenseNotificacio;
	}

	public ReservaSenseNotificacio removeReservesSenseNotificacio(ReservaSenseNotificacio reservesSenseNotificacio) {
		getReservesSenseNotificacio().remove(reservesSenseNotificacio);
		reservesSenseNotificacio.setRecurs(null);

		return reservesSenseNotificacio;
	}

	@Transient
	public ArrayList<String> estasDisp(Date d, Integer hi, Integer hf) {

		for (ReservaAmbNotificacio r : reservesAmbNotificacio) {
			if (!r.periodeNoSolapat(d, hi, hf))
				return null;
		}
		for (ReservaSenseNotificacio r : reservesSenseNotificacio) {
			if (!r.periodeNoSolapat(d, hi, hf))
				return null;
		}

		return this.getInfo();

	}

	private ArrayList<String> getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public Boolean etsSala() {
		return (getType().equals(0));
	}

}