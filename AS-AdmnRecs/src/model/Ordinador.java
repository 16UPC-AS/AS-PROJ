package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the ordinadors database table.
 * 
 */
@Entity
@Table(name = "ordinadors")
@DiscriminatorValue("1")
@NamedQuery(name = "Ordinador.findAll", query = "SELECT o FROM Ordinador o")
public class Ordinador extends Recurs {
	private static final long serialVersionUID = 1L;

	private String marca;

	private String model;

	// bi-directional one-to-one association to Sala
	@OneToOne(mappedBy = "ordinador")
	private Sala sala;

	public Ordinador(){
		super();
	}
	
	public Ordinador(String nom, String marca, String model) {
		super(nom, 1);
		this.marca = marca;
		this.model = model;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}