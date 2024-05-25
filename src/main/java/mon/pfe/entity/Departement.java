package mon.pfe.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departement")
	private int id_departement;

	@Column(name = "id_chef")  // Assurez-vous que le nom de la colonne est correct
	private Long id_chef;

	@Column(name = "nom_departement")  // Assurez-vous que le nom de la colonne est correct
	private String nom_departement;

	// Getters and setters
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

	public void setNom_departement(String nom_departement) {
		this.nom_departement = nom_departement;
	}

	public Departement(Long id_chef, String nom_departement) {
		this.id_chef = id_chef;
		this.nom_departement = nom_departement;
	}

	public Departement() {}

	public Departement(int id_departement, Long id_chef, String nom_departement) {
		this.id_departement = id_departement;
		this.id_chef = id_chef;
		this.nom_departement = nom_departement;
	}
}
