package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Evento;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Integer> {
	@Query("from Evento e where e.nombreEvento like %:nombreEvento%")
	List<Evento> buscarNombre(@Param("nombreEvento") String nombreEvento);
	
	
}
