package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Notificaciones;

@Repository
public interface INotificacionesRepository extends JpaRepository<Notificaciones, Integer> {
	@Query("from Notificaciones n where n.nombreNotificacion like %:nombreNotificacion%")
	List<Notificaciones> buscarNombre(@Param("nombreNotificacion") String nombreNotificacion);

	
	

}
