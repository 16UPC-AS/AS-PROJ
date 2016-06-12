package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the reservesambnotificacio database table.
 * 
 */
@Entity
@Table(name = "reservesambnotificacio")
@DiscriminatorValue("1")
@NamedQuery(name = "ReservaAmbNotificacio.findAll", query = "SELECT r FROM ReservaAmbNotificacio r")
public class ReservaAmbNotificacio extends Reserva {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-many association to Usuari
	// bi-directional many-to-many association to ReservaAmbNotificacio
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "esnotifica", joinColumns = { @JoinColumn(name = "reserva") }, inverseJoinColumns = {
			@JoinColumn(name = "usuari") })
	private Set<Usuari> usuarisEsNotifica = new HashSet<Usuari>();

	public ReservaAmbNotificacio() {
	}

	public ReservaAmbNotificacio(Recurs rec, Usuari u, Date data, Integer horaInici, Integer horaFi, String comentari) {
		super(rec, u, data, horaInici, horaFi, comentari, true);
		this.usuarisEsNotifica.add(u);
	}

	public Set<Usuari> getUsuarisEsNotifica() {
		return this.usuarisEsNotifica;
	}

	public void setUsuarisEsNotifica(Set<Usuari> usuarisEsNotifica) {
		this.usuarisEsNotifica = usuarisEsNotifica;
	}

	@Transient
	public ArrayList<String> getInfoPerServei() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<String> i = new ArrayList<String>();
		String nr = getRecurs().getNom();
		String nu = getUsuariAutor().getUsername();
		i.add(nr);
		i.add(sdf.format(getData()));
		i.add(getHoraInici().toString());
		i.add(getHoraFi().toString());
		i.add(nu);
		i.add(getComentari());
		return i;
	}

}