package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the projectors database table.
 * 
 */
@Entity
@Table(name = "projectors")
@DiscriminatorValue("1")
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

}