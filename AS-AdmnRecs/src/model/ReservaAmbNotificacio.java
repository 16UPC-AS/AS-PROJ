package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

	private String comentari;

	// bi-directional many-to-many association to Usuari
	@ManyToMany(mappedBy = "reservesEsNotifica")
	private List<Usuari> usuarisEsNotifica;

	public ReservaAmbNotificacio() {
	}

	public ReservaAmbNotificacio(Recurs rec, Usuari u, Date data, Integer horaInici, Integer horaFi, String comentari) {

	}

	public String getComentari() {
		return this.comentari;
	}

	public void setComentari(String comentari) {
		this.comentari = comentari;
	}

	public List<Usuari> getUsuarisEsNotifica() {
		return this.usuarisEsNotifica;
	}

	public void setUsuarisEsNotifica(List<Usuari> usuarisEsNotifica) {
		this.usuarisEsNotifica = usuarisEsNotifica;
	}

	@Transient
	public ArrayList<String> getInfoPerServei() {
		// TODO Auto-generated method stub
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> mails = new ArrayList<String>();
		for (Usuari u : usuarisEsNotifica)
			mails.add(u.getMail());
		String nr = getRecurs().getNom();
		String nu = getUsuariAutor().getUsername();
		i.add(nr);
		i.add(getData().toString());
		i.add(getHoraInici().toString());
		i.add(getHoraFi().toString());
		i.add(nu);
		i.add(getComentari());
		i.addAll(mails);
		return i;
	}

}