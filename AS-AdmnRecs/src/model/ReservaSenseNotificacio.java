package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reservessensenotificacio database table.
 * 
 */
@Entity
@Table(name="reservessensenotificacio")
@NamedQuery(name="ReservaSenseNotificacio.findAll", query="SELECT r FROM ReservaSenseNotificacio r")
public class ReservaSenseNotificacio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String comentari;

	@Temporal(TemporalType.DATE)
	private Date data;

	private Integer horaFi;

	private Integer horaInici;

	//bi-directional many-to-one association to Recurs
	@ManyToOne
	@JoinColumn(name="nrecurs")
	private Recurs recurs;

	//bi-directional many-to-one association to Usuari
	@ManyToOne
	@JoinColumn(name="unusuari")
	private Usuari usuari;

	public ReservaSenseNotificacio() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentari() {
		return this.comentari;
	}

	public void setComentari(String comentari) {
		this.comentari = comentari;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getHoraFi() {
		return this.horaFi;
	}

	public void setHoraFi(Integer horaFi) {
		this.horaFi = horaFi;
	}

	public Integer getHoraInici() {
		return this.horaInici;
	}

	public void setHoraInici(Integer horaInici) {
		this.horaInici = horaInici;
	}

	public Recurs getRecurs() {
		return this.recurs;
	}

	public void setRecurs(Recurs recurs) {
		this.recurs = recurs;
	}

	public Usuari getUsuari() {
		return this.usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

	@Transient
	public boolean periodeNoSolapat(Date d, Integer hi, Integer hf) {
		return (!data.equals(d)) || (hi >= horaFi || hf <= horaInici);
	}
}