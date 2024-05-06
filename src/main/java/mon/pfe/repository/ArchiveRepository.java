package mon.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mon.pfe.entity.Archive;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {
	
	@Query("select a from Archive a where a.id_validation = :x")
	 public Archive find_by_id_validation(@Param("x")Long id_validation);

	@Query("select COUNT(id_archive) from Archive where id_validation = :x")
	public int count(@Param("x")Long id_validation);
	
}
