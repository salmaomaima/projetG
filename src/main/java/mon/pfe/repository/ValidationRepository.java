package mon.pfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mon.pfe.entity.Validation;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
	
	@Query("select v from Validation v where v.id_premier_responsable = :x AND v.action_pr =:y")
	 public List<Validation> find_by_id_pr(@Param("x")Long id_employer, @Param("y") char action_pr);
	
	@Query("select v from Validation v where v.id_responsable = :x AND v.action_rd =:y")
	 public List<Validation> find_by_id_rd(@Param("x")Long id_employer, @Param("y") char action_rd);
	
	 @Query("select v from Validation v where v.id_employer = :x AND v.id_demande = :y")
	 public Validation find_id_emp_dem(@Param("x")Long id_employer,@Param("y")Long id_demande);

	@Query("select v from Validation v where v.action_rh = :x")
	 public List<Validation> find_to_archive(@Param("x")char action_rh);
	
	
}
