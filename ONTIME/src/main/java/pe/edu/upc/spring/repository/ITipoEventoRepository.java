package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.TipoEvento;

@Repository
public interface ITipoEventoRepository extends JpaRepository<TipoEvento, Integer> {
	@Query("from TipoEvento t where t.nombreTipoEvento like %:nombreTipoEvento%")
	List<TipoEvento> buscarNombre(@Param("nombreTipoEvento") String nameTipoEvento);
}
