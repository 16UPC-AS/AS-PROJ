package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
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

	// bi-directional many-to-many association to ReservaAmbNotificacio
	@ManyToMany(mappedBy = "usuarisEsNotifica", cascade = { CascadeType.ALL })
	private Set<ReservaAmbNotificacio> reservesEsNotifica = new HashSet<ReservaAmbNotificacio>(0);;

	// bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy = "usuariAutor")
	private List<Reserva> reserves;

	public Usuari() {
	}

	public Usuari(String username, String nom, String mail) {
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

	public Set<ReservaAmbNotificacio> getReservesEsNotifica() {
		return this.reservesEsNotifica;
	}

	public void setReservesEsNotifica(Set<ReservaAmbNotificacio> reservesEsNotifica) {
		this.reservesEsNotifica = reservesEsNotifica;
	}

	public List<Reserva> getReserves() {
		return this.reserves;
	}

	public void setReserves(List<Reserva> reserves) {
		this.reserves = reserves;
	}

	public Reserva addReserva(Reserva reserva) {
		getReserves().add(reserva);
		reserva.setUsuariAutor(this);
		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Usuari))
			return false;
		Usuari u = (Usuari) o;
		if (u.getUsername().equals(username))
			return true;
		return false;

	}

}