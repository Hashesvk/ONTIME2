package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Deuda;

@Repository
public interface IDeudaRepository extends JpaRepository<Deuda, Integer> {
	@Query("from Deuda d where d.nameCreditor like %:nameCreditor%")
	List<Deuda> buscarNombre(@Param("nameCreditor") String nameCreditor);
	List<Deuda> findByPersonaUsername(String username);
}
