package mon.pfe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Archive implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id_archive;
	
	private Long id_validation;
	
	private Long id_admin;
	
	private Date date_archive;

	public Long getId_archive() {
		return id_archive;
	}

	public void setId_archive(Long id_archive) {
		this.id_archive = id_archive;
	}

	public Long getId_validation() {
		return id_validation;
	}

	public void setId_validation(Long id_validation) {
		this.id_validation = id_validation;
	}

	public Date getDate_archive() {
		return date_archive;
	}

	public void setDate_archive(Date date_archive) {
		this.date_archive = date_archive;
	}

	
	public Long getId_admin() {
		return id_admin;
	}

	public void setId_admin(Long id_admin) {
		this.id_admin = id_admin;
	} 

	public Archive(Long id_validation, Long id_admin, Date date_archive) {
		super();
		this.id_validation = id_validation;
		this.id_admin = id_admin;
		this.date_archive = date_archive;
	}


	public Archive() {
		super();
	}

	public Archive(Long id_archive, Long id_validation, Long id_admin, Date date_archive) {
		super();
		this.id_archive = id_archive;
		this.id_validation = id_validation;
		this.id_admin = id_admin;
		this.date_archive = date_archive;
	}

	
	
}
