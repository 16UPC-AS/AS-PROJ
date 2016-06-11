package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the sales database table.
 * 
 */
@Entity
@Table(name = "sales")
@DiscriminatorValue("0")
@NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
public class Sala extends Recurs {
	private static final long serialVersionUID = 1L;

	private Integer aforament;

	private String ubicacio;

	// bi-directional one-to-one association to Ordinador
	@OneToOne
	@JoinColumn(name = "ordinador")
	private Ordinador ordinador;

	// bi-directional one-to-one association to Projector
	@OneToOne
	@JoinColumn(name = "projector")
	private Projector projector;

	// bi-directional one-to-one association to Recurs
	@OneToOne
	@JoinColumn(name = "nom")
	private Recurs recurs;

	public Sala() {
	}

	public Sala(String nom, String ubicacio, int aforament) {
		super(nom, 0);
		this.ubicacio = ubicacio;
		this.aforament = aforament;
	}

	public Integer getAforament() {
		return this.aforament;
	}

	public void setAforament(Integer aforament) {
		this.aforament = aforament;
	}

	public String getUbicacio() {
		return this.ubicacio;
	}

	public void setUbicacio(String ubicacio) {
		this.ubicacio = ubicacio;
	}

	public Ordinador getOrdinador() {
		return this.ordinador;
	}

	public void setOrdinador(Ordinador ordinador) {
		this.ordinador = ordinador;
	}

	public Projector getProjector() {
		return this.projector;
	}

	public void setProjector(Projector projector) {
		this.projector = projector;
	}

	public Recurs getRecurs() {
		return this.recurs;
	}

	public void setRecurs(Recurs recurs) {
		this.recurs = recurs;
	}

	@Transient
	@Override
	public Boolean etsSala() {
		return true;
	}

}