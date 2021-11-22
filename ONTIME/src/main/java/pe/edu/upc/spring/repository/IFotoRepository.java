package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Foto;



@Repository
public interface IFotoRepository extends JpaRepository<Foto, Integer> {
	@Query("from Foto f where f.namephoto like %:namephoto%")
	List<Foto> buscarNombre(@Param("namephoto") String namephoto);
	
	@Query("from Foto f where f.Tevento.nombreTipoEvento like %:nombreTipoEvento%")
	List<Foto> buscarTevento(@Param("nombreTipoEvento") String nombreTipoEvento);
	
	List<Foto> findByPersonaUsername(String username);
}
