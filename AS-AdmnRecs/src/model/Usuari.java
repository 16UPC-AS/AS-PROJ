package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

	// bi-directional many-to-many association to ReservaAmbNotificacio
	@ManyToMany
	@JoinTable(name = "esnotifica", joinColumns = { @JoinColumn(name = "usuari") }, inverseJoinColumns = {
			@JoinColumn(name = "reserva") })
	private List<ReservaAmbNotificacio> reservesEsNotifica;

	// bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy = "usuariAutor")
	private List<Reserva> reserves;

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

	public List<ReservaAmbNotificacio> getReservesEsNotifica() {
		return this.reservesEsNotifica;
	}

	public void setReservesEsNotifica(List<ReservaAmbNotificacio> reservesEsNotifica) {
		this.reservesEsNotifica = reservesEsNotifica;
	}

	public List<Reserva> getReserves() {
		return this.reserves;
	}

	public void setReserves(List<Reserva> reserves) {
		this.reserves = reserves;
	}

	public Reserva addReserve(Reserva reserva) {
		getReserves().add(reserva);
		reserva.setUsuariAutor(this);

		return reserva;
	}

	public Reserva removeReserve(Reserva reserva) {
		getReserves().remove(reserva);
		reserva.setUsuariAutor(null);

		return reserva;
	}

	public ArrayList<String> getInfo() {

		ArrayList<String> info = new ArrayList<String>();
		info.add(getUsername());
		info.add(getNom());
		info.add(getMail());

		return info;
	}

}