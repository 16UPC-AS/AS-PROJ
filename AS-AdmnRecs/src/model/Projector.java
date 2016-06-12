package model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the projectors database table.
 * 
 */
@Entity
@Table(name = "projectors")
@DiscriminatorValue("2")
@NamedQuery(name = "Projector.findAll", query = "SELECT p FROM Projector p")
public class Projector extends Recurs {
	private static final long serialVersionUID = 1L;
	private String resolucio;

	// bi-directional one-to-one association to Sala
	@OneToOne(mappedBy = "projector")
	private Sala sala;

	public Projector() {
	}

	public Projector(String nom, String resolucio) {
		super(nom, 2);
		this.resolucio = resolucio;
		// TODO Auto-generated constructor stub
	}

	public String getResolucio() {
		return this.resolucio;
	}

	public void setResolucio(String resolucio) {
		this.resolucio = resolucio;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	
	@Transient
	@Override
	public ArrayList<String> estasDisp(Date d, Integer hi, Integer hf) {
		if (getSala() != null)
			return null;
		for (Reserva r : getReserves()) {
			if ((r.periodeSolapat(d, hi, hf)))
				return null;
		}
		return this.getInfo();
	}
	
	@Transient
	@Override
	public ArrayList<String> getInfo() {
		ArrayList<String> info = new ArrayList<String>();
		info.add(getNom());
		info.add(null);
		info.add(null);
		info.add(resolucio);
		info.add(null);
		info.add(null);
		info.add(null);
		info.add(null);
		info.add(null);
		return info;
	}

}