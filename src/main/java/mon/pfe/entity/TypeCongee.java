package mon.pfe.entity;

import java.io.Serializable;


import javax.persistence.*;
@Entity
public class TypeCongee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id_type;

	private String nom_type;

	private int nmbre_jours;
	
	private String code_type;
	
	public int getId_type() {
		return id_type;
	}
	
	public String getNom_type() {
		return nom_type;
	}
	
	
	public int getNmbre_jours() {
		return nmbre_jours;
	}

	public void setNmbre_jours(int nmbre_jours) {
		this.nmbre_jours = nmbre_jours;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public void setNom_type(String nom_type) {
		this.nom_type = nom_type;
	}

	
	public TypeCongee() {
		super();
	}

	public TypeCongee( String nom_type, int nmbre_jours, String code_type ) {
		super();
		this.nom_type = nom_type;
		this.nmbre_jours = nmbre_jours;
		this.code_type=code_type;
	}

	public TypeCongee(int id_type, String nom_type, int nmbre_jours, String code_type) {
		super();
		this.id_type = id_type;
		this.nom_type = nom_type;
		this.nmbre_jours = nmbre_jours;
		this.code_type=code_type;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	
	

}
