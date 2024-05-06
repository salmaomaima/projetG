package mon.pfe.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class  Departement implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id_departement;
	
	private Long id_chef;

	private String nom_departement;


	public int getId_departement() {
		return id_departement;
	}
	
	public Long getId_chef() {
		return id_chef;
	}
	public void setId_chef(Long id_chef) {
		this.id_chef = id_chef;
	}
	public String getNom_departement() {
		return nom_departement;
	}



	public void setId_departement(int id_departement) {
		this.id_departement = id_departement;
	}

	public void setNom_departement(String nom_departement) {
		this.nom_departement = nom_departement;
	}

	public Departement(Long id_chef, String nom_departement) {
		super();
		this.id_chef = id_chef;
		this.nom_departement = nom_departement;
	}

	public Departement() {
		super();
	}

	public Departement(int id_departement, Long id_chef, String nom_departement) {
		super();
		this.id_departement = id_departement;
		this.id_chef = id_chef;
		this.nom_departement = nom_departement;
	}


}
