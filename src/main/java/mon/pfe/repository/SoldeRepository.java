package mon.pfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mon.pfe.entity.Solde;

public interface SoldeRepository extends JpaRepository<Solde, Long> {

	@Query("select sol from Solde sol where sol.id_employer = :x")
	 public List<Solde> find_by_id_employer(@Param("x")Long id_employer);
	
	@Query("select SUM(total_solde) from Solde where id_employer = :x")
	public int sum(@Param("x")Long id_employer);
	
	@Query("select sol from Solde sol where sol.id_employer = :x AND sol.annee_solde= :y")
	 public Solde find_by_id_employer_anne(@Param("x")Long id_employer,@Param("y")int annee_solde);
	
	@Query("select COUNT(id_solde) from Solde sol where sol.id_employer = :x AND sol.annee_solde= :y")
	 public int count_by_id_employer_anne(@Param("x")Long id_employer,@Param("y")int annee_solde);

	
	
}
