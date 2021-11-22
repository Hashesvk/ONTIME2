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
	
	@Query("from Evento e where e.numcomplejidad =:numcomplejidad")
	List<Evento> buscarComple(@Param("numcomplejidad") int numcomplejidad);
	
	@Query("from Evento e where e.tipoEvento.nombreTipoEvento like %:nombreTipoEvento%")
	List<Evento> buscarTevento(@Param("nombreTipoEvento") String nombreTipoEvento);
		
	/*@Query("from Evento e where e.numprioridad like %:numprioridad%")
	List<Evento> buscarPriori(@Param("numprioridad") String numprioridad);
	
	@Query("from Evento e where e.numimportancia like %:numimportancia%")
	List<Evento> buscarImpor(@Param("numimportancia") String numimportancia);*/
}
