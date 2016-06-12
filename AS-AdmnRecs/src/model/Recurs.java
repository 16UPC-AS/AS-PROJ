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
	private static final long serialVersionUID = 1L;

	@Id
	private String nom;

	private Integer type;

	public Recurs(String nom, Integer type) {
		super();
		this.nom = nom;
		this.type = type;
	}

	// bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy = "recurs")
	private List<Reserva> reserves;

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

	public List<Reserva> getReserves() {
		return this.reserves;
	}

	public void setReserves(List<Reserva> reserves) {
		this.reserves = reserves;
	}

	public Reserva addReserve(Reserva reserva) {
		getReserves().add(reserva);
		reserva.setRecurs(this);

		return reserva;
	}

	public Reserva removeReserve(Reserva reserva) {
		getReserves().remove(reserva);
		reserva.setRecurs(null);

		return reserva;
	}

	@Transient
	public ArrayList<String> estasDisp(Date d, Integer hi, Integer hf) {
		for (Reserva r : getReserves()) {
			if ((r.periodeSolapat(d, hi, hf)))
				return null;
		}
		return this.getInfo();
	}

	@Transient
	public ArrayList<String> getInfo() {
		// ArrayList<String>
		return null;
	}

	@Transient
	public Boolean etsSala() {
		return false;
	}

}