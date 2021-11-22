package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Pendiente;

@Repository
public interface IPendienteRepository extends JpaRepository<Pendiente, Integer> {
	@Query("from Pendiente p where p.namePendiente like %:namePendiente%")
	List<Pendiente> buscarNombre(@Param("namePendiente") String namePendiente);
	List<Pendiente> findByPersonaUsername(String username);
}
