package mon.pfe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
public class Demande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id_demande;
	
	private Date date_envoit; 
	
	private Date date_debut; 
	
	private Date date_fin;
	
	private int periode;
	
	private Long id_employer;

	private int id_type;

	private char valid;
	
	public Demande() {
		super();
	}

	public void setId_demande(Long id_demande) {
		this.id_demande = id_demande;
	}
	public Long getId_employer() {
		return id_employer;
	}
	public void setId_employer(Long id_employer) {
		this.id_employer = id_employer;
	}
	public int getId_type() {
		return id_type;
	}
	public void setId_type(int id_type) {
		this.id_type = id_type;
	}
	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}
	public Long getId_demande() {
		return id_demande;
	}
	
	public char getValid() {
		return valid;
	}

	public void setValid(char valid) {
		this.valid = valid;
	}

	public Date getDate_envoit() {
		return date_envoit;
	}
	public void setDate_envoit(Date date_envoit) {
		this.date_envoit = date_envoit;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

//	public Demande( Date date_envoit, Date date_debut, Date date_fin, int periode, Long id_employer,
//			int id_type) {
//		super();
//		this.date_envoit = date_envoit;
//		this.date_debut = date_debut;
//		this.date_fin = date_fin;
//		this.periode = periode;
//		this.id_employer = id_employer;
//		this.id_type = id_type;
//	}

	public Demande(Date date_envoit, Date date_debut, Date date_fin, int periode, Long id_employer, int id_type,
			char valid) {
		super();
		this.date_envoit = date_envoit;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.periode = periode;
		this.id_employer = id_employer;
		this.id_type = id_type;
		this.valid = valid;
	}

	public Demande(Long id_demande, Date date_envoit, Date date_debut, Date date_fin, int periode, Long id_employer,
			int id_type, char valid) {
		super();
		this.id_demande = id_demande;
		this.date_envoit = date_envoit;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.periode = periode;
		this.id_employer = id_employer;
		this.id_type = id_type;
		this.valid = valid;
	}


	
	
	
	
	

	
}
