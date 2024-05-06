package mon.pfe.entity;

import java.io.Serializable;



import javax.persistence.*;
@Entity
public class Employer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id_employer;
	
	@Column(unique = true)
	private String nom_utilisateur;
	
	private String mot_de_passe;

	private String adresse;

	private String telephone;

	private String grade; 
	
	private String nom;

	private String prenom;

	private Long idresponsable;

	private int id_departement;

	private int id_role;

	public Long getId_employer() {
		return id_employer;
	}

	public void setId_employer(Long id_employer) {
		this.id_employer = id_employer;
	}

	public String getNom_utilisateur() {
		return nom_utilisateur;
	}

	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getIdresponsable() {
		return idresponsable;
	}

	public void setIdresponsable(Long idresponsable) {
		this.idresponsable = idresponsable;
	}

	public int getId_departement() {
		return id_departement;
	}

	public void setId_departement(int id_departement) {
		this.id_departement = id_departement;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	public Employer(String nom_utilisateur, String mot_de_passe, String adresse, String telephone, String grade,
			String nom, String prenom, Long idresponsable, int id_departement, int id_role) {
		super();
		this.nom_utilisateur = nom_utilisateur;
		this.mot_de_passe = mot_de_passe;
		this.adresse = adresse;
		this.telephone = telephone;
		this.grade = grade;
		this.nom = nom;
		this.prenom = prenom;
		this.idresponsable = idresponsable;
		this.id_departement = id_departement;
		this.id_role = id_role;
	}

	public Employer() {
		super();
	}

	public Employer(Long id_employer) {
		super();
		this.id_employer = id_employer;
	}

	public Employer(Long id_employer, String nom_utilisateur, String mot_de_passe, String adresse, String telephone,
			String grade, String nom, String prenom, Long idresponsable, int id_departement, int id_role) {
		super();
		this.id_employer = id_employer;
		this.nom_utilisateur = nom_utilisateur;
		this.mot_de_passe = mot_de_passe;
		this.adresse = adresse;
		this.telephone = telephone;
		this.grade = grade;
		this.nom = nom;
		this.prenom = prenom;
		this.idresponsable = idresponsable;
		this.id_departement = id_departement;
		this.id_role = id_role;
	}

	

	public Employer(Long id_employer, String adresse, String telephone, String nom, String prenom) {
		super();
		this.id_employer = id_employer;
		this.adresse = adresse;
		this.telephone = telephone;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Employer(Long id_employer, String mot_de_passe) {
		super();
		this.id_employer = id_employer;
		this.mot_de_passe = mot_de_passe;
	}

	


	

	
	


	
	
	
	
	
	

}
