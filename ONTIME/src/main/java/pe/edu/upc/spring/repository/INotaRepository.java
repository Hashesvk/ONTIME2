package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Nota;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Integer> {
	@Query("from Nota n where n.nombreNota like %:nombreNota%")
	List<Nota> buscarNombre(@Param("namePet") String namePet);
}
