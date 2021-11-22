package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Prueba;

@Repository
public interface IPruebaRepository extends JpaRepository<Prueba, Integer> {
	@Query("from Prueba p where p.namePrueba like %:namePrueba%")
	List<Prueba> buscarNombre(@Param("namePrueba") String namePrueba);
	
	@Query("from Prueba p where p.Tevento.nombreTipoEvento like %:nombreTipoEvento%")
	List<Prueba> buscarTevento(@Param("nombreTipoEvento") String nombreTipoEvento);
	
}
