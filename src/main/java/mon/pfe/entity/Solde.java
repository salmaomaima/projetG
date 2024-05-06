package mon.pfe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
public class Solde implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id_solde;
	
	private long total_solde;
	private int solde_materniter;
	private int solde_specifique;
	private int solde_maladie;
	
	private int annee_solde;

	private Long id_employer;
	
	private Date mise_a_jours;
	
	
	public Long getId_Solde() {
		return id_solde;
	}
	public void setId_Solde(Long id_Solde) {
		this.id_solde = id_Solde;
	}

	public long getTotal_solde() {
		return total_solde;
	}

	public void setTotal_solde(long total_solde) {
		this.total_solde = total_solde;
	}

	public int getAnnee_solde() {
		return annee_solde;
	}

	public void setAnnee_solde(int annee_solde) {
		this.annee_solde = annee_solde;
	}

	public Long getId_employer() {
		return id_employer;
	}

	public void setId_employer(Long id_employer) {
		this.id_employer = id_employer;
	}

	public Date getMise_a_jours() {
		return mise_a_jours;
	}
	public void setMise_a_jours(Date mise_a_jours) {
		this.mise_a_jours = mise_a_jours;
	}

	public int getSolde_materniter() {
		return solde_materniter;
	}
	public void setSolde_materniter(int solde_materniter) {
		this.solde_materniter = solde_materniter;
	}
	public int getSolde_specifique() {
		return solde_specifique;
	}
	public void setSolde_specifique(int solde_specifique) {
		this.solde_specifique = solde_specifique;
	}
	public int getSolde_maladie() {
		return solde_maladie;
	}
	public void setSolde_maladie(int solde_maladie) {
		this.solde_maladie = solde_maladie;
	}

	public Solde() {
		super();
	}
	public Solde(Long id_solde, long total_solde, int solde_materniter, int solde_specifique, int solde_maladie,
			int annee_solde, Long id_employer, Date mise_a_jours) {
		super();
		this.id_solde = id_solde;
		this.total_solde = total_solde;
		this.solde_materniter = solde_materniter;
		this.solde_specifique = solde_specifique;
		this.solde_maladie = solde_maladie;
		this.annee_solde = annee_solde;
		this.id_employer = id_employer;
		this.mise_a_jours = mise_a_jours;
	}
	public Solde(long total_solde, int solde_materniter, int solde_specifique, int solde_maladie, int annee_solde,
			Long id_employer, Date mise_a_jours) {
		super();
		this.total_solde = total_solde;
		this.solde_materniter = solde_materniter;
		this.solde_specifique = solde_specifique;
		this.solde_maladie = solde_maladie;
		this.annee_solde = annee_solde;
		this.id_employer = id_employer;
		this.mise_a_jours = mise_a_jours;
	}
	
	

}
