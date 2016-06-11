package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the reservesambnotificacio database table.
 * 
 */
@Entity
@Table(name = "reservesambnotificacio")
@NamedQuery(name = "ReservaAmbNotificacio.findAll", query = "SELECT r FROM ReservaAmbNotificacio r")
public class ReservaAmbNotificacio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

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
	private Usuari usuari;

	// bi-directional many-to-many association to Usuari
	@ManyToMany(mappedBy = "reservesEsNotifica")
	private List<Usuari> usuarisEsNotifica;

	public ReservaAmbNotificacio() {
	}

	public ReservaAmbNotificacio(Recurs recurs, Usuari usuari, Date data, Integer horaInici, Integer horaFi,
			String comentari) {
		this.recurs = recurs;
		this.usuari = usuari;
		this.data = data;
		this.horaInici = horaInici;
		this.horaFi = horaFi;
		this.comentari = comentari;
		
		
		// TODO Auto-generated constructor stub
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

	public List<Usuari> getUsuarisEsNotifica() {
		return this.usuarisEsNotifica;
	}

	public void setUsuarisEsNotifica(List<Usuari> usuarisEsNotifica) {
		this.usuarisEsNotifica = usuarisEsNotifica;
	}

	@Transient
	public boolean periodeNoSolapat(Date d, Integer hi, Integer hf) {
		return (!data.equals(d)) || (hi >= horaFi || hf <= horaInici);
	}

	public boolean recursEtsSala() {
		recurs.etsSala();
		return false;
	}
	
	public String getNomRecurs(){
		return recurs.getNom();
	}
	
	@Transient
	public ArrayList<String> getInfoPerServei() {
		// TODO Auto-generated method stub
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> mails = new ArrayList<String>();
		for (Usuari u : usuarisEsNotifica)
			mails.add(u.getMail());
		String nr = getRecurs().getNom();
		String nu = getUsuari().getUsername();
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