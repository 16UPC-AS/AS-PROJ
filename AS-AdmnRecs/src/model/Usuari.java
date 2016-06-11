package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the usuaris database table.
 * 
 */
@Entity
@Table(name = "usuaris")
@NamedQuery(name = "Usuari.findAll", query = "SELECT u FROM Usuari u")
public class Usuari implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String mail;

	private String nom;

	// bi-directional many-to-one association to ReservaAmbNotificacio
	@OneToMany(mappedBy = "usuari")
	private List<ReservaAmbNotificacio> reservesAmbNotificacio;

	// bi-directional many-to-one association to ReservaSenseNotificacio
	@OneToMany(mappedBy = "usuari")
	private List<ReservaSenseNotificacio> reservesSenseNotificacio;

	// bi-directional many-to-many association to ReservaAmbNotificacio
	@ManyToMany
	@JoinTable(name = "esnotifica", joinColumns = { @JoinColumn(name = "usuari") }, inverseJoinColumns = {
			@JoinColumn(name = "reserva") })
	private List<ReservaAmbNotificacio> reservesEsNotifica;

	public Usuari() {
	}

	public Usuari(String username, String mail, String nom) {
		super();
		this.username = username;
		this.mail = mail;
		this.nom = nom;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ReservaAmbNotificacio> getReservesAmbNotificacio() {
		return this.reservesAmbNotificacio;
	}

	public void setReservesAmbNotificacio(List<ReservaAmbNotificacio> reservesAmbNotificacio) {
		this.reservesAmbNotificacio = reservesAmbNotificacio;
	}

	public ReservaAmbNotificacio addReservesAmbNotificacio(ReservaAmbNotificacio reservesAmbNotificacio) {
		getReservesAmbNotificacio().add(reservesAmbNotificacio);
		reservesAmbNotificacio.setUsuari(this);

		return reservesAmbNotificacio;
	}

	public ReservaAmbNotificacio removeReservesAmbNotificacio(ReservaAmbNotificacio reservesAmbNotificacio) {
		getReservesAmbNotificacio().remove(reservesAmbNotificacio);
		reservesAmbNotificacio.setUsuari(null);

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
		reservesSenseNotificacio.setUsuari(this);

		return reservesSenseNotificacio;
	}

	public ReservaSenseNotificacio removeReservesSenseNotificacio(ReservaSenseNotificacio reservesSenseNotificacio) {
		getReservesSenseNotificacio().remove(reservesSenseNotificacio);
		reservesSenseNotificacio.setUsuari(null);

		return reservesSenseNotificacio;
	}

	public List<ReservaAmbNotificacio> getReservesEsNotifica() {
		return this.reservesEsNotifica;
	}

	public void setReservesEsNotifica(List<ReservaAmbNotificacio> reservesEsNotifica) {
		this.reservesEsNotifica = reservesEsNotifica;
	}

	public ArrayList<String> getInfo() {

		ArrayList<String> info = new ArrayList<String>();
		info.add(getUsername());
		info.add(getNom());
		info.add(getMail());

		return info;
	}

}