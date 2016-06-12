package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the reserves database table.
 * 
 */
@Entity
@Table(name = "reserves")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ambnotificacio", discriminatorType = DiscriminatorType.INTEGER)
@NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "reserves_id_sec")
	private Long id;

	private Boolean ambnotificacio;

	private String comentari;

	@Temporal(TemporalType.DATE)
	private Date data;

	private Integer horaFi;

	private Integer horaInici;

	// bi-directional many-to-one association to Recurs
	@ManyToOne
	@JoinColumn(name = "nrecurs")
	private Recurs recurs;

	// bi-directional many-to-one association to Usuari
	@ManyToOne
	@JoinColumn(name = "unusuari")
	private Usuari usuariAutor;

	public Reserva() {
	}

	public Reserva(Recurs recurs, Usuari usuari, Date data, Integer horaInici, Integer horaFi, String comentari,
			boolean ambnotificacio) {
		super();
		this.recurs = recurs;
		this.usuariAutor = usuari;
		this.data = data;
		this.horaInici = horaInici;
		this.horaFi = horaFi;
		this.ambnotificacio = ambnotificacio;
		this.comentari = comentari;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAmbnotificacio() {
		return this.ambnotificacio;
	}

	public void setAmbnotificacio(Boolean ambnotificacio) {
		this.ambnotificacio = ambnotificacio;
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

	public Usuari getUsuariAutor() {
		return this.usuariAutor;
	}

	public void setUsuariAutor(Usuari usuariAutor) {
		this.usuariAutor = usuariAutor;
	}

	public boolean periodeSolapat(Date d, Integer hi, Integer hf) {
		if (d.getDate() != data.getDate())
			return false;
		if (d.getMonth() != data.getMonth())
			return false;
		if (d.getYear() != data.getYear())
			return false;
		return (!(hi >= horaFi || hf <= horaInici));
	}

	public boolean recursEsSala() {
		return getRecurs().etsSala();
	}

	public boolean reservaSolapada(Date d, Integer hi, Integer hf, String nomR) {
		return (periodeSolapat(d, hi, hf) && recursEsSala());
	}

	@Transient
	public ArrayList<String> getInfoPerServei() {

		// TODO Activa reservanoNotificada
		return null;
	}
}