package mon.pfe.entity;

import java.io.Serializable;


import javax.persistence.*;
@Entity
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id_roles;
	
	private String nom_roles;
	
	private String description;
	
	
	
	
	public String getNom_roles() {
		return nom_roles;
	}
	public void setNom_roles(String nom_roles) {
		this.nom_roles = nom_roles;
	}
	
	public int getId_roles() {
		return id_roles;
	}
	public void setId_roles(int id_roles) {
		this.id_roles = id_roles;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Roles(String nom_roles, String description) {
		super();
		this.nom_roles = nom_roles;
		this.description = description;
	}
	public Roles() {
		super();
	}
	
	
	
}
