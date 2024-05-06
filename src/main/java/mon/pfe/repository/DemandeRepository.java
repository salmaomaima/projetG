package mon.pfe.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mon.pfe.entity.Demande;



public interface DemandeRepository extends JpaRepository<Demande, Long> {

	@Query("select d from Demande d  where d.id_employer = :x AND d.valid = :y")
	 public Demande demande_en_cours(@Param("x")Long id_employer, @Param("y")char valid);
	
	@Query("select dem from Demande dem  where dem.id_employer = :x")
	 public List<Demande> find_id_employer(@Param("x")Long id_employer);
	
	@Query("select COUNT(id_demande) from Demande where id_employer = :x")
	public int count(@Param("x")Long id_employer);
	
	@Query("select COUNT(id_demande) from Demande where id_employer = :x AND valid = :y")
	public int count_demande_en_cours(@Param("x")Long id_employer, @Param("y")char valid);
	

	@Query("select d from Demande d  where d.id_employer = :x AND d.valid = :y AND d.date_debut <:z AND d.date_fin >:z")
	 public List<Demande> find_to_reprise(@Param("x")Long id_employer, @Param("y")char valid,@Param("z") Date date);
	
	@Query("select nr from Demande nr where nr.valid = :x")
	 public List<Demande> find_dem_ann(@Param("x") char valid);

	@Query("select d from Demande d  where d.id_employer = :x AND d.date_debut >:z AND d.valid = :y ")
	 public List<Demande> find_to_annule(@Param("x")Long id_employer,@Param("z") Date date,@Param("y")char valid);
	
	@Query("select d from Demande d  where d.id_employer = :x AND (d.date_debut <:z OR d.valid = :y)")
	 public List<Demande> cant_annul(@Param("x")Long id_employer,@Param("z") Date date,@Param("y")char valid);
}
