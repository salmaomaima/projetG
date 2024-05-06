package mon.pfe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



@Entity
public class Notification_Reprise implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id_reprise;
	private Long id_employer;
	private Long id_demande;
	private Date date_retour;
	private Date date_envoit;
	
	
	public Long getId_reprise() {
		return id_reprise;
	}
	public void setId_reprise(Long id_reprise) {
		this.id_reprise = id_reprise;
	}
	public Long getId_employer() {
		return id_employer;
	}
	public void setId_employer(Long id_employer) {
		this.id_employer = id_employer;
	}
	public Long getId_demande() {
		return id_demande;
	}
	public void setId_demande(Long id_demande) {
		this.id_demande = id_demande;
	}
	public Date getDate_retour() {
		return date_retour;
	}
	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}
	public Date getDate_envoit() {
		return date_envoit;
	}
	public void setDate_envoit(Date date_envoit) {
		this.date_envoit = date_envoit;
	}
	public Notification_Reprise(Long id_employer, Long id_demande, Date date_retour, Date date_envoit) {
		super();
		this.id_employer = id_employer;
		this.id_demande = id_demande;
		this.date_retour = date_retour;
		this.date_envoit = date_envoit;
	}
	public Notification_Reprise(Long id_reprise, Long id_employer, Long id_demande, Date date_retour,
			Date date_envoit) {
		super();
		this.id_reprise = id_reprise;
		this.id_employer = id_employer;
		this.id_demande = id_demande;
		this.date_retour = date_retour;
		this.date_envoit = date_envoit;
	}
	
	
	
	

	
	
	
	
}