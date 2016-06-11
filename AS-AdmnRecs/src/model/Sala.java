package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sales database table.
 * 
 */
@Entity
@Table(name="sales")
@DiscriminatorValue("0")
@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala extends Recurs{
	private static final long serialVersionUID = 1L;

	private Integer aforament;

	private String ubicacio;

	//uni-directional one-to-one association to Ordinador
	@OneToOne
	@JoinColumn(name="ordinador")
	private Ordinador ordinadorBean;

	//uni-directional one-to-one association to Projector
	@OneToOne
	@JoinColumn(name="projector")
	private Projector projectorBean;

	public Sala() {
	}

	public Sala(String nom, String ubicacio, int aforament) {
		super(nom,0);
		this.ubicacio = ubicacio;
		this.aforament = aforament;
		// TODO Auto-generated constructor stub
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

	public Ordinador getOrdinadorBean() {
		return this.ordinadorBean;
	}

	public void setOrdinadorBean(Ordinador ordinadorBean) {
		this.ordinadorBean = ordinadorBean;
	}

	public Projector getProjectorBean() {
		return this.projectorBean;
	}

	public void setProjectorBean(Projector projectorBean) {
		this.projectorBean = projectorBean;
	}

}