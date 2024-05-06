package mon.pfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mon.pfe.entity.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
	
	@Query("select emp from Employer emp  where emp.nom like :x")
 public List<Employer> find_by_nom(@Param("x")String nom);
	
	@Query("select emp from Employer emp  where emp.nom_utilisateur like :x")
	 public List<Employer> find_by_nom_utilisateur(@Param("x")String nom_utilisateur);
	

	@Query(" select nom from Employer ")
	public List<String> empl_nom();
	
	@Query("select id_employer from Employer  where nom_utilisateur like :x")
	public Long get_id(@Param("x")String nom_utilisateur);
	
}
//UPDATE `employer` SET `adresse`="rue",`grade`="grd",`id_departement`=2,`mot_de_passe`="mo",`id_role`=2,`idresponsable`=2,`nom_utilisateur`="sami",`prenom`="pre",`telephone`="123" WHERE id_employer=6