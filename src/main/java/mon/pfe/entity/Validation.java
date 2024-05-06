package mon.pfe.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;
@Entity
public class Validation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id_validation;

	private Date date_validation_rd;

	private Date date_validation_pr;

	private Long id_demande;

	private Long id_employer;
	
	private Long id_remplaçant;

	private Long id_responsable;

	private Long id_premier_responsable;

	char action_rd ;
	char action_pr ;
	char action_rh ;
	private String avis_rd;
	private String avis_pr;
	
	public Long getId_validation() {
		return id_validation;
	}

	public void setId_validation(Long id_validation) {
		this.id_validation = id_validation;
	}


	public char getAction_rd() {
		return action_rd;
	}

	public void setAction_rd(char action_rd) {
		this.action_rd = action_rd;
	}

	public char getAction_pr() {
		return action_pr;
	}

	public void setAction_pr(char action_pr) {
		this.action_pr = action_pr;
	}

	public Long getId_demande() {
		return id_demande;
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

	public Long getId_responsable() {
		return id_responsable;
	}

	public void setId_resposable(Long id_resposable) {
		this.id_responsable = id_resposable;
	}

	public Long getId_premier_responsable() {
		return id_premier_responsable;
	}

	public void setId_premier_responsable(Long id_premier_responsable) {
		this.id_premier_responsable = id_premier_responsable;
	}

	
	public Date getDate_validation_rd() {
		return date_validation_rd;
	}

	public void setDate_validation_rd(Date date_validation_rd) {
		this.date_validation_rd = date_validation_rd;
	}

	public Date getDate_validation_pr() {
		return date_validation_pr;
	}

	public void setDate_validation_pr(Date date_validation_pr) {
		this.date_validation_pr = date_validation_pr;
	}

	public String getAvis_pr() {
		return avis_pr;
	}

	public void setAvis_pr(String avis_pr) {
		this.avis_pr = avis_pr;
	}
	
	public Validation() {
		super();
	}

	public String getAvis_rd() {
		return avis_rd;
	}

	public void setAvis_rd(String avis_rd) {
		this.avis_rd = avis_rd;
	}

	public Long getId_remplaçant() {
		return id_remplaçant;
	}

	public void setId_remplaçant(Long id_remplaçant) {
		this.id_remplaçant = id_remplaçant;
	}

	
	

	public char getAction_rh() {
		return action_rh;
	}

	public void setAction_rh(char action_rh) {
		this.action_rh = action_rh;
	}

	public Validation(Date date_validation_rd, Long id_demande, Long id_employer, Long id_remplaçant,
			Long id_responsable, Long id_premier_responsable, char action_rd,char action_pr, String avis_rd) {
		super();
		this.date_validation_rd = date_validation_rd;
		this.id_demande = id_demande;
		this.id_employer = id_employer;
		this.id_remplaçant = id_remplaçant;
		this.id_responsable = id_responsable;
		this.id_premier_responsable = id_premier_responsable;
		this.action_rd = action_rd;
		this.action_pr = action_pr;
		this.avis_rd = avis_rd;
	}

	public Validation(Long id_validation, Date date_validation_rd, Date date_validation_pr, Long id_demande,
			Long id_employer, Long id_remplaçant, Long id_responsable, Long id_premier_responsable, char action_rd,
			char action_pr, char action_rh, String avis_rd, String avis_pr) {
		super();
		this.id_validation = id_validation;
		this.date_validation_rd = date_validation_rd;
		this.date_validation_pr = date_validation_pr;
		this.id_demande = id_demande;
		this.id_employer = id_employer;
		this.id_remplaçant = id_remplaçant;
		this.id_responsable = id_responsable;
		this.id_premier_responsable = id_premier_responsable;
		this.action_rd = action_rd;
		this.action_pr = action_pr;
		this.action_rh = action_rh;
		this.avis_rd = avis_rd;
		this.avis_pr = avis_pr;
	}

	public Validation(Date date_validation_rd, Long id_demande, Long id_employer, Long id_responsable, char action_rd) {
		super();
		this.date_validation_rd = date_validation_rd;
		this.id_demande = id_demande;
		this.id_employer = id_employer;
		this.id_responsable = id_responsable;
		this.action_rd = action_rd;
	}

	public Validation(Long id_validation, Date date_validation_rd, Long id_demande, Long id_employer,
			Long id_remplaçant, Long id_responsable, Long id_premier_responsable, char action_rd, char action_pr,
			String avis_rd) {
		super();
		this.id_validation = id_validation;
		this.date_validation_rd = date_validation_rd;
		this.id_demande = id_demande;
		this.id_employer = id_employer;
		this.id_remplaçant = id_remplaçant;
		this.id_responsable = id_responsable;
		this.id_premier_responsable = id_premier_responsable;
		this.action_rd = action_rd;
		this.action_pr = action_pr;
		this.avis_rd = avis_rd;
	}

	
}
